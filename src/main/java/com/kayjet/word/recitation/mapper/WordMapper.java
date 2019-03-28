package com.kayjet.word.recitation.mapper;

import com.kayjet.word.recitation.entity.WordEntity;
import com.opdar.plugins.mybatis.core.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * WordMapper
 *
 * @author kai.liu
 * @date 2018/08/13
 */
public interface WordMapper extends IBaseMapper<WordEntity> {

    Integer updateWrongWordTimes(@Param("wordIds") List<String> wordIds);

    List<WordEntity> wrongWords();

    List<WordEntity> reviewWrongWords();

    Integer countYesterdayAddedWords(@Param("time")Timestamp time);

}
