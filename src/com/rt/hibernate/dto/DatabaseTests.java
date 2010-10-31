package com.rt.hibernate.dto;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.util.List;

public class DatabaseTests {

    private Session session;

    public void testFindsAnAliasedWord() {
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from CoreDataRhymePart r where r.word = :word");
        //Query query = session.createQuery("from CoreDataRhymePart c");

        List list = query
                .setString("word", "SUCKIN'")
                .list();

        System.out.println("list.size() = " + list.size());
    }

    public static void main(String[] args) {
        DatabaseTests databaseTests = new DatabaseTests("rhymeTime-1280519224632.sqlite");
        databaseTests.testFindsAnAliasedWord();
    }

    public DatabaseTests(String sqliteFile) {

        Configuration cfg = new Configuration();
//        cfg.setProperty("connection.url", "jdbc:sqlite:" + sqliteFile);
//        cfg.setProperty("dialect", "com.rt.hibernate.sqlite.SQLiteDialect");
//        cfg.setProperty("connection.driver_class", "org.sqlite.JDBC");
//        cfg.setProperty("show_sq", "true");

        SessionFactory sessionFactory = cfg.configure("hibernate.sqlite.cfg.xml").buildSessionFactory();

        session = sessionFactory.openSession();
    }
}

