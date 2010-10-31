package com.rt.hibernate.dto.coredata;

import com.rt.hibernate.dto.ListSerializer;
import com.rt.hibernate.dto.Rhyme;
import com.rt.indexing.RhymeLeaf;
import com.rt.util.ScalaConversions;

import java.util.List;

public class CoreDataRhyme implements Rhyme<Integer> {
    private static transient final ListSerializer serializer = new ListSerializer();
    private static final IdGenerator ID_GENERATOR = new IdGenerator();

    private String rhymeLines;
    private String rhymeParts;
    private String wordsNotIndexed;

    private Integer id;

    private int ent;
    private int opt;

    CoreDataRhyme(RhymeLeaf leaf, List<String> wordsNotIndexed) {
        this(
                ScalaConversions.toJavaList(leaf.lines()),
                ScalaConversions.toJavaList(leaf.parts()),
                wordsNotIndexed);
    }

    CoreDataRhyme(List<String> rhymeLines, List<String> rhymeParts, List<String> wordsNotIndexed) {
        this(
                serializer.serialize(rhymeLines),
                serializer.serialize(rhymeParts),
                serializer.serialize(wordsNotIndexed));
    }

    private CoreDataRhyme(String rhymeLines, String rhymeParts, String wordsNotIndexed) {
        this.rhymeLines = rhymeLines;
        this.rhymeParts = rhymeParts;
        this.wordsNotIndexed = wordsNotIndexed;

        this.id = ID_GENERATOR.next();

        this.ent = 0;
        this.opt = 0;
    }

    public CoreDataRhyme() {
    }

    public String getRhymeLines() {
        return rhymeLines;
    }

    public void setRhymeLines(String rhymeLines) {
        this.rhymeLines = rhymeLines;
    }

    public String getRhymeParts() {
        return rhymeParts;
    }

    public void setRhymeParts(String rhymeParts) {
        this.rhymeParts = rhymeParts;
    }

    public String getWordsNotIndexed() {
        return wordsNotIndexed;
    }

    public void setWordsNotIndexed(String wordsNotIndexed) {
        this.wordsNotIndexed = wordsNotIndexed;
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

    public void setId(Integer integer) {
        this.id = integer;
    }
}
