package com.kayjet.word.recitation.dto;


/**
 * AlreadyStudyDto
 *
 * @author kai.liu
 * @date 2019/03/14
 */
public class AlreadyStudyDto {
    private Integer all;
    private Integer yesterdayAdded;
    private Integer yesterdayReviewed;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public Integer getYesterdayAdded() {
        return yesterdayAdded;
    }

    public void setYesterdayAdded(Integer yesterdayAdded) {
        this.yesterdayAdded = yesterdayAdded;
    }

    public Integer getYesterdayReviewed() {
        return yesterdayReviewed;
    }

    public void setYesterdayReviewed(Integer yesterdayReviewed) {
        this.yesterdayReviewed = yesterdayReviewed;
    }
}
