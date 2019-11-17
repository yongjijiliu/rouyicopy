package com.ruoyi.framework.config;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import com.google.code.kaptcha.text.impl.DefaultTextCreator;
import lombok.extern.slf4j.Slf4j;

/**
 * 验证码文本生成器
 *
 * @author ruoyi
 */
@Slf4j
public class KaptchaTextCreator extends DefaultTextCreator {
    private static final String[] CNUMBERS = "11,10,20,100,4,5,6,7,8,9,10".split(",");

    private static Random random;

    static {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(),e);
        }
    }

    @Override
    public String getText() {
        Integer result = 0;
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        StringBuilder suChinese = new StringBuilder();
        int randomoperands = new Random().nextInt(2);
        if (randomoperands == 0) {
            result = x * y;
            suChinese.append(CNUMBERS[x]);
            suChinese.append("*");
            suChinese.append(CNUMBERS[y]);
        } else if (randomoperands == 1) {
            if (x != 0 && y % x == 0) {
                result = y / x;
                suChinese.append(CNUMBERS[y]);
                suChinese.append("/");
                suChinese.append(CNUMBERS[x]);
            } else {
                result = x + y;
                suChinese.append(CNUMBERS[x]);
                suChinese.append("+");
                suChinese.append(CNUMBERS[y]);
            }
        } else if (randomoperands == 2) {
            if (x >= y) {
                result = x - y;
                suChinese.append(CNUMBERS[x]);
                suChinese.append("-");
                suChinese.append(CNUMBERS[y]);
            } else {
                result = y - x;
                suChinese.append(CNUMBERS[y]);
                suChinese.append("-");
                suChinese.append(CNUMBERS[x]);
            }
        } else {
            result = x + y;
            suChinese.append(CNUMBERS[x]);
            suChinese.append("+");
            suChinese.append(CNUMBERS[y]);
        }
        suChinese.append("=?@").append(result);
        return suChinese.toString();
    }
}
