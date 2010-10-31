package com.rt.hibernate.dto;

import com.rt.indexing.ArtistNode;

public interface Artist<ID> {

    public interface Factory {
        Artist create(ArtistNode node);
    }

    String getName();

    void setName(String name);

    ID getId();

    void setId(ID id);
}
