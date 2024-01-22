package com.luoying.model.params;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 星座运势请求参数
 */
@Data
@Accessors(chain = true)
public class HoroscopeParams implements Serializable {
    /**
     * 十二星座对应英文小写，aries, taurus, gemini, cancer, leo, virgo, libra, scorpio, sagittarius, capricorn, aquarius, pisces
     */
    private String type;
    /**
     * 十二星座对应英文小写，aries, taurus, gemini, cancer, leo, virgo, libra, scorpio, sagittarius, capricorn, aquarius, pisces
     */
    private String time;

    private static final long serialVersionUID = 1L;
}
