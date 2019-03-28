package com.kayjet.word.recitation.dto;

import com.kayjet.word.recitation.entity.WordEntity;
import com.kayjet.word.recitation.entity.WordGroupRelEntity;
import com.kayjet.word.recitation.entity.WordListGroupEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * WordGroupDto
 *
 * @author kai.liu
 * @date 2018/08/13
 */
public class WordGroupDto extends WordListGroupEntity {
    public WordGroupDto() {

    }

    public WordGroupDto(WordListGroupEntity wordGroupRelEntity) {
        setTid(wordGroupRelEntity.getTid());
        setCreateTime(wordGroupRelEntity.getCreateTime());
        setUpdateTime(wordGroupRelEntity.getUpdateTime());
        setForgetCurve(wordGroupRelEntity.getForgetCurve());
        setReviewTimes(wordGroupRelEntity.getReviewTimes());
        setLastReviewDatetime(wordGroupRelEntity.getLastReviewDatetime());
    }

    private List<WordEntity> words;

    public List<WordEntity> getWords() {
        return words;
    }

    public void setWords(List<WordEntity> words) {
        this.words = words;
    }
}
