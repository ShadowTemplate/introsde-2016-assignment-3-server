package introsde.assignment.soap;

import introsde.assignment.to.MeasureTO;
import introsde.assignment.to.PersonTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL) //optional
public interface People {

    @WebMethod
    List<PersonTO> readPersonList();

    @WebMethod
    PersonTO readPerson(Long id);

    @WebMethod
    PersonTO updatePerson(PersonTO personTO);

    @WebMethod
    PersonTO createPerson(PersonTO personTO);

    @WebMethod
    void deletePerson(Long id);

    @WebMethod
    List<MeasureTO> readPersonHistory(Long id, String measureType);

    @WebMethod
    List<String> readMeasureTypes();

    @WebMethod
    MeasureTO readPersonMeasure(Long id, String measureType, Long mid);

    @WebMethod
    MeasureTO savePersonMeasure(Long id, MeasureTO measureTO);

    @WebMethod
    MeasureTO updatePersonMeasure(Long id, MeasureTO measureTO);

    @WebMethod
    void resetDB(); // Utility
}