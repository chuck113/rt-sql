package com.rt.hibernate;

import com.rt.hibernate.dto.DuplicationFinder;
import com.rt.hibernate.dto.RhymePart;
import com.rt.hibernate.dto.vanilla.RhymeImpl;
import com.rt.hibernate.dto.vanilla.RhymePartImpl;
import junit.framework.Assert;
import org.junit.Test;

import java.util.*;

public class DuplicationTest {

    private DuplicationFinder duplicationFinder = new DuplicationFinder();

    @Test
    public void ShouldFindDuplicate(){
        Collection<RhymePart> rhymeParts = new ArrayList<RhymePart>();
        rhymeParts.add(newRhymePart(
                "Tre' pound seven spin around for my bredren the cloud comes down"));

        List<String> lines = Arrays.asList(
                "Tre' pound seven spin around for my bredren the clouds come down",
                "War and peace, I take it to the street");

        Assert.assertTrue(duplicationFinder.isSimilarToAnyOtherRhymes(lines, rhymeParts));
    }

    @Test
    public void ShouldFindDuplicateAtcq(){
        Collection<RhymePart> rhymeParts = new ArrayList<RhymePart>();
        rhymeParts.add(newRhymePart(
                "Hah-hah, yo, when it's time to flow I suprise and blow",
                "five hundred thousand units off a dime a trow",
                "Forty below, I'm thorough when it's time to throw",
                "the caboose, I'm even hard to be touched by a masousse"));

        List<String> lines = Arrays.asList(
                "Hah-hah, yo, when it's time to flow I suprise and blow",
                "five hundred thousand units off a dime a trow",
                "Forty below, I'm thorough when it's time to throw");

        Assert.assertTrue(duplicationFinder.isSimilarToAnyOtherRhymes(lines, rhymeParts));
    }

    private RhymePart newRhymePart(String ... lines){
        return newRhymePart(Arrays.asList(lines));
    }

    private RhymePart newRhymePart(List<String> lines){
        return new RhymePartImpl(null, null, new RhymeImpl(lines, Collections.<String>emptyList(), Collections.<String>emptyList()));
    }
}
