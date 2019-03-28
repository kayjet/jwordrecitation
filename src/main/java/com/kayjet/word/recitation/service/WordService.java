package com.kayjet.word.recitation.service;

import com.kayjet.word.recitation.dto.AlreadyStudyDto;
import com.kayjet.word.recitation.dto.CreateWordDto;
import com.kayjet.word.recitation.dto.ReviewWordDto;
import com.kayjet.word.recitation.dto.WordGroupDto;
import com.kayjet.word.recitation.entity.WordEntity;
import com.kayjet.word.recitation.entity.WordGroupRelEntity;
import com.kayjet.word.recitation.entity.WordListGroupEntity;
import com.kayjet.word.recitation.mapper.WordGroupRelMapper;
import com.kayjet.word.recitation.mapper.WordListGroupMapper;
import com.kayjet.word.recitation.mapper.WordMapper;
import com.kayjet.word.recitation.utils.ForgetCurveUtils;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * WordService
 *
 * @author kai.liu
 * @date 2018/08/13
 */
@Service
public class WordService {
    private static final Logger logger = LoggerFactory.getLogger(WordService.class);
    private final DecimalFormat df = new DecimalFormat("######0.00");


    @Autowired
    private WordMapper wordMapper;

    @Autowired
    private WordGroupRelMapper wordGroupRelMapper;

    @Autowired
    private WordListGroupMapper wordListGroupMapper;

    public AlreadyStudyDto countInfo() {
        AlreadyStudyDto alreadyStudyDto = new AlreadyStudyDto();
        alreadyStudyDto.setAll(wordMapper.count(null));
        alreadyStudyDto.setYesterdayAdded(wordMapper.countYesterdayAddedWords(new Timestamp(DateTime.now().minusDays(1).getMillis())));
        alreadyStudyDto.setYesterdayReviewed(wordListGroupMapper.countYesterdayReviewed(new Timestamp(DateTime.now().minusDays(1).getMillis())));
        return alreadyStudyDto;
    }

    public WordGroupDto createGroup(List<CreateWordDto> words) {
        String groupId = UUID.randomUUID().toString();

        List<WordEntity> toSaveWords = checkWords(words);
        for (WordEntity wordEntity : toSaveWords) {
            WordGroupRelEntity wgRel = new WordGroupRelEntity();
            wgRel.setFkGroupId(groupId);
            wgRel.setFkWordId(wordEntity.getTid());
            wordGroupRelMapper.insert(wgRel);
        }

        WordListGroupEntity listGroupEntity = new WordListGroupEntity();
        listGroupEntity.setTid(groupId);
        listGroupEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        wordListGroupMapper.insert(listGroupEntity);

        double hours = 1;
        double forgetCurve = ForgetCurveUtils.getForgetCurve(hours);
        listGroupEntity.setForgetCurve(String.valueOf(forgetCurve));

        WordGroupDto wordGroupDto = new WordGroupDto(listGroupEntity);
        wordGroupDto.setWords(toSaveWords);
        return wordGroupDto;
    }

    public List<WordEntity> checkWords(List<CreateWordDto> words) {
        List<WordEntity> result = new ArrayList<WordEntity>();
        for (CreateWordDto w : words) {
            WordEntity query = new WordEntity();
            query.setKatakana(w.getKatakana());
            WordEntity ret = wordMapper.selectOne(query);
            //如果假名不存在，或者假名相同而文字不相同 则视为新单词
            if (ret == null || (w.getKatakanaCn() != null && !w.getKatakanaCn().equalsIgnoreCase(ret.getKatakanaCn()))) {
                WordEntity toSaveWord = new WordEntity();
                toSaveWord.setTid(UUID.randomUUID().toString());
                toSaveWord.setKatakana(w.getKatakana().trim());
                if (!StringUtils.isEmpty(w.getKatakanaCn())) {
                    toSaveWord.setKatakanaCn(w.getKatakanaCn());
                }
                toSaveWord.setExplaination(w.getExplaination().trim());
                toSaveWord.setCreateTime(new Timestamp(System.currentTimeMillis()));
                toSaveWord.setFirstChar(w.getKatakana().charAt(0) + "");
                toSaveWord.setWordType(w.getWordType());
                wordMapper.insert(toSaveWord);
                result.add(toSaveWord);
            } else {
                if(ret.getWordType() == null ||ret.getWordType().equals(w.getWordType())){
                    ret.setWordType(w.getWordType());
                    wordMapper.update(ret,new WordEntity(ret.getTid()));
                }
                result.add(ret);
            }
        }
        return result;
    }

    public List<WordEntity> selectAllWrongWords() {
        return wordMapper.wrongWords();
    }

    public List<WordGroupDto> getGroupList(String groupId) {
        List<WordGroupDto> result = new ArrayList<WordGroupDto>();
        WordListGroupEntity query = null;
        if (!StringUtils.isEmpty(groupId)) {
            query = new WordListGroupEntity();
            query.setTid(groupId);
        }

        List<WordListGroupEntity> groupList = wordListGroupMapper.selectList(query);
        if (CollectionUtils.isEmpty(groupList)) {
            return result;
        }
        DateTime now = new DateTime();
        for (WordListGroupEntity w : groupList) {
            double forgetCurve = 0D;
            if (w.getLastReviewDatetime() == null) {
                DateTime createDate = new DateTime(w.getCreateTime());
                int hours = Hours.hoursBetween(createDate, now).getHours();
                forgetCurve = ForgetCurveUtils.getForgetCurve(hours);
                w.setForgetCurve(df.format(forgetCurve));
            } else {
                DateTime lastReviewDate = new DateTime(w.getLastReviewDatetime());
                int hours = Hours.hoursBetween(lastReviewDate, now).getHours();
                forgetCurve = ForgetCurveUtils.getForgetCurve(hours);
                w.setForgetCurve(df.format(forgetCurve));
            }

            WordGroupDto wordGroupDto = new WordGroupDto(w);
            List<WordEntity> words = wordGroupRelMapper.selectGroupWords(w.getTid());
            wordGroupDto.setWords(words);
            result.add(wordGroupDto);
        }
        return result;
    }

    public List<WordGroupDto> getGroupList() {
        return this.getGroupList(null);
    }

    public Integer review(ReviewWordDto reviewWordDto) {
        if (!CollectionUtils.isEmpty(reviewWordDto.getWrongWordIds())) {
            wordMapper.updateWrongWordTimes(reviewWordDto.getWrongWordIds());
        }
        String groupId = reviewWordDto.getGroupId();
        WordListGroupEntity query = new WordListGroupEntity(groupId);
        WordListGroupEntity result = wordListGroupMapper.selectOne(query);
        result.setLastReviewDatetime(new Timestamp(System.currentTimeMillis()));
        result.setReviewTimes(result.getReviewTimes() + 1);
        return wordListGroupMapper.update(result, query);
    }

    public List<WordEntity> reviewRandomWrongsWords() {
        return wordMapper.reviewWrongWords();
    }
}
