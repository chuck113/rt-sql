package com.rt.hibernate.dto;

import java.util.Arrays;
import java.util.List;

public class ListSerializer {

    private static final String DELIMITER = "%%%";

     public static String serialize(List<String> strings){
        if(strings.size() == 0)return "";

        StringBuilder res = new StringBuilder(strings.get(0));
        for(int i=1; i<strings.size(); i++){
            res.append(DELIMITER+strings.get(i));
        }

        return res.toString();
    }

    public static List<String> deserialize(String st){
        return Arrays.asList(st.split(DELIMITER));
    }
}
