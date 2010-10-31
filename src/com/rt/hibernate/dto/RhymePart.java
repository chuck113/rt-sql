package com.rt.hibernate.dto;

import com.rt.hibernate.dto.coredata.*;
import com.rt.indexing.RhymeLeaf;

import java.util.List;

public interface RhymePart<ID> {

    public interface Factory {
        RhymePart create(Rhyme rhyme, Song song, RhymeLeaf leaf);
    }

    ID getId();

    void setId(ID id);

    Song getSong();

    void setSong(Song song);

    Rhyme getRhyme();

    void setRhyme(Rhyme rhyme);

    String getWord();

    void setWord(String word);

//    String getRhymeLines();
//
//    void setRhymeLines(String rhymeLines);
//
//    String getRhymeParts();
//
//    void setRhymeParts(String rhymeParts);
//
//    String getWordsNotIndexed();
//
//    void setWordsNotIndexed(String otherRhymeParts);

    public Integer getRhymeScore();

    public void setRhymeScore(Integer score);
}
