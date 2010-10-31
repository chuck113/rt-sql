package com.rt.hibernate.dto;

public class StringUtils {
    public static String decode(String st){
        return st != null ? st.replace("&amp;", "&") : "";
    }
}
