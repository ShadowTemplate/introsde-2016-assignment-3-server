package introsde.assignment.soap;

import introsde.assignment.dao.EntityDAO;
import introsde.assignment.to.MeasureTO;
import introsde.assignment.to.PersonTO;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "introsde.assignment.soap.People", serviceName="PeopleService")
public class PeopleImpl implements People {

    // #1
    @Override
    public List<PersonTO> readPersonList() {
        return EntityDAO.listPeople();
    }

    // #2
    @Override
    public PersonTO readPerson(Long personId) {
        return EntityDAO.getPerson(personId);
    }

    // #3
    @Override
    public PersonTO updatePerson(PersonTO personTO) {
        return EntityDAO.updatePerson(personTO.getId(), personTO);
    }

    // #4
    @Override
    public PersonTO createPerson(PersonTO personTO) {
        personTO.setId(null);
        return EntityDAO.putPerson(personTO);
    }

    // #5
    @Override
    public void deletePerson(Long personId) {
        PersonTO personTO = EntityDAO.getPerson(personId);
        EntityDAO.deletePerson(personTO);
    }

    // #6
    @Override
    public List<MeasureTO> readPersonHistory(Long personId, String measureType) {
        List<MeasureTO> healthHistory = EntityDAO.getPerson(personId).getHealthHistory();
        List<MeasureTO> measures = new ArrayList<>();
        for (MeasureTO measureTO : healthHistory) {
            if (measureTO.getMeasureType().equals(measureType)) {
                measures.add(measureTO);
            }
        }
        return measures;
    }

    // #7
    @Override
    public List<String> readMeasureTypes() {
        return EntityDAO.listMeasure();
    }

    // #8
    @Override
    public MeasureTO readPersonMeasure(Long personId, String measureType, Long measureId) {
        List<MeasureTO> currentHealth = EntityDAO.getPerson(personId).getCurrentHealth();
        for (MeasureTO measureTO : currentHealth) {
            if (measureTO.getMid().equals(measureId)) {
                return measureTO;
            }
        }
        return null;
    }

    // #9
    @Override
    public MeasureTO savePersonMeasure(Long personId, MeasureTO measureTO) {
        return EntityDAO.addMeasure(personId, measureTO);
    }

    // #10
    @Override
    public MeasureTO updatePersonMeasure(Long personId, MeasureTO measureTO) {
        return EntityDAO.updateMeasure(personId, measureTO);
    }

    // #11 - Utility
    @Override
    public void resetDB() {
        EntityDAO.initDatabase();
    }
}
