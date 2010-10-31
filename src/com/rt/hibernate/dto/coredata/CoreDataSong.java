package com.rt.hibernate.dto.coredata;

import com.rt.hibernate.dto.Album;
import com.rt.hibernate.dto.StringUtils;
import com.rt.hibernate.dto.coredata.IdGenerator;
import com.rt.hibernate.dto.ListSerializer;
import com.rt.hibernate.dto.Song;
import com.rt.indexing.AlbumNode;
import com.rt.indexing.PipeLine;
import com.rt.indexing.SongNode;

class CoreDataSong implements Song<Integer> {
    private static transient final ListSerializer serializer = new ListSerializer();
    private static final IdGenerator ID_GENERATOR = new IdGenerator();

    private String title;
    private Integer id;
    private Album album;

    private int ent;
    private int opt;

    private String lyricWikiSongName;

    CoreDataSong(SongNode song, Album album) {
        this.album = album;
        this.title = StringUtils.decode(song.title());
        this.id = ID_GENERATOR.next();

        this.lyricWikiSongName = EntityProperties.getStringProperty(song, PipeLine.LYRIC_WIKI_SONG_URL_PART());


        this.ent = 0;
        this.opt = 0;
    }

    CoreDataSong() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getLyricWikiSongName() {
        return lyricWikiSongName;
    }

    public void setLyricWikiSongName(String lyricWikiSongName) {
        this.lyricWikiSongName = lyricWikiSongName;
    }

    public int getEnt() {
        return ent;
    }

    public void setEnt(int ent) {
        this.ent = ent;
    }

    public int getOpt() {
        return opt;
    }

    public void setOpt(int opt) {
        this.opt = opt;
    }
}
