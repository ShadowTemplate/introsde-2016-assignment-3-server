package introsde.assignment.dao;

import introsde.assignment.model.Measure;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ObjectConverter {

    public static introsde.assignment.to.Measure toTO(introsde.assignment.model.Measure measure) {
        introsde.assignment.to.Measure measureTO = new introsde.assignment.to.Measure();
        measureTO.setMid(measure.getMid());
        measureTO.setDateRegistered(measure.getDateRegistered());
        measureTO.setMeasureType(measure.getMeasureType());
        measureTO.setMeasureValue(measure.getMeasureValue());
        measureTO.setMeasureValueType(measure.getMeasureValueType());
        return measureTO;
    }

    public static introsde.assignment.to.Person toTO(introsde.assignment.model.Person person) {
        introsde.assignment.to.Person personTO = new introsde.assignment.to.Person();
        personTO.setId(person.getId());
        personTO.setFirstname(person.getFirstname());
        personTO.setLastname(person.getLastname());
        //personTO.setCurrentHealth(person.getCurrentHealth().stream().map(ObjectConverter::toTO).toArray(introsde.assignment.to.Measure[]::new));
        //personTO.setHealthHistory(person.getHealthHistory().stream().map(ObjectConverter::toTO).toArray(introsde.assignment.to.Measure[]::new));
        personTO.setCurrentHealth(person.getCurrentHealth().stream().map(ObjectConverter::toTO).collect(Collectors.toList()));
        personTO.setHealthHistory(person.getHealthHistory().stream().map(ObjectConverter::toTO).collect(Collectors.toList()));
        return personTO;
    }

    public static introsde.assignment.model.Measure toModel(introsde.assignment.to.Measure measureTO,
                                                            introsde.assignment.model.Person person) {
        introsde.assignment.model.Measure measure = new introsde.assignment.model.Measure();
        measure.setMid(measureTO.getMid());
        measure.setDateRegistered(measureTO.getDateRegistered());
        measure.setMeasureType(measureTO.getMeasureType());
        measure.setMeasureValue(measureTO.getMeasureValue());
        measure.setMeasureValueType(measureTO.getMeasureValueType());
        measure.setPerson(person);
        return measure;
    }

    public static introsde.assignment.model.Person toModel(introsde.assignment.to.Person personTO) {
        introsde.assignment.model.Person person = new introsde.assignment.model.Person();
        person.setId(personTO.getId());
        person.setFirstname(personTO.getFirstname());
        person.setLastname(personTO.getLastname());
        //person.setCurrentHealth(Arrays.stream(personTO.getCurrentHealth()).map(measure -> toModel(measure, person)).collect(Collectors.toList()));
        //person.setHealthHistory(Arrays.stream(personTO.getHealthHistory()).map(measure -> toModel(measure, person)).collect(Collectors.toList()));
        person.setCurrentHealth(personTO.getCurrentHealth().stream().map(measure -> toModel(measure, person)).collect(Collectors.toList()));
        person.setHealthHistory(personTO.getHealthHistory().stream().map(measure -> toModel(measure, person)).collect(Collectors.toList()));
        return person;
    }
}