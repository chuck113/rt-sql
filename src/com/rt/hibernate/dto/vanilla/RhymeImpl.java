package com.rt.hibernate.dto.vanilla;

import com.rt.hibernate.dto.Rhyme;

import java.util.Arrays;
import java.util.List;

public class RhymeImpl implements Rhyme<Long> {

    private Long id;
    private String rhymeLines;
    private String rhymeParts;
    private String wordsNotIndexed;

        private static final String DELIMITER = "%%%";

    private static String serialize(List<String> strings){
        if(strings == null || strings.size() == 0)return "";

        StringBuilder res = new StringBuilder(strings.get(0));
        for(int i=1; i<strings.size(); i++){
            res.append(DELIMITER+strings.get(i));
        }

        return res.toString();
    }

    public RhymeImpl() {
    }

    private static List<String> deserialize(String st){
        return Arrays.asList(st.split(DELIMITER));
    }

   public RhymeImpl(List<String> rhymeLines, List<String> rhymeParts, List<String> otherRhymeParts) {
        this.rhymeLines = serialize(rhymeLines);
        this.rhymeParts = serialize(rhymeParts);
        this.wordsNotIndexed = serialize(otherRhymeParts);
    }

    RhymeImpl( String rhymeLines, String rhymeParts, String otherRhymeParts) {
        this.rhymeLines = rhymeLines;
        this.rhymeParts = rhymeParts;
        this.wordsNotIndexed = otherRhymeParts;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
