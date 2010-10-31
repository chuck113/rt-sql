package com.rt.hibernate.dto.coredata;

import com.rt.hibernate.dto.*;
import com.rt.indexing.PipeLine;
import com.rt.indexing.RhymeLeaf;


public class CoreDataRhymePart implements RhymePart<Integer> {
    private static final IdGenerator ID_GENERATOR = new IdGenerator();

    private Integer id;
    private Song song;
    private String word;
    private Rhyme rhyme;
    private Integer rhymeScore;
    private int ent;
    private int opt;

    CoreDataRhymePart(Song song, Rhyme rhyme, RhymeLeaf leaf){
        this(song, rhyme, leaf.word(), getScore(leaf));
    }

    private static int getScore(RhymeLeaf leaf) {
        return Integer.parseInt(leaf.getProp(PipeLine.RHYME_SCORE_KEY(), "-1"));
    }

    private CoreDataRhymePart(Song song, Rhyme rhyme, String word, Integer rhymeScore) {
        this.song = song;
        this.word = word;
        this.rhyme = rhyme;
        this.rhymeScore = rhymeScore;
        this.id = ID_GENERATOR.next();

        this.ent = 0;
        this.opt = 0;
    }

    CoreDataRhymePart(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Rhyme getRhyme() {
        return rhyme;
    }

    public void setRhyme(Rhyme rhyme) {
        this.rhyme = rhyme;
    }

    public Integer getRhymeScore() {
        return rhymeScore;
    }

    public void setRhymeScore(Integer score) {
        this.rhymeScore = score;
    }

    public int getOpt() {
        return opt;
    }

    public void setOpt(int opt) {
        this.opt = opt;
    }

    public int getEnt() {
        return ent;
    }

    public void setEnt(int ent) {
        this.ent = ent;
    }
}
