package com.kayjet.word.recitation.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * CreateWordDto
 *
 * @author kai.liu
 * @date 2018/08/13
 */
public class CreateWordDto implements Serializable {
    private String katakana;
    private String katakanaCn;
    private String explaination;
    private String wordType;

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

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }
}
