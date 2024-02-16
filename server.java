import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverDateTime = in.readLine();
            System.out.println("Server Date and Time: " + serverDateTime);
        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

