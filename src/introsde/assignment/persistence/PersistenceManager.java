package introsde.assignment.persistence;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

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

    <T> void persist(EntityManager entityManager, T object) {
        System.out.println("\nGoing to persist: " + object + "\n");
        entityManager.persist(object);
    }

    public <T> void persist(T object) {
        runInTransaction(entityManager -> {
            persist(entityManager, object);
        });
    }

    public <T> void remove(T object) {
        runInTransaction(entityManager -> {
            System.out.println("\nGoing to remove: " + object + "\n");
            T mergedObject = entityManager.merge(object);
            entityManager.remove(mergedObject);
        });
    }

    private Query buildQuery(EntityManager entityManager, String query, Map<String, Object> parameters) {
        Query q = entityManager.createQuery(query);
        for (Map.Entry<String, Object> param : parameters.entrySet()) {
            q.setParameter(param.getKey(), param.getValue());
        }
        return q;
    }

    public List listResultQuery(String query) {
        return runInTransaction(entityManager -> {
            System.out.println("\nGoing to run query: " + query + "\n");
            return entityManager.createQuery(query).getResultList();
        });
    }

    public List listResultQuery(String query, Map<String, Object> parameters) {
        return runInTransaction(entityManager -> {
            System.out.println("\nGoing to run query: " + query + "\n params: " + parameters + "\n");
            return buildQuery(entityManager, query, parameters).getResultList();
        });
    }

    public Object singleResultQuery(String query) {
        return runInTransaction(entityManager -> {
            System.out.println("\nGoing to run query: " + query + "\n");
            return entityManager.createQuery(query).getSingleResult();
        });
    }

    Object singleResultQuery(EntityManager entityManager, String query, Map<String, Object> parameters) {
        System.out.println("\nGoing to run query: " + query + "\n params: " + parameters + "\n");
        return buildQuery(entityManager, query, parameters).getSingleResult();
    }

    public Object singleResultQuery(String query, Map<String, Object> parameters) {
        return runInTransaction(entityManager -> {
            return singleResultQuery(entityManager, query, parameters);
        });
    }

    public Integer updateQuery(String query) {
        return runInTransaction(entityManager -> {
            System.out.println("\nGoing to run update query: " + query + "\n");
            return entityManager.createQuery(query).executeUpdate();
        });
    }

    public Integer updateQuery(String query, Map<String, Object> parameters) {
        return runInTransaction(entityManager -> {
            System.out.println("\nGoing to run update query: " + query + "\n params: " + parameters + "\n");
            return buildQuery(entityManager, query, parameters).executeUpdate();
        });
    }

    public <T> T runViaProxy(Function<EntityManagerProxy, T> function) {
        return runInTransaction(entityManager -> {
            System.out.println("\nRunning function\n");
            return function.apply(new EntityManagerProxy(entityManager));
        });
    }

    private void runInTransaction(Consumer<EntityManager> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        function.accept(entityManager);
        transaction.commit();
    }

    private <T> T runInTransaction(Function<EntityManager, T> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        T result = function.apply(entityManager);
        transaction.commit();
        return result;
    }
}
