package introsde.assignment.dao;

import introsde.assignment.to.MeasureTO;
import introsde.assignment.to.PersonTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectConverter {

    public static MeasureTO toTO(introsde.assignment.model.Measure measure) {
        MeasureTO measureTO = new MeasureTO();
        measureTO.setMid(measure.getMid());
        measureTO.setDateRegistered(measure.getDateRegistered());
        measureTO.setMeasureType(measure.getMeasureType());
        measureTO.setMeasureValue(measure.getMeasureValue());
        measureTO.setMeasureValueType(measure.getMeasureValueType());
        return measureTO;
    }

    public static PersonTO toTO(introsde.assignment.model.Person person) {
        PersonTO personTO = new PersonTO();
        personTO.setId(person.getId());
        personTO.setFirstname(person.getFirstname());
        personTO.setLastname(person.getLastname());
        //personTO.setCurrentHealth(person.getCurrentHealth().stream().map(ObjectConverter::toTO).toArray(introsde.assignment.to.MeasureTO[]::new));
        //personTO.setHealthHistory(person.getHealthHistory().stream().map(ObjectConverter::toTO).toArray(introsde.assignment.to.MeasureTO[]::new));
        personTO.setCurrentHealth(person.getCurrentHealth().stream().map(ObjectConverter::toTO).collect(Collectors.toList()));
        personTO.setHealthHistory(person.getHealthHistory().stream().map(ObjectConverter::toTO).collect(Collectors.toList()));
        return personTO;
    }

    public static introsde.assignment.model.Measure toModel(MeasureTO measureTO,
                                                            introsde.assignment.model.Person person) {
        introsde.assignment.model.Measure measure = new introsde.assignment.model.Measure();
        measure.setMid(measureTO.getMid());
        measure.setDateRegistered(measureTO.getDateRegistered());
        measure.setMeasureType(measureTO.getMeasureType());
        measure.setMeasureValue(measureTO.getMeasureValue());
        measure.setMeasureValueType(measureTO.getMeasureValueType());
        //measure.setPerson(person);
        return measure;
    }

    public static introsde.assignment.model.Person toModel(PersonTO personTO) {
        introsde.assignment.model.Person person = new introsde.assignment.model.Person();
        person.setId(personTO.getId());
        person.setFirstname(personTO.getFirstname());
        person.setLastname(personTO.getLastname());
        //person.setCurrentHealth(Arrays.stream(personTO.getCurrentHealth()).map(measure -> toModel(measure, person)).collect(Collectors.toList()));
        //person.setHealthHistory(Arrays.stream(personTO.getHealthHistory()).map(measure -> toModel(measure, person)).collect(Collectors.toList()));

        List<MeasureTO> currentHealth = personTO.getCurrentHealth();
        if(currentHealth != null) {
            person.setCurrentHealth(currentHealth.stream().map(measure -> toModel(measure, person)).collect(Collectors.toList()));
        } else {
            person.setCurrentHealth(new ArrayList<>());
        }

        List<MeasureTO> healthHistory = personTO.getHealthHistory();
        if (healthHistory != null) {
            person.setHealthHistory(healthHistory.stream().map(measureTO -> toModel(measureTO, person)).collect(Collectors.toList()));
        } else {
            person.setHealthHistory(new ArrayList<>());
        }
        return person;
    }
}