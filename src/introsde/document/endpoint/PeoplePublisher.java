package introsde.document.endpoint;
import introsde.document.ws.PeopleImpl;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

import javax.xml.ws.Endpoint;

public class PeoplePublisher {
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException{
        String SERVER_URL = "http://localhost";
        String PORT = "6902";
        String BASE_URL = "/ws/people";

        if (String.valueOf(System.getenv("PORT")) != "null"){
            PORT=String.valueOf(System.getenv("PORT"));
        }
        
        String endpointUrl = SERVER_URL+":"+PORT+BASE_URL;
        System.out.println("Starting People Service...");
        System.out.println("--> Published. Check out "+endpointUrl+"?wsdl");
        Endpoint.publish(endpointUrl, new PeopleImpl());
    }
}
