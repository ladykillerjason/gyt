/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package org.gyt.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormat {

    private static SimpleDateFormat timeFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    private static SimpleDateFormat timeFormat2 = new SimpleDateFormat("YYYYMMddHHmmss");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

    public static String timeFormat(Date date){
        return timeFormat.format(date);
    }

    public static String timeFormat2(Date date){
        return timeFormat2.format(date);
    }

    public static String dateFormat(Date date){
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        System.out.println(timeFormat2(new Date()));
    }
}
