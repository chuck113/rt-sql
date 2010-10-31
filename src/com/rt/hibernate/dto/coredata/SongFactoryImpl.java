package com.rt.hibernate.dto.coredata;

import com.rt.hibernate.dto.Album;
import com.rt.hibernate.dto.Song;
import com.rt.hibernate.dto.StringUtils;
import com.rt.indexing.SongNode;

public class SongFactoryImpl implements Song.Factory {
    public Song create(SongNode node, Album album) {
        return new CoreDataSong(node, album);
    }
}
