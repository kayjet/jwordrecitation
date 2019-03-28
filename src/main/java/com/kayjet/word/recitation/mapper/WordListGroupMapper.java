package com.kayjet.word.recitation.mapper;

import com.kayjet.word.recitation.entity.WordListGroupEntity;
import com.opdar.plugins.mybatis.core.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

/**
 * WordListGroupMapper
 *
 * @author kai.liu
 * @date 2018/08/13
 */
public interface WordListGroupMapper extends IBaseMapper<WordListGroupEntity> {
    Integer countYesterdayReviewed(@Param("time") Timestamp time);
}
