package gr.aueb.cf.schoolapp.service.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * A utility class for managing Java Persistence API (JPA) operations.
 */
public class JPAHelper {
    private static EntityManagerFactory emf;
    protected static ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    private JPAHelper() {}

    /**
     * Get the EntityManagerFactory.
     *
     * @return The EntityManagerFactory instance for JPA operations.
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        if ((emf == null) || (!emf.isOpen())) {
            emf = Persistence.createEntityManagerFactory("teachers22PU");
        }

        return emf;
    }

    /**
     * Get the EntityManager associated with the current thread.
     *
     * @return The EntityManager instance associated with the current thread.
     */
    public static EntityManager getEntityManager() {
        EntityManager em = threadLocal.get();
        if ((em == null) || (!em.isOpen())) {
            em = getEntityManagerFactory().createEntityManager();
            threadLocal.set(em);
        }

        return em;
    }

    /**
     * Close the EntityManager associated with the current thread.
     */
    public static void closeEntityManager() {
        getEntityManager().close();
    }

    /**
     * Begin a transaction using the current EntityManager.
     */
    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    /**
     * Commit the transaction using the current EntityManager.
     */
    public static void commitTransaction() {
        getEntityManager().getTransaction().commit();
    }

    /**
     * Roll back the transaction using the current EntityManager.
     */
    public static void rollbackTransaction() {
        getEntityManager().getTransaction().rollback();
    }

    /**
     * Close the EntityManagerFactory.
     */
    public static void closeEMF() {
        emf.close();
    }
}
