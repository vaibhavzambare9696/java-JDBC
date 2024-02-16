import java.net.InetAddress;
import java.net.UnknownHostException;

public class main {
    public static void main(String[] args) {
 

        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            System.out.println("Host Address:" + address.getHostAddress());
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

