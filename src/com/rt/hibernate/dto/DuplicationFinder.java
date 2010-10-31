package com.rt.hibernate.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DuplicationFinder {

    public boolean isSimilarToAnyOtherRhymes(List<String> lines, Collection<RhymePart> rhymeParts) {
        for (RhymePart rhymePart : rhymeParts) {
            String existingRhymeLines = rhymePart.getRhyme().getRhymeLines();
            String existingMerged = merge(ListSerializer.deserialize(existingRhymeLines));
            String newMerged = merge(lines);
            //System.out.println("comparing "+ newMerged + " and  "+merge(lines) + ": "+(areStringsSimilar(newMerged, existingMerged)));
            if(areStringsSimilar(newMerged, existingMerged)) {
                //System.out.println("Found duplicate lines: "+lines+" and "+newMerged);
                return true;
            }
        }
        return false;
    }

    private String merge(List<String> strings){
        StringBuffer sb = new StringBuffer();
        for (String st : strings) {
            sb.append(st +" ");
        }
        return sb.toString();
    }

    // approach - separate words into list, if first half are the same return true
    private static boolean areStringsSimilar(String one, String two){
        List<String> oneList = Arrays.asList(one.split(" "));
        List<String> twoList = Arrays.asList(two.split(" "));

        int shortest = Math.min(oneList.size(), twoList.size());

        int count = 0;
        for(int i=0; i<shortest; i++){
            if(oneList.get(i).equalsIgnoreCase(twoList.get(i))){
                if(count++ > (shortest/2))return true;
            }
        }
        return false;
    }
}
