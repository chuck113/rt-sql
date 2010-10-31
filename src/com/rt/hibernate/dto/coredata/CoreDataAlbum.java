package com.rt.hibernate.dto.coredata;

import com.rt.hibernate.dto.Album;
import com.rt.hibernate.dto.Artist;
import com.rt.indexing.AlbumNode;
import com.rt.indexing.Node;
import com.rt.indexing.PipeLine;

class CoreDataAlbum implements Album<Integer> {
    private static final IdGenerator ID_GENERATOR = new IdGenerator();

    private String title;
    private Artist artist;
    private Integer id;

    private Integer itunesUs;
    private Integer itunesEu;

    private int ent;
    private int opt;

    CoreDataAlbum(AlbumNode album, Artist artist) {
        this.title = album.title();
        this.artist = artist;
        this.id = ID_GENERATOR.next();

        this.itunesUs = getItunesUsId(album);
        this.itunesEu = getItunesEuId(album);

        this.ent = 0;
        this.opt = 0;
    }


    private static int getItunesEuId(AlbumNode album) {
        return EntityProperties.getIntProperty(album, PipeLine.ITUNES_ALBUM_ID_EU());
    }

    private static int getItunesUsId(AlbumNode album) {
        return EntityProperties.getIntProperty(album, PipeLine.ITUNES_ALBUM_ID_US());
    }

    public CoreDataAlbum() {
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getItunesUs() {
        return itunesUs;
    }

    public void setItunesUs(Integer itunesUs) {
        this.itunesUs = itunesUs;
    }

    public Integer getItunesEu() {
        return itunesEu;
    }

    public void setItunesEu(Integer itunesEu) {
        this.itunesEu = itunesEu;
    }
}
