package org.ngarcia.webapp.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        //ejemplo está definido en persistence.xml
        return Persistence.createEntityManagerFactory("ejemploJpa");
    }

    public static EntityManager getEntityManeger() {
        return  entityManagerFactory.createEntityManager();
    }
}
