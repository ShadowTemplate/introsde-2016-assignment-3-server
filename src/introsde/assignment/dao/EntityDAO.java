package introsde.assignment.dao;

import introsde.assignment.model.Measure;
import introsde.assignment.model.Person;
import introsde.assignment.persistence.PersistenceManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EntityDAO {

    public static List<Person> listPeople() {
        String query = "SELECT p FROM Person p";
        return PersistenceManager.instance.listResultQuery(query);
    }

    public static Person getPerson(Long personId) {
        String query = "SELECT p FROM Person p WHERE p.id=:arg1";
        Map<String, Object> params = new HashMap<>();
        params.put("arg1", personId);
        return (Person) PersistenceManager.instance.singleResultQuery(query, params);
    }

    public static Person putPerson(Person person) {
        PersistenceManager.instance.persist(person);
        return person;
    }

    public static void deletePerson(Person person) {
        PersistenceManager.instance.remove(person);
    }

    public static void updatePerson(Long personId, Person oldperson) {
        String query = "UPDATE Person p SET p.firstname = :arg1, p.lastname = :arg2 WHERE p.id = :arg3";
        Map<String, Object> params = new HashMap<>();
        params.put("arg1", oldperson.getFirstname());
        params.put("arg2", oldperson.getLastname());
        params.put("arg3", personId);
        PersistenceManager.instance.updateQuery(query, params);
    }

    public static List<String> listMeasure() {
        String query = "SELECT DISTINCT t.measureType FROM Measure t";
        return PersistenceManager.instance.listResultQuery(query);
    }

    public static void addMeasure(Long personId, Measure newMeasure) {
        PersistenceManager.instance.runViaProxy(entityManagerProxy -> {
            String query = "SELECT p FROM Person p WHERE p.id=:arg1";
            Map<String, Object> params = new HashMap<>();
            params.put("arg1", personId);
            Person person = (Person) entityManagerProxy.singleResultQuery(query, params);

            Predicate<? super Measure> sameMeasure =
                    (Predicate<Measure>) m ->
                            m.getMeasureType().equals(newMeasure.getMeasureType());

            List<Measure> currentMeasures = person.getCurrentHealth();

            Measure oldMeasure = currentMeasures.stream().filter(sameMeasure).findFirst().orElse(null);
            if (oldMeasure != null) {
                currentMeasures.remove(oldMeasure);
                person.getHealthHistory().add(oldMeasure);
            }

            currentMeasures.add(newMeasure);
            entityManagerProxy.persist(person);
            return null;
        });
    }

        /*

    public static void updateMeasureType(Double measureId, MeasureType measureTO) {
        String query = "UPDATE MeasureType m SET m.value = :arg1, m.created = :arg2 WHERE m.mid = :arg3";
        Map<String, Object> params = new HashMap<>();
        params.put("arg1", measureTO.getValue());
        params.put("arg2", measureTO.getCreated());
        params.put("arg3", measureId);
        PersistenceManager.instance.updateQuery(query, params);
    }



*/

    public static void initDatabase() {
        PersistenceManager.instance.updateQuery("DELETE FROM Measure m");
        PersistenceManager.instance.updateQuery("DELETE FROM Person p");
    }
}
