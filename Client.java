import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        startClient();
    }

    // ส่วนของ Client
    public static void startClient() {
        try {
            // ใส่ IP ของเซิร์ฟเวอร์ที่ต้องการเชื่อมต่อ
            String serverIp = "192.168.1.122";  
            Socket socket = new Socket(serverIp, 8080);

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // ส่งข้อความไปยัง Server
            output.println("Hello from Client!");

            // รับข้อความตอบกลับจาก Server
            String serverMessage = input.readLine();
            System.out.println("Received from server: " + serverMessage);

            input.close();
            output.close();
            socket.close();
            System.out.println("Client closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
