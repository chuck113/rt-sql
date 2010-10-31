package com.rt.hibernate.dto.coredata;

import com.rt.hibernate.dto.Artist;
import com.rt.indexing.ArtistNode;

public class ArtistFactoryImpl implements Artist.Factory{
    public Artist create(ArtistNode node) {
        return new CoreDataArtist(node);
    }
}