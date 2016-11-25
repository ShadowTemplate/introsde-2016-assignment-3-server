package introsde.assignment.soap;

import introsde.assignment.model.Measure;
import introsde.assignment.model.Person;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "introsde.assignment.soap.People")
public class PeopleImpl implements People {
    @Override
    public Person readPerson(int id) {
        return new Person(1L, "Gianvito", "Taneburgo", new ArrayList<Measure>(), new ArrayList<Measure>());
    }

    @Override
    public List<Person> getPeople() {
        return new ArrayList<>();
    }

    @Override
    public int addPerson(Person person) {
        return 0;
    }

    @Override
    public int updatePerson(Person person) {
        return 42;
    }

    @Override
    public int deletePerson(int id) {
        return -1;
    }
}
