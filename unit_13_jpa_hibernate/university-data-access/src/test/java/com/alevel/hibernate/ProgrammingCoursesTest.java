package com.alevel.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManager;


public abstract class ProgrammingCoursesTest {
    Session session;
    static EntityManager entityManager;
    static SessionFactory sessionFactory;

    @BeforeAll
    static void setupFactories() {
        var config = new Configuration().configure();
        sessionFactory = config.buildSessionFactory();
        entityManager = sessionFactory.createEntityManager();
    }

    @BeforeEach
    void setupSession() {
        session = sessionFactory.openSession();
    }

    @AfterEach
    void closeSession() {
        session.close();
    }

    @AfterAll
    static void closeFactories() {
        sessionFactory.close();
    }
}
