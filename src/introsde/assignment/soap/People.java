package introsde.assignment.soap;

import introsde.assignment.model.Person;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL) //optional
public interface People {
    @WebMethod(operationName="readPerson")
    @WebResult(name="person")
    Person readPerson(@WebParam(name = "personId") int id);

    @WebMethod(operationName="getPeopleList")
    @WebResult(name="people")
    List<Person> getPeople();

    @WebMethod(operationName="createPerson")
    @WebResult(name="personId")
    int addPerson(@WebParam(name = "person") Person person);

    @WebMethod(operationName="updatePerson")
    @WebResult(name="personId")
    int updatePerson(@WebParam(name = "person") Person person);

    @WebMethod(operationName="deletePerson")
    @WebResult(name="personId")
    int deletePerson(@WebParam(name = "personId") int id);
}