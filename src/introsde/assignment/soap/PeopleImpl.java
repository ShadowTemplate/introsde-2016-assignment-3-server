package introsde.assignment.soap;

import introsde.assignment.dao.EntityDAO;
import introsde.assignment.to.Measure;
import introsde.assignment.to.Person;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "introsde.assignment.soap.People", serviceName="PeopleService")
public class PeopleImpl implements People {

    @Override
    public List<Person> readPersonList() {
        return EntityDAO.listPeople();
    }

    @Override
    public Person readPerson(Long personId) {
        return EntityDAO.getPerson(personId);
    }

    @Override
    public Person updatePerson(Person person) {
        EntityDAO.updatePerson(person.getId(), person);
        return EntityDAO.getPerson(person.getId());
    }

    @Override
    public Person createPerson(Person person) {
        person.setId(null);
        return EntityDAO.putPerson(person);
    }

    @Override
    public void deletePerson(Long personId) {
        Person person = EntityDAO.getPerson(personId);
        EntityDAO.deletePerson(person);
    }

    @Override
    public List<Measure> readPersonHistory(Long personId, String measureType) {
        return EntityDAO.getPerson(personId).getHealthHistory();
    }

    @Override
    public List<String> readMeasureTypes() {
        return EntityDAO.listMeasure();
    }

    @Override
    public Measure readPersonMeasure(Long personId, String measureType, Long measureId) {
        //List<Measure> currentHealth = Arrays.asList(EntityDAO.getPerson(personId).getCurrentHealth());
        //return currentHealth.parallelStream().filter(m -> m.getMid().equals(measureId)).findFirst().orElseGet(null);
        return null;
    }

    @Override
    public void savePersonMeasure(Long personId, Measure measure) {
        //EntityDAO.addMeasure(personId, measure);
    }

    @Override
    public Measure updatePersonMeasure(Long measureId, Measure measure) {
        return null;
    }
}
