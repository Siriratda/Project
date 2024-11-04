import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ping {
    public static void main(String[] args) throws UnknownHostException, IOException {
    String ipAddress = "192.168.1.122";
    InetAddress inet = InetAddress.getByName(ipAddress); //getByNameเป็นเมธอดของคลาส InetAddress ใช้เพื่อรับข้อมูลของip

    System.out.println("Sending Ping Request to " + ipAddress);
    System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");

    ipAddress = "192.168.1.114";
    inet = InetAddress.getByName(ipAddress);

    System.out.println("Sending Ping Request to " + ipAddress);
    System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");
}
}
