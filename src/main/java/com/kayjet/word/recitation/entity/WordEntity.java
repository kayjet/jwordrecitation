package com.kayjet.word.recitation.entity;

import com.kayjet.word.recitation.mapper.WordMapper;
import com.opdar.plugins.mybatis.annotations.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * WordEntity
 *
 * @author kai.liu
 * @date 2018/08/13
 */
@Namespace(WordMapper.class)
public class WordEntity implements Serializable {
    private String tid;
    private String katakana;
    private String katakanaCn;
    private String explaination;
    private String firstChar;
    private Integer wrongTimes;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String wordType;

    public WordEntity() {
    }

    public WordEntity(String tid) {
        this.tid = tid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getKatakana() {
        return katakana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    public String getKatakanaCn() {
        return katakanaCn;
    }

    public void setKatakanaCn(String katakanaCn) {
        this.katakanaCn = katakanaCn;
    }

    public Integer getWrongTimes() {
        return wrongTimes;
    }

    public void setWrongTimes(Integer wrongTimes) {
        this.wrongTimes = wrongTimes;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }
}
