package com.rt.hibernate.dto;

import com.rt.indexing.RhymeLeaf;

import java.util.List;

public interface Rhyme<ID> {

    public interface Factory {
        public Rhyme create(RhymeLeaf rhymeLeaf, List<String> otherRhymeParts);
    }

    String getRhymeLines();

    void setRhymeLines(String rhymeLines);

    String getRhymeParts();

    void setRhymeParts(String rhymeParts);

    String getWordsNotIndexed();

    void setWordsNotIndexed(String wordsNotIndexed);

    ID getId();

    void setId(ID id);
}