package com.jt.blog.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : 戴瑞
 * @create 2016-08-31 15
 **/
public class AutoNoGenerator {
    private static AtomicInteger autono = new AtomicInteger(0);

    public static String nextNo() {
        long next = autono.addAndGet(1);
        if (next > 100) { //每秒最多提交999个文件
            autono.set(1);
            next = 1;
        }
        String nextStr = "" + next;
        nextStr = StringUtils.leftPad(nextStr, 3, "0");
        String result = new Date().getTime() + nextStr;
        return result;
    }
}
