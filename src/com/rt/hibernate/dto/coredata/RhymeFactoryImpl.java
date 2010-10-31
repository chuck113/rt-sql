package com.rt.hibernate.dto.coredata;

import com.rt.hibernate.dto.Rhyme;
import com.rt.indexing.RhymeLeaf;

import java.util.List;

public class RhymeFactoryImpl implements Rhyme.Factory{

    public Rhyme create(RhymeLeaf leaf, List<String> otherRhymeParts) {
        return new CoreDataRhyme(leaf, otherRhymeParts);
    }

//    public Rhyme create(Song song, String word, List<String> rhymeLines, List<String> rhymeParts, List<String> otherRhymeParts, Integer score) {
//        return new CoreDataRhymePart(song, word, rhymeLines, rhymeParts, otherRhymeParts, score);
//    }
}

