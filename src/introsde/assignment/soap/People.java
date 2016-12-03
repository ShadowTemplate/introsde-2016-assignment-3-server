package introsde.assignment.soap;

import introsde.assignment.to.Measure;
import introsde.assignment.to.Person;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL) //optional
public interface People {

    @WebMethod(operationName="readPersonList")
    @WebResult(name="people")
    List<Person> readPersonList();

    @WebMethod(operationName="readPerson")
    @WebResult(name="person")
    Person readPerson(@WebParam(name = "personId") Long id);

    @WebMethod(operationName="updatePerson")
    @WebResult(name="person")
    Person updatePerson(@WebParam(name = "person") Person person);

    @WebMethod(operationName="createPerson")
    @WebResult(name="person")
    Person createPerson(@WebParam(name = "person") Person person);

    @WebMethod(operationName="deletePerson")
    @WebResult(name="person")
    void deletePerson(@WebParam(name = "personId") Long id);

    @WebMethod(operationName="readPersonHistory")
    @WebResult(name="history")
    List<Measure> readPersonHistory(@WebParam(name = "personId") Long id, @WebParam(name = "measure") String measureType);

    @WebMethod(operationName="readMeasureTypes")
    @WebResult(name="measures")
    List<String> readMeasureTypes();

    @WebMethod(operationName="readPersonMeasure")
    @WebResult(name="measure")
    Measure readPersonMeasure(@WebParam(name = "personId") Long id, @WebParam(name = "measure") String measureType,
                              @WebParam(name = "measureId") Long mid);

    @WebMethod(operationName="savePersonMeasure")
    @WebResult(name="measure")
    void savePersonMeasure(@WebParam(name = "personId") Long id, @WebParam(name = "measure") Measure measure);

    @WebMethod(operationName="updatePersonMeasure")
    @WebResult(name="measure")
    Measure updatePersonMeasure(@WebParam(name = "personId") Long id, @WebParam(name = "measure") Measure measure);
}