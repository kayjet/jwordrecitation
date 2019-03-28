package com.kayjet.word.recitation.mapper;

import com.kayjet.word.recitation.entity.WordEntity;
import com.kayjet.word.recitation.entity.WordGroupRelEntity;
import com.opdar.plugins.mybatis.core.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * WordGroupRelMapper
 *
 * @author kai.liu
 * @date 2018/08/13
 */
public interface WordGroupRelMapper extends IBaseMapper<WordGroupRelEntity> {
    List<WordEntity> selectGroupWords(@Param("fkGroupId") String fkGroupId);
}
