package com.kayjet.word.recitation.controllers;

import com.alibaba.fastjson.JSONObject;
import com.kayjet.word.recitation.dto.CreateWordDto;
import com.kayjet.word.recitation.dto.ReviewWordDto;
import com.kayjet.word.recitation.dto.WordGroupDto;
import com.kayjet.word.recitation.entity.WordEntity;
import com.kayjet.word.recitation.service.WordService;
import com.opdar.platform.annotations.JSON;
import com.opdar.platform.annotations.Request;
import com.opdar.platform.core.base.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * WordController
 *
 * @author kai.liu
 * @date 2018/08/13
 */
@Controller
public class WordController {
    private static final Logger logger = LoggerFactory.getLogger(WordController.class);

    @Autowired
    private WordService wordService;

    @Request(value = "/createGroup", format = Request.Format.JSON)
    public WordGroupDto createGroup(@JSON List<CreateWordDto> words) {
        return wordService.createGroup(words);
    }

    @Request(value = "/getGroupList", format = Request.Format.JSON)
    public List<WordGroupDto> getGroupList(String groupId) {
        return wordService.getGroupList(groupId);
    }

    @Request(value = "/getWrongWordsList", format = Request.Format.JSON)
    public List<WordEntity> getWrongWordsList() {
        return wordService.selectAllWrongWords();
    }

    @Request(value = "/review", format = Request.Format.JSON)
    public Integer review(@JSON ReviewWordDto reviewWordDto) {
        return wordService.review(reviewWordDto);
    }


}
