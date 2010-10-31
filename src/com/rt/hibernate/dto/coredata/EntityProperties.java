package com.rt.hibernate.dto.coredata;

import com.rt.indexing.Node;

public class EntityProperties {
    static int getIntProperty(Node node, String propName){
        return Integer.parseInt(node.getProp(propName, "-1"));
    }

    static String getStringProperty(Node node, String propName){
        return node.getProp(propName, "");
    }
}
