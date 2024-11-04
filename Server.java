import java.io.*;
import java.net.*;

public class Server {
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

            // อ่านข้อความจาก Client
            String clientMessage = input.readLine();
            System.out.println("Received from client: " + clientMessage);

            // ตอบกลับไปยัง Client
            output.println("Hello from Server!");

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
