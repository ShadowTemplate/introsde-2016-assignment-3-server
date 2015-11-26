package introsde.document.endpoint;
import introsde.document.ws.PeopleImpl;

import javax.xml.ws.Endpoint;

public class PeoplePublisher {
    public String SERVER_URL = "http://localhost";
    public String PORT = "6902";
    public String BASE_URL = "/ws/people";

    public String getEndpointURL() {
	    if (String.valueOf(System.getenv("PORT")) != "null"){
            PORT = String.valueOf(System.getenv("PORT"));
        }
        return SERVER_URL+":"+PORT+BASE_URL;
    }

    public void main(String[] args) {
        String endpointUrl = getEndpointURL();
        System.out.println("Starting People Service...");
        System.out.println("--> Published. Check out "+endpointUrl+"?wsdl");
        Endpoint.publish(endpointUrl, new PeopleImpl());
    }
}
