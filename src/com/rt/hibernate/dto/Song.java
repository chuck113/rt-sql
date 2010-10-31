package com.rt.hibernate.dto;


import com.rt.indexing.SongNode;

import java.util.List;

public interface Song<ID> {

    public interface Factory{
        Song create(SongNode node, Album album);
    }

    ID getId();

    void setId(ID id);

    String getTitle();

    void setTitle(String title);
}
