package com.rt.hibernate.dto;

import com.rt.indexing.AlbumNode;
import com.rt.indexing.Node;

public interface Album<ID> {

    public interface Factory {
        public Album create(AlbumNode node, Artist artist);
    }

    ID getId();

    void setId(ID id);

    String getTitle();

    void setTitle(String title);

    Artist getArtist();

    void setArtist(Artist artist);
}
