package introsde.assignment.persistence;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

public enum PersistenceManager {
    instance;

    private static final String PERSISTENCE_UNIT_NAME = "introsde-jpa";

    private EntityManagerFactory entityManagerFactory;

    PersistenceManager() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    <T> T merge(EntityManager entityManager, T object) {
        System.out.println("\nGoing to merge: " + object + "\n");
        return entityManager.merge(object);
    }

    <T> void persist(EntityManager entityManager, T object) {
        System.out.println("\nGoing to persist: " + object + "\n");
        entityManager.persist(object);
    }

    public <T> void persist(T object) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        persist(entityManager, object);
        transaction.commit();
    }

    public <T> void remove(T object) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        System.out.println("\nGoing to remove: " + object + "\n");
        T mergedObject = entityManager.merge(object);
        entityManager.remove(mergedObject);
        transaction.commit();
    }

    private Query buildQuery(EntityManager entityManager, String query, Map<String, Object> parameters) {
        Query q = entityManager.createQuery(query);
        for (Map.Entry<String, Object> param : parameters.entrySet()) {
            q.setParameter(param.getKey(), param.getValue());
        }
        return q;
    }

    public List listResultQuery(String query) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        System.out.println("\nGoing to run query: " + query + "\n");
        List result = entityManager.createQuery(query).getResultList();
        transaction.commit();
        return result;
    }

    public Object singleResultQuery(EntityManager entityManager, String query, Map<String, Object> parameters) {
        System.out.println("\nGoing to run query: " + query + "\n params: " + parameters + "\n");
        return buildQuery(entityManager, query, parameters).getSingleResult();
    }

    public Object singleResultQuery(String query, Map<String, Object> parameters) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Object result = singleResultQuery(entityManager, query, parameters);
        transaction.commit();
        return result;
    }

    public Integer updateQuery(String query) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        System.out.println("\nGoing to run update query: " + query + "\n");
        int result = entityManager.createQuery(query).executeUpdate();
        transaction.commit();
        return result;
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
