package com.kayjet.word.recitation.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kayjet.word.recitation.dto.WordGroupDto;
import com.kayjet.word.recitation.entity.WordEntity;
import com.kayjet.word.recitation.service.WordService;
import com.opdar.platform.annotations.Request;
import com.opdar.platform.core.base.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * ViewContronller
 *
 * @author kai.liu
 * @date 2018/10/26
 */
@Controller
public class ViewContronller {
    Logger logger = LoggerFactory.getLogger(ViewContronller.class);

    @Autowired
    private WordService wordService;


    @Request(value = "/reviewWrongWordsView", format = Request.Format.VIEW)
    public String reviewWrongWordsView() {
        List<WordEntity> wordEntities = wordService.reviewRandomWrongsWords();
        Context.putAttribute("wrongWords", wordEntities);
        Context.putAttribute("wrongWordsJson", JSON.toJSONString(wordEntities));
        return "rand_review";
    }

    @Request(value = "/createView", format = Request.Format.VIEW)
    public String createView() {
        return "create";
    }

    @Request(value = "/wrongView", format = Request.Format.VIEW)
    public String wrongView() {
        return "wrong";
    }

    @Request(value = "/reviewView", format = Request.Format.VIEW)
    public String reviewView(String groupId) {
        Context.putAttribute("groupId", groupId);
        List<WordGroupDto> result = wordService.getGroupList(groupId);
        Context.putAttribute("wordGroupDto", result);
        Context.putAttribute("wordGroupDtoJson", JSONObject.toJSONString(result));
        Context.putAttribute("words", result.get(0).getWords());
        return "review";
    }

    @Request(value = "/index", format = Request.Format.VIEW)
    public String index() {
        Context.putAttribute("countInfo", wordService.countInfo());
        return "index";
    }
}
