package com.rt.hibernate.dto.vanilla;

import com.rt.hibernate.dto.Song;
import com.rt.indexing.SongNode;

class SongImpl implements Song<Long> {
    private String title;
    private Long id;

    public static Song fromSongNode(SongNode songNode){
        return new SongImpl(songNode.title());
    }

    SongImpl() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    SongImpl(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
