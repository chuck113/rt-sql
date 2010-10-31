package com.rt.hibernate.dto.coredata;

import com.rt.hibernate.dto.Rhyme;
import com.rt.hibernate.dto.RhymePart;
import com.rt.hibernate.dto.Song;
import com.rt.indexing.RhymeLeaf;

import java.util.List;

public class RhymePartFactoryImpl implements RhymePart.Factory{    

    public RhymePart create(Rhyme rhyme, Song song, RhymeLeaf leaf) {
        return new CoreDataRhymePart(song, rhyme, leaf);
    }

//    public RhymePart create(Song song, String word, List<String> rhymeLines, List<String> rhymeParts, List<String> otherRhymeParts, Integer score) {
//        return new CoreDataRhymePart(song, word, rhymeLines, rhymeParts, otherRhymeParts, score);
//    }
}

