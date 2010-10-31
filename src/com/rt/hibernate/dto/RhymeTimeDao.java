package com.rt.hibernate.dto;

import com.google.common.collect.*;
import com.rt.Properties;
import com.rt.indexing.*;
import com.rt.rhyme.StringRhymeUtils;
import com.rt.util.ScalaConversions;
import com.rt.util.Strings;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.util.List;

import java.util.*;

import static java.lang.String.format;

public class RhymeTimeDao {

    private final RhymePart.Factory rhymePartFactory;
    private final Song.Factory songFactory;
    private final Album.Factory albumFactory;
    private final Artist.Factory artistFactory;
    private final Rhyme.Factory rhymeFactory;

    private final int MAX_NUMBER_OF_ENTRIES = 30;

    public RhymeTimeDao(RhymePart.Factory rhymePartFactory, Song.Factory songFactory, Album.Factory albumFactory, Artist.Factory artistFactory, Rhyme.Factory rhymeFactory) {
        this.rhymePartFactory = rhymePartFactory;
        this.songFactory = songFactory;
        this.albumFactory = albumFactory;
        this.artistFactory = artistFactory;
        this.rhymeFactory = rhymeFactory;
    }

    public List<RhymePart> findRhymes(String wordQuery) {
        Session session = newSession();

        Query query = session.createQuery("from CoreDataRhymePart p where p.word = :wordQuery").
                setString("wordQuery", wordQuery);

        return query.list();
    }

    private Session newSession() {
        Configuration cfg = new Configuration();
        SessionFactory sessionFactory = cfg.configure("hibernate.sqlite.cfg.xml").buildSessionFactory();

        return sessionFactory.openSession();
    }

    private static final Set<String> UNWANTED_WORDS = new HashSet<String>(toJavaList(Properties.unwantedWords(), String.class));

    private static List<String> findRhymePartsNotInIndex(List<String> lines, Set<String> allKnownParts) {
        Set<String> foundParts = Sets.newHashSet();
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String wordUnprepared : words) {
                String word = StringRhymeUtils.prepareWordForComparison(wordUnprepared.toUpperCase());
                if (!UNWANTED_WORDS.contains(word) && !allKnownParts.contains(word)) {
                    //client also needs to trim punctuation
                    foundParts.add(Strings.trimPunctuation(wordUnprepared.toUpperCase()));
                }
            }
        }
        return new ArrayList<String>(foundParts);
    }

    public void addArtists(HierarchicalIndexerResult result) {
        addArtists(result.indexJ(), result.getAllRhymePartsJ());
    }

    private static class SongRhymeKey {
        public final List<String> lines;
        public final List<String> parts;

        private SongRhymeKey(List<String> lines, List<String> parts) {
            this.lines = lines;
            this.parts = parts;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SongRhymeKey rhymeKey = (SongRhymeKey) o;

            if (lines != null ? !lines.equals(rhymeKey.lines) : rhymeKey.lines != null) return false;
            if (parts != null ? !parts.equals(rhymeKey.parts) : rhymeKey.parts != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = lines != null ? lines.hashCode() : 0;
            result = 31 * result + (parts != null ? parts.hashCode() : 0);
            return result;
        }
    }

    private SongRhymeKey songRhymeKey(List<String> lines, List<String> parts) {
        return new SongRhymeKey(lines, parts);
    }

    public void addArtists(Map<String, ArtistNode> artists, Set<String> allKnownParts) {
        DuplicationFinder duplicationFinder = new DuplicationFinder();
        ResultCollector resultMap = new ResultCollector(MAX_NUMBER_OF_ENTRIES);
        Multiset<String> counter = HashMultiset.create();

        for (ArtistNode artistNode : artists.values()) {
            System.out.println("adding artist " + artistNode.name());
            Artist artist = artistFactory.create(artistNode);
            for (AlbumNode albumNode : artistNode.childrenJList()) {
                Album album = albumFactory.create(albumNode, artist);
                List<SongNode> songNodeList = albumNode.childrenJList();
                for (SongNode songNode : songNodeList) {
                    Map<SongRhymeKey, Rhyme> songRhymes = new HashMap<SongRhymeKey, Rhyme>();
                    Song song = songFactory.create(songNode, album);
                    List<RhymeLeaf> rhymeLeafList = songNode.rhymesJList();
                    for (RhymeLeaf rhymeLeaf : rhymeLeafList) {
                        List<String> rhymeLines = rhymeLeaf.linesJList();
                        SongRhymeKey rhymeKey = songRhymeKey(rhymeLines, rhymeLeaf.partsJList());

                        if (duplicationFinder.isSimilarToAnyOtherRhymes(rhymeLines, resultMap.get(rhymeLeaf.word()))) {
                            continue;
                        }

                        if (!songRhymes.containsKey(rhymeKey)) {
                            List<String> otherRhymeParts = findRhymePartsNotInIndex(rhymeLines, allKnownParts);
                            Rhyme value = rhymeFactory.create(rhymeLeaf, otherRhymeParts);
                            songRhymes.put(rhymeKey, value);
                        }

                        RhymePart part = rhymePartFactory.create(songRhymes.get(rhymeKey), song, rhymeLeaf);
                        resultMap.put(part);
                    }
                }
            }
        }

        commitMap(resultMap);
        printStats(counter);
    }

    private static class ResultCollector {

        private final int maxNumberOfEntries;
        public final Multimap<String, RhymePart> resultMap = HashMultimap.create();

        public ResultCollector(int maxNumberOfEntries) {
            this.maxNumberOfEntries = maxNumberOfEntries;
        }

        public void put(RhymePart part) {
            resultMap.put(part.getWord(), part);

            List<RhymePart> list = new ArrayList<RhymePart>(resultMap.get(part.getWord()));

            if (list.size() >= maxNumberOfEntries) {
                addAndRemoveLowest(part, list);
            }
        }

        public Collection<RhymePart> get(String word) {
            return resultMap.get(word);
        }

        public Collection<RhymePart> values() {
            return resultMap.values();
        }

        private void addAndRemoveLowest(RhymePart part, List<RhymePart> list) {
            Collections.sort(list, new Comparator<RhymePart>() {
                public int compare(RhymePart o1, RhymePart o2) {
                    return o1.getRhymeScore() - o2.getRhymeScore();
                }
            });

            list.remove(0);
            resultMap.removeAll(part.getWord());
            resultMap.putAll(part.getWord(), list);
        }
    }

    private static <T> List<T> toJavaList(Object scalaList, Class<T> clazz) {
        return ScalaConversions.toJavaList((scala.collection.immutable.List<T>) scalaList);
    }

    private void commitMap(ResultCollector map) {
        Session session = newSession();
        Transaction transaction = session.beginTransaction();

        for (RhymePart part : map.values()) {
            System.out.println(format("RhymeTimeDao.commitMap saving part %s, %s, %swq", part.getWord(), part.getRhyme().getRhymeLines(), part.getRhyme().getRhymeParts()));
            session.save(part);
        }

        System.out.println("RhymeTimeDao.addArtists committing...");
        transaction.commit();
        session.close();
    }

    private void printStats(Multiset<String> counter) {
        Set<Multiset.Entry<String>> entries = counter.entrySet();
        for (Multiset.Entry<String> entry : entries) {
            System.out.println("RhymeTimeDao.addArtists " + entry.getElement() + ": " + entry.getCount());
        }
    }
}
