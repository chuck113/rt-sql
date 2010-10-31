package com.rt.hibernate.dto;

import com.google.common.collect.Lists;
import com.rt.hibernate.dto.coredata.*;
import com.rt.indexing.*;
//import static com.rt.util.ScalaConversions.*;

import com.rt.util.Logger;
import com.rt.util.ScalaConversions;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class HibernateMain {

    private final RhymeTimeDao dao;

    public HibernateMain(){
        dao = new RhymeTimeDao(
                new RhymePartFactoryImpl(),
                new SongFactoryImpl(),
                new AlbumFactoryImpl(),
                new ArtistFactoryImpl(),
                new RhymeFactoryImpl());
    }

    public static void main(String[] args) {
       HibernateMain hibernateMain = new HibernateMain();
       //hibernateMain.testDb();

       hibernateMain.copyEmptyDb();

       hibernateMain.index();
       hibernateMain.testFind();

       hibernateMain.copyCreatedDb();
    }

    private void copyEmptyDb(){
        String emtpyDbName = "rhymeTime-ORIGINAL.sqlite";
        String targetDbName = "rhymeTime.sqlite";
        String emptyDbFolder = "C:\\data\\projects\\rapAttack\\rapAttackSQL";
        String targetDbFolder = "C:\\data\\projects\\rapAttack";
        File emptyDb = new File(emptyDbFolder, emtpyDbName);
        File targetDb = new File(targetDbFolder, targetDbName);
        try {
            System.out.println("HibernateMain.copyEmptyDb targetDb = "+targetDb);
            if(targetDb.exists()){
                targetDb.delete();
            }
            FileUtils.copyFile(emptyDb, targetDb);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void copyCreatedDb(){
        String targetDbName = "rhymeTime-"+System.currentTimeMillis()+".sqlite";
        String createdDbName = "rhymeTime.sqlite";
        String emptyDbFolder = "C:\\data\\projects\\rapAttack\\rapAttackSQL\\sqlite-dbs";
        String createdDbFolder = "C:\\data\\projects\\rapAttack";
        File targetDb = new File(emptyDbFolder, targetDbName);
        File createdDb = new File(createdDbFolder, createdDbName);
        try {
            //new File(targetDbName).createNewFile();
            FileUtils.copyFile(createdDb, targetDb);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void testFind(){
        List<RhymePart> rhymes = dao.findRhymes("SINGING");
        System.out.println("HibernateMain.testFind found "+rhymes.size());
        for (RhymePart rhyme : rhymes) {
            System.out.println("HibernateMain.main rhyme: "+rhyme);
            System.out.println("HibernateMain.main rhyme: "+rhyme.getSong().getTitle());
        }
    }

    private List<String> allFolderNamesInOhhlaFolder(){
        File ohhlaFolder = new File("C:\\data\\projects\\rapAttack\\rapAttack\\olhha");
        return Arrays.asList(ohhlaFolder.list());
    }

    private List<String> allFolderNamesInTestFolder(String testFolder){
        File ohhlaFolder = new File(ARTIST_TEST_FOLDER + "\\"+testFolder);
        return Arrays.asList(ohhlaFolder.list());
    }

    private static final String ARTIST_TEST_FOLDER = "C:\\data\\projects\\rapAttack\\rapAttack\\olhha-testdata";

    public void perist(HierarchicalIndexerResult result){
       copyEmptyDb();

       //dao.addArtists(ScalaConversions.toJavaMap(result.index()), ScalaConversions.toJavaSet(result.foundWords()));
        dao.addArtists(result);

       testFind();

       copyCreatedDb();
    }

    private void index(){
        //System.out.println("HibernateMain.index " + allFolderNamesInOhhlaFolder().size());
        HierarchicalIndexer indexer = new HierarchicalIndexer();

        //List artists = Lists.newArrayList("METHOD_MAN");
        //List artists = Lists.newArrayList(allFolderNamesInTestFolder("methodman-test"));
        List artists = Lists.newArrayList(allFolderNamesInTestFolder("atcq-test"));

        //List artists = Lists.newArrayList(allFolderNamesInOhhlaFolder());

//        List artists = Lists.newArrayList("NOTORIOUS_BIG");
        //List artists = Lists.newArrayList("THE_GENIUSGZA");
        //List artists = Lists.newArrayList("THE_GENIUSGZA", "NOTORIOUS_BIG", "GANGSTARR", "KRSONE", "MADVILLAIN");
        //List artists = Lists.newArrayList("THE_GENIUSGZA", "PUBLIC_ENEMY", "NAS", "BEASTIE_BOYS", "JAYZ", "ICE_CUBE", "NOTORIOUS_BIG", "COMMON", "ATCQ",
        //     "CYPRESS_HILL", "EMINEM", "DR_DRE", "ERIC_B_AND_RAKIM", "GANGSTARR", "KRSONE", "MADVILLAIN");



//        List artists = Lists.newArrayList("THE_GENIUSGZA", "PUBLIC_ENEMY", "NAS", "BEASTIE_BOYS", "JAYZ", "ICE_CUBE",
//                "NOTORIOUS_BIG", "COMMON", "ATCQ", "CYPRESS_HILL", "EMINEM", "DR_DRE", "ERIC_B_AND_RAKIM", "GANGSTARR",
//                "KRSONE", "MADVILLAIN", "BIG_DADDY_KANE", "BDP", "BUSTA_RHYMES", "CANIBUS", "ONYX", "DE_LA_SOUL",
//                "DITC", "DR_OCTAGON", "EPMD", "FUGEES", "GHOSTFACE_KILLAH", "METHOD_MAN", "MISSY", "MOBB_DEEP",
//                "NAUGHTY_BY_NATURE", "NICE_AND_SMOOTH", "OUTKAST", "PHARCYDE", "REDMAN", "RUNDMC", "SLICK_RICK",
//                "SNOOP_DOGG","THE_ROOTS", "ULTRAMAGNETIC_MCS" );

        //Logger.progress("building index hierarchy");
        //HierarchicalIndexerResult result = indexer.makeArtistHierarchyWithAllWords(ScalaConversions.toScalaList(artists), ARTIST_TEST_FOLDER+"\\"+"atcq-test");
        //HierarchicalIndexerResult result = indexer.makeArtistHierarchyWithAllWords(toScalaList(artists));

        //indexer.serializeResult(result, Constants.serializedHierarchyResultFile("METHOD_MAN_test"));
        //indexer.serializeResult(result, Constants.serializedHierarchyResultFile("40Artists"));
        //indexer.serializeResult(result, Constants.serializedHierarchyResultFile("all"));

        //HierarchicalIndexerResult result = indexer.deserializeResult(Constants.serializedHierarchyResultFile("BIG_L"));
        //HierarchicalIndexerResult result = indexer.deserializeResult(Constants.serializedHierarchyResultFile("all"));
        //Logger.progress("built index hierarchy");
//        List<RhymeLeaf> list = new ArrayList<RhymeLeaf>(ScalaConversions.toJavaList(result.bestRhymes()));
//
//        Collections.sort(list, new Comparator<RhymeLeaf>(){
//            public int compare(RhymeLeaf o1, RhymeLeaf o2) {
//                return o1.rating() - o2.rating();
//            }
//        });
//
//        for (RhymeLeaf rhymeLeaf : list) {
//            if(rhymeLeaf.rating() > 0)
//                System.out.println("HibernateMain.index score:"+rhymeLeaf.rating()+", "+rhymeLeaf.lines()+", "+rhymeLeaf.parts()+" "+rhymeLeaf.word());
//        }

        //Map<String, ArtistNode> map = ScalaConversions.toJavaMap(HierarchicalIndexerObj.createArtistIndex(ScalaConversions.toScalaList(artists)));
       // dao.addArtists(ScalaConversions.toJavaMap(result.index()), ScalaConversions.toJavaSet(result.foundWords()));
    }


//    private void testDb(){
//        //rhymeTimeIPhonePrototypeCopy.sqlite
//        Configuration cfg = new Configuration().configure("hibernate.sqlite.cfg.xml");
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        Artist artist = new ArtistFactoryImpl().create("NAS");
//        Album album = new AlbumFactoryImpl().create(artist, "Illmatic");
//
//        Song song = new SongFactoryImpl().create("Illmatic", album);
//        session.persist(song);
//        System.out.println("HibernateMain.testDb song id is "+song.getId());
//        RhymePart part = new RhymePartFactoryImpl().create(
//                song,
//                "gold",
//                Lists.newArrayList("need gold etcc", "fold lead etc etc"),
//                Lists.newArrayList("gold", "fold"),
//                Lists.newArrayList("need", "lead"),0
//                );
//
//        session.persist(part);
//        transaction.commit();
//
//        session.close();
//        sessionFactory.close();
//
////        SessionFactory sessionFactory2 = cfg.buildSessionFactory();
////        Session session1 = sessionFactory2.openSession();
////        Transaction transaction1 = session1.beginTransaction();
////
////        Query query = session1.createQuery("from RhymePartImpl p where p.word = :wordQuery").
////                setString("wordQuery", "gold");
////
////        RhymePartImpl foundPart = (RhymePartImpl)query.list().get(0);
////        System.out.println("HibernateMain.createDb qery result is :" + foundPart);
////        System.out.println("HibernateMain.createDb lines are: "+foundPart.rhymeLinesDeserialized());
////        System.out.println("HibernateMain.createDb song is: "+foundPart.getSong().getTitle());
//
//    }
}
