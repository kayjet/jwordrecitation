package com.kayjet.word.recitation.entity;

import com.kayjet.word.recitation.mapper.WordGroupRelMapper;
import com.opdar.plugins.mybatis.annotations.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * WordGroupRelEntity
 *
 * @author kai.liu
 * @date 2018/08/13
 */
@Namespace(WordGroupRelMapper.class)
public class WordGroupRelEntity implements Serializable {
    private String fkGroupId;
    private String fkWordId;

    public String getFkGroupId() {
        return fkGroupId;
    }

    public void setFkGroupId(String fkGroupId) {
        this.fkGroupId = fkGroupId;
    }

    public String getFkWordId() {
        return fkWordId;
    }

    public void setFkWordId(String fkWordId) {
        this.fkWordId = fkWordId;
    }
}
