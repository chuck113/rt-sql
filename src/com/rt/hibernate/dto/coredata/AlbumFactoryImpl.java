package com.rt.hibernate.dto.coredata;

import com.rt.hibernate.dto.Album;
import com.rt.hibernate.dto.Artist;
import com.rt.indexing.AlbumNode;
import com.rt.indexing.ArtistNode;
import com.rt.indexing.Node;

public class AlbumFactoryImpl implements Album.Factory{
    public Album create(AlbumNode node, Artist artist) {
        return new CoreDataAlbum(node, artist);
    }

//    public Album create(Artist artist, String title) {
//        return new CoreDataAlbum(title, artist);
//    }
}
