package introsde.assignment.dao;

import introsde.assignment.model.Person;
import introsde.assignment.persistence.PersistenceManager;
import introsde.assignment.to.PersonTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityDAO {

    public static List<PersonTO> listPeople() {
        String query = "SELECT p FROM Person p";
        List<Person> people = PersistenceManager.instance.listResultQuery(query);
        return people.stream().map(ObjectConverter::toTO).collect(Collectors.toList());
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

    public static void updatePerson(Long personId, PersonTO oldpersonTO) {
        String query = "UPDATE Person p SET p.firstname = :arg1, p.lastname = :arg2 WHERE p.id = :arg3";
        Map<String, Object> params = new HashMap<>();
        params.put("arg1", oldpersonTO.getFirstname());
        params.put("arg2", oldpersonTO.getLastname());
        params.put("arg3", personId);
        PersistenceManager.instance.updateQuery(query, params);
    }

    public static List<String> listMeasure() {
        String query = "SELECT DISTINCT t.measureType FROM Measure t";
        return PersistenceManager.instance.listResultQuery(query);
    }


        /*

    public static void addMeasure(Long personId, introsde.assignment.to.MeasureTO newMeasureTO) {
        PersistenceManager.instance.runViaProxy(entityManagerProxy -> {
            String query = "SELECT p FROM Person p WHERE p.id=:arg1";
            Map<String, Object> params = new HashMap<>();
            params.put("arg1", personId);
            PersonTO person = (PersonTO) entityManagerProxy.singleResultQuery(query, params);

            Predicate<? super MeasureTO> sameMeasure =
                    (Predicate<MeasureTO>) m ->
                            m.getMeasureType().equals(newMeasure.getMeasureType());

            List<MeasureTO> currentMeasures = person.getCurrentHealth();

            MeasureTO oldMeasure = currentMeasures.stream().filter(sameMeasure).findFirst().orElse(null);
            if (oldMeasure != null) {
                currentMeasures.remove(oldMeasure);
                person.getHealthHistory().add(oldMeasure);
            }

            currentMeasures.add(newMeasure);
            entityManagerProxy.persist(person);
            return null;
        });
    }

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
