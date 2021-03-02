package com.bjpowernode.p2p.util;

import java.util.regex.Pattern;

public class CommonUtil {

    public static boolean checkPhoneFormat(String phone){

        boolean result = Pattern.matches("^1[1-9]\\d{9}$",phone);
        return result;
    }

}
