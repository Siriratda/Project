import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) {
        startClient();
    }

    // ส่วนของ Client
    public static void startClient() {
        try {
            Socket socket = new Socket("192.168.200.85", 8080); // เชื่อมต่อกับ Server ที่ localhost บนพอร์ต 8080
            System.out.println("Connected to server");

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            String userMessage;
            String serverMessage;

            // วนลูปรับส่งข้อมูลกับ Server ตลอดเวลา
            while (true) {
                // รับข้อความจากผู้ใช้
                System.out.print("Enter message to server: ");
                userMessage = scanner.nextLine();

                // ส่งข้อความไปยัง Server
                output.println(userMessage);

                // รับข้อความตอบกลับจาก Server
                serverMessage = input.readLine();
                System.out.println("Received from server: " + serverMessage);

                // ออกจากลูปถ้าผู้ใช้พิมพ์ "exit"
                if (userMessage.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            // ปิดการเชื่อมต่อ
            scanner.close();
            input.close();
            output.close();
            socket.close();
            System.out.println("Client closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
