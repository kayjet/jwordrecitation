package com.kayjet.word.recitation.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * ReviewWordDto
 *
 * @author kai.liu
 * @date 2018/08/14
 */
public class ReviewWordDto implements Serializable {
    private String groupId;
    private List<String> wrongWordIds;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getWrongWordIds() {
        return wrongWordIds;
    }

    public void setWrongWordIds(List<String> wrongWordIds) {
        this.wrongWordIds = wrongWordIds;
    }
}
