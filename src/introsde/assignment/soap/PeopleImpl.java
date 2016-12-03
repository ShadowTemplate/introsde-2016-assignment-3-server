package introsde.assignment.soap;

import introsde.assignment.dao.EntityDAO;
import introsde.assignment.to.MeasureTO;
import introsde.assignment.to.PersonTO;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "introsde.assignment.soap.People", serviceName="PeopleService")
public class PeopleImpl implements People {

    @Override
    public List<PersonTO> readPersonList() {
        return EntityDAO.listPeople();
    }

    @Override
    public PersonTO readPerson(Long personId) {
        return EntityDAO.getPerson(personId);
    }

    @Override
    public PersonTO updatePerson(PersonTO personTO) {
        EntityDAO.updatePerson(personTO.getId(), personTO);
        return EntityDAO.getPerson(personTO.getId());
    }

    @Override
    public PersonTO createPerson(PersonTO personTO) {
        personTO.setId(null);
        return EntityDAO.putPerson(personTO);
    }

    @Override
    public void deletePerson(Long personId) {
        PersonTO personTO = EntityDAO.getPerson(personId);
        EntityDAO.deletePerson(personTO);
    }

    @Override
    public List<MeasureTO> readPersonHistory(Long personId, String measureType) {
        return EntityDAO.getPerson(personId).getHealthHistory();
    }

    @Override
    public List<String> readMeasureTypes() {
        return EntityDAO.listMeasure();
    }

    @Override
    public MeasureTO readPersonMeasure(Long personId, String measureType, Long measureId) {
        //List<MeasureTO> currentHealth = Arrays.asList(EntityDAO.getPerson(personId).getCurrentHealth());
        //return currentHealth.parallelStream().filter(m -> m.getMid().equals(measureId)).findFirst().orElseGet(null);
        return null;
    }

    @Override
    public void savePersonMeasure(Long personId, MeasureTO measureTO) {
        //EntityDAO.addMeasure(personId, measureTO);
    }

    @Override
    public MeasureTO updatePersonMeasure(Long measureId, MeasureTO measureTO) {
        return null;
    }
}
