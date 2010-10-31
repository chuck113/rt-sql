package com.rt.hibernate.dto.coredata;

import com.rt.hibernate.dto.Artist;
import com.rt.indexing.ArtistNode;
import com.rt.indexing.PipeLine;

class CoreDataArtist implements Artist<Integer> {
    private static final IdGenerator ID_GENERATOR = new IdGenerator();

    private String name;

    private String lyricWikiArtistName;

    private Integer id;

    private int ent;
    private int opt;

    CoreDataArtist(ArtistNode node) {
        this.name = node.name();
        this.id = ID_GENERATOR.next();

        this.lyricWikiArtistName = EntityProperties.getStringProperty(node, PipeLine.LYRIC_WIKI_ARTIST_URL_PART());
        
        this.ent = 0;
        this.opt = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoreDataArtist() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLyricWikiArtistName() {
        return lyricWikiArtistName;
    }

    public void setLyricWikiArtistName(String lyricWikiArtistName) {
        this.lyricWikiArtistName = lyricWikiArtistName;
    }
}