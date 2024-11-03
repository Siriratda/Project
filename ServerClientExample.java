import java.io.*;
import java.net.*;

public class ServerClientExample {

    public static void main(String[] args) {
        // เริ่ม Server ใน Thread แยก
        new Thread(() -> startServer()).start();

        // รอให้ Server เริ่มต้นก่อน
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        // เริ่ม Client ใน Thread แยก
        new Thread(() -> startClient()).start();
    }

    // ส่วนของ Server
    public static void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server is listening on port");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String clientMessage = input.readLine();
            System.out.println("Received from client: " + clientMessage);

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

    // ส่วนของ Client
    public static void startClient() {
        try {
            Socket socket = new Socket("172.20.10.7", 8080);

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            output.println("Hello from Client!");

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
