package introsde.assignment.soap;

import javax.xml.ws.Endpoint;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Publisher {

    public static void main(String[] args) throws UnknownHostException, ClassNotFoundException {
        String protocol = "http://";
        String hostname = InetAddress.getLocalHost().getHostAddress();
        if (hostname.equals("127.0.0.1")) {
            hostname = "localhost";
        }
        String port = "6902";
        String baseUrl = "/ws/people";

        if (String.valueOf(System.getenv("PORT")) != "null") {
            port = String.valueOf(System.getenv("PORT"));
        }

        String endpointUrl = protocol + hostname + ":" + port + baseUrl;
        System.out.println("Starting service...");
        Endpoint.publish(endpointUrl, new PeopleImpl());
        System.out.println("Published. Check out " + endpointUrl + "?wsdl");
    }
}
