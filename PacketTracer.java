import pse.pt.PacketTracer;

public class PacketTracer {

    public static void main(String[] args) {
        // สร้างออบเจ็กต์ PacketTracer โดยระบุที่อยู่และพอร์ต
        PacketTracer packetTracer = new PacketTracer("192.168.1.122", 39000, true);

        try {
            // เริ่มต้น Packet Tracer
            packetTracer.launch();

            // เชื่อมต่อกับ Packet Tracer
            packetTracer.connect();

            System.out.println("เชื่อมต่อกับ Cisco Packet Tracer สำเร็จ!");

        } catch (Exception e) {
            System.err.println("การเชื่อมต่อกับ Cisco Packet Tracer ล้มเหลว: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // ปิด Packet Tracer หลังจากเสร็จสิ้นการใช้งาน
            packetTracer.shutDown();
        }
    }
}
