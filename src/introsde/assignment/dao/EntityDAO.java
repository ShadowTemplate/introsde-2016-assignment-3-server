package introsde.assignment.dao;

import introsde.assignment.model.Measure;
import introsde.assignment.model.Person;
import introsde.assignment.persistence.PersistenceManager;
import introsde.assignment.to.MeasureTO;
import introsde.assignment.to.PersonTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityDAO {

    public static List<PersonTO> listPeople() {
        String query = "SELECT p FROM Person p";
        List<Person> people = PersistenceManager.instance.listResultQuery(query);
        List<PersonTO> personTOsList = new ArrayList<>();
        for (Person person : people) {
            personTOsList.add(ObjectConverter.toTO(person));
        }
        return personTOsList;
    }

    public static PersonTO getPerson(Long personId) {
        String query = "SELECT p FROM Person p WHERE p.id=:arg1";
        Map<String, Object> params = new HashMap<>();
        params.put("arg1", personId);
        return ObjectConverter.toTO((Person) PersistenceManager.instance.singleResultQuery(query, params));
    }

    public static PersonTO putPerson(PersonTO personTO) {
        Person person = ObjectConverter.toModel(personTO);
        PersistenceManager.instance.persist(person);
        return ObjectConverter.toTO(person);
    }

    public static void deletePerson(PersonTO personTOTO) {
        PersistenceManager.instance.remove(ObjectConverter.toModel(personTOTO));
    }

    public static PersonTO updatePerson(Long personId, PersonTO oldpersonTO) {
        EntityManager entityManager = PersistenceManager.instance.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        System.out.println("\nRunning function\n");
        Person person = ObjectConverter.toModel(oldpersonTO);
        person.setId(personId);
        person = entityManager.merge(person);
        person.setFirstname(oldpersonTO.getFirstname());
        person.setLastname(oldpersonTO.getLastname());
        entityManager.persist(person);
        transaction.commit();
        return ObjectConverter.toTO(person);
    }

    public static List<MeasureTO> getPersonMeasureHistory(Long personId, String measureType) {
        List<MeasureTO> healthHistory = EntityDAO.getPerson(personId).getHealthHistory();
        List<MeasureTO> measures = new ArrayList<>();
        for (MeasureTO measureTO : healthHistory) {
            if (measureTO.getMeasureType().equals(measureType)) {
                measures.add(measureTO);
            }
        }
        return measures;
    }

    public static List<String> listMeasure() {
        String query = "SELECT DISTINCT t.measureType FROM Measure t";
        return PersistenceManager.instance.listResultQuery(query);
    }

    public static MeasureTO getPersonMeasure(Long personId, String measureType, Long measureId) {
        List<MeasureTO> currentHealth = EntityDAO.getPerson(personId).getCurrentHealth();
        for (MeasureTO measureTO : currentHealth) {
            if (measureTO.getMid().equals(measureId)) {
                return measureTO;
            }
        }
        return null;
    }

    public static MeasureTO addMeasure(Long personId, introsde.assignment.to.MeasureTO newMeasureTO) {
        EntityManager entityManager = PersistenceManager.instance.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        System.out.println("\nRunning function\n");

        String query = "SELECT p FROM Person p WHERE p.id=:arg1";
        Map<String, Object> params = new HashMap<>();
        params.put("arg1", personId);
        Person person = (Person) PersistenceManager.instance.singleResultQuery(query, params);
        List<Measure> currentMeasures = person.getCurrentHealth();
        Measure oldMeasure = null;
        for (Measure m : currentMeasures) {
            if (m.getMeasureType().equals(newMeasureTO.getMeasureType())) {
                oldMeasure = m;
            }
        }
        if (oldMeasure != null) {
            currentMeasures.remove(oldMeasure);
            person.getHealthHistory().add(oldMeasure);
        }

        currentMeasures.add(ObjectConverter.toModel(newMeasureTO));
        person = entityManager.merge(person);
        transaction.commit();

        oldMeasure = null;
        for (Measure m : person.getCurrentHealth()) {
            if (m.getMeasureType().equals(newMeasureTO.getMeasureType())) {
                oldMeasure = m;
            }
        }
        return oldMeasure == null ? null : ObjectConverter.toTO(oldMeasure);
    }

    public static MeasureTO updateMeasure(Long personId, MeasureTO measureTO) {
        EntityManager entityManager = PersistenceManager.instance.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        System.out.println("\nRunning function\n");
        String query = "SELECT p FROM Person p WHERE p.id=:arg1";
        Map<String, Object> params = new HashMap<>();
        params.put("arg1", personId);
        Person person = (Person) PersistenceManager.instance.singleResultQuery(entityManager, query, params);
        Measure measure = null;
        for (Measure m : person.getCurrentHealth()) {
            if (m.getMid().equals(measureTO.getMid())) {
                measure = m;
            }
        }
        if (measure != null) {
            measure.setMeasureType(measureTO.getMeasureType());
            measure.setMeasureValue(measureTO.getMeasureValue());
            measure.setMeasureValueType(measureTO.getMeasureValueType());
            measure.setDateRegistered(measureTO.getDateRegistered());
        }
        person = entityManager.merge(person);
        entityManager.persist(person);
        transaction.commit();
        return measure == null ? null : ObjectConverter.toTO(measure);
    }

    public static void initDatabase() {
        PersistenceManager.instance.updateQuery("DELETE FROM Measure m");
        PersistenceManager.instance.updateQuery("DELETE FROM Person p");
    }

    public static void main(String[] args) {
        System.out.println(listPeople().size());
        initDatabase();
        System.out.println(listPeople().size());
    }
}
