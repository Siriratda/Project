import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ping {
    public static void main(String[] args) throws UnknownHostException, IOException {
    String ipAddress = "172.20.10.7";
    InetAddress inet = InetAddress.getByName(ipAddress);

    System.out.println("Sending Ping Request to " + ipAddress);
    System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");

    ipAddress = "8.8.8.8";
    inet = InetAddress.getByName(ipAddress);

    System.out.println("Sending Ping Request to " + ipAddress);
    System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");
}
}
