import java.io.*;
import java.net.*;

public class Server2 {
    public static void main(String[] args) {
        startServer();
    }

    // ส่วนของ Server
    public static void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server is listening on port 8080...");

            // รอรับการเชื่อมต่อจาก Client
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String clientMessage;

            // วนลูปรับส่งข้อมูลกับ Client ตลอดเวลา
            while ((clientMessage = input.readLine()) != null) { // รับข้อความจาก Client
                System.out.println("Received from client: " + clientMessage);

                // ตอบกลับไปยัง Client
                output.println("Server received: " + clientMessage);
            }

            // ปิดการเชื่อมต่อเมื่อ Client ปิด
            input.close();
            output.close();
            socket.close();
            serverSocket.close();
            System.out.println("Server closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}