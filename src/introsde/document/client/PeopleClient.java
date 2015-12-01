package introsde.document.client;

import introsde.document.model.Person;
import introsde.document.ws.People;

import java.net.URL;
import java.util.List;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class PeopleClient{
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://10.196.210.219:6902/ws/people?wsdl");
        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.document.introsde/", "PeopleService");
        Service service = Service.create(url, qname);

        People people = service.getPort(People.class);
        // read person with id 1
        Person p = people.readPerson(1);
        System.out.println("First Person with id=1 ==> "+p.getName());
        // change name of the person with id 1
        String uuid = UUID.randomUUID().toString();
        p.setName("Veabrechuk New name"+ uuid);
        people.updatePerson(p);
        // read again this person
        p = people.readPerson(1);
        System.out.println("First Person with id=1 ==> "+p.getName());
    }
}
