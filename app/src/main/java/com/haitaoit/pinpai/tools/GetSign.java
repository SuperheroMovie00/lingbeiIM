package com.haitaoit.pinpai.tools;

import com.github.androidtools.MD5;
import com.haitaoit.pinpai.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetSign {
    public static String getSign(String key, String value) {
        StringBuilder sb = new StringBuilder();
        sb.append(Config.KEY);
        sb.append(key);
        sb.append(value);
        sb.append(Config.KEY);
        String packageSign = MD5.getMessageDigest(sb.toString().getBytes())
                .toUpperCase();
        return exChange(packageSign);
    }

    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();
        map.put("b","b");
        map.put("a","a");
        map.put("e","e");
        map.put("c","c");
        map.put("d","d");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
        Object[] key_arr = map.keySet().toArray();
        Arrays.sort(key_arr);
        for  (Object key : key_arr) {
            Object value = map.get(key);
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
    public static String getSign (Map<String,String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(Config.KEY);
        if(params instanceof HashMap){
            Object[] key_arr = params.keySet().toArray();
            Arrays.sort(key_arr);
            for  (Object key : key_arr) {
                sb.append(key);
                sb.append(params.get(key));
            }
        }else{
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey());
                sb.append(entry.getValue());
            }
        }
        sb.append(Config.KEY);
        String packageSign = MD5.getMessageDigest(sb.toString().getBytes())
                .toUpperCase();
        return exChange(packageSign);
    }

/*    static Comparator<NameValuePairSign> comparator = new Comparator<NameValuePairSign>() {

        public int compare(NameValuePairSign lhs, NameValuePairSign rhs) {
            return lhs.getName().compareTo(rhs.getName());
        }
    };*/

    public static String exChange(String str) {
        StringBuffer sb = new StringBuffer();
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append(Character.toLowerCase(c));
                } else if (Character.isLowerCase(c)) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    // 判断邮箱格式是否正确
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    public static String genPackageSign(List<NameValuePairSign> params) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(params, comparator);
        sb.append(Config.KEY);
        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append(params.get(i).getValue());
        }
        sb.append(Config.KEY);

        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        return exChange(packageSign);
    }

    static Comparator<NameValuePairSign> comparator = new Comparator<NameValuePairSign>() {

        public int compare(NameValuePairSign lhs, NameValuePairSign rhs) {
            return lhs.getName().compareTo(rhs.getName());
        }
    };

}
