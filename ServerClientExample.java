import java.io.*;
import java.net.*;

public class ServerClientExample {

    public static void main(String[] args) {
        // เริ่มการทำงานของเซิร์ฟเวอร์ในเธรดใหม่ เธรดสามารถทำให้รันหลายๆโปรแกรมพร้อมกันได้
        new Thread(() -> startServer()).start();

        // รอให้ Server เริ่มต้นก่อน
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        // เริ่มการทำงานของไคลเอนต์ในเธรดใหม่
        new Thread(() -> startClient()).start();
    }

    // ส่วนของ Server
    public static void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080); //สร้าง ServerSocketให้เซิร์ฟเวอร์รอรับการเชื่อมต่อบนพอร์ต 
            System.out.println("Server is listening on port");

            Socket socket = serverSocket.accept(); //เซิร์ฟเวอร์จะรอให้ไคลเอนต์เชื่อมต่อ
            System.out.println("Client connected");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); //ใช้สำหรับอ่านข้อมูลจากไคลเอนต์
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true); //ใช้สำหรับส่งข้อมูลไปยังไคลเอนต์

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
            Socket socket = new Socket("192.168.1.122", 8080);

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
