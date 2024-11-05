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

            // กำหนด timeout ให้กับ socket สำหรับการอ่านข้อมูล
            socket.setSoTimeout(10000); // หมดเวลา 10 วินาที

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String clientMessage;

            // วนลูปรับส่งข้อมูลกับ Client ตลอดเวลา
            while (true) {
                try {
                    // อ่านข้อความจาก Client หากไม่มีข้อความเข้ามาใน 10 วินาทีจะเกิด Timeout
                    clientMessage = input.readLine();
                    if (clientMessage == null) {
                        System.out.println("Client disconnected.");
                        break; // ถ้า Client หยุดการเชื่อมต่อ
                    }
                    System.out.println("Received from client: " + clientMessage);

                    // ตอบกลับไปยัง Client
                    output.println("Server received: " + clientMessage);
                } catch (SocketTimeoutException e) {
                    System.out.println("Connection timed out, closing server.");
                    break; // หากเกิด Timeout ให้หยุดการทำงาน
                }
            }

            // ปิดการเชื่อมต่อ
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
