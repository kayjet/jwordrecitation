package com.kayjet.word.recitation.entity;

import com.kayjet.word.recitation.mapper.WordListGroupMapper;
import com.opdar.plugins.mybatis.annotations.Field;
import com.opdar.plugins.mybatis.annotations.Namespace;
import com.opdar.plugins.mybatis.annotations.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * WordListGroupEntity
 *
 * @author kai.liu
 * @date 2018/08/13
 */
@Namespace(WordListGroupMapper.class)
public class WordListGroupEntity implements Serializable {
    private String tid;
    @Sort(type = Sort.SortType.DESC)
    private Timestamp createTime;
    private Timestamp updateTime;
    @Field(
            resultmap = false,
            insert = false,
            update = false,
            delete = false,
            select = false
    )
    private String forgetCurve;
    private Integer reviewTimes;
    private Timestamp lastReviewDatetime;

    public WordListGroupEntity() {
    }

    public WordListGroupEntity(String tid) {
        this.tid = tid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
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

    public String getForgetCurve() {
        return forgetCurve;
    }

    public void setForgetCurve(String forgetCurve) {
        this.forgetCurve = forgetCurve;
    }

    public Integer getReviewTimes() {
        return reviewTimes;
    }

    public void setReviewTimes(Integer reviewTimes) {
        this.reviewTimes = reviewTimes;
    }

    public Timestamp getLastReviewDatetime() {
        return lastReviewDatetime;
    }

    public void setLastReviewDatetime(Timestamp lastReviewDatetime) {
        this.lastReviewDatetime = lastReviewDatetime;
    }
}
