package com.rt.hibernate.dto.vanilla;

import com.rt.hibernate.dto.Rhyme;
import com.rt.hibernate.dto.RhymePart;
import com.rt.hibernate.dto.Song;

public class RhymePartImpl implements RhymePart<Long> {

    private Long id;
    private Song song;
    private String word;
    private Rhyme rhyme;
    private Integer rhymeScore;

    RhymePartImpl() {
    }

    public RhymePartImpl(Song song, String word, Rhyme rhyme) {
        this.song = song;
        this.word = word;
        this.rhyme = rhyme;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Song getSong() {
        return song;
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

    public void setRhymeScore(Integer rhymeScore) {
        this.rhymeScore = rhymeScore;
    }


    @Override
    public String toString() {
        return "RhymePartImpl{" +
                "song=" + song +
                ", word='" + word + '\'' +
                '}';
    }
}
