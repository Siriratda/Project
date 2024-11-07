import apiipc.generated.system.*;
import java.io.File;
import java.io.FilenameFilter;
import pse.pt.PacketTracer;
import java.util.concurrent.TimeUnit;

class Grade2 {
    public static void main(String args[]) throws Exception {
        if (args.length != 1) {
            System.err.println("You need to specify a folder containing .pka files to grade");
            System.exit(1);
        }

        File folder = new File(args[0]);
        File[] listOfFiles = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".pka");
            }
        });

        if (listOfFiles == null || listOfFiles.length == 0) {
            System.err.println("No .pka files found in the specified folder");
            System.exit(1);
        }

        System.out.println("Program start...");

        for (File file : listOfFiles) {
            PacketTracer packettracer = new PacketTracer("localhost", 39000, true);
            try {
                packettracer.launch();
                packettracer.connect();

                System.out.println("Processing file: " + file.getName());

                // ลองเปิดไฟล์ .pka ถ้ามีข้อผิดพลาดจะทำการ reconnect
                boolean openedSuccessfully = false;
                int retryCount = 0;
                while (!openedSuccessfully && retryCount < 3) {
                    try {
                        packettracer.fileOpen(file.getCanonicalPath());
                        openedSuccessfully = true;
                    } catch (Exception e) {
                        System.err.println("Error opening file, reconnecting and retrying...");
                        packettracer.shutDown();
                        packettracer.connect();
                        retryCount++;
                    }
                }

                if (!openedSuccessfully) {
                    System.err.println("Failed to open file " + file.getName() + " after multiple attempts.");
                    continue;
                }

                // เพิ่มเวลาหน่วง
                TimeUnit.SECONDS.sleep(5);

                ActivityFile activityfile = packettracer.getActivityFile();
                Double s = activityfile.getPercentageComplete();

                System.out.println("Completion for " + file.getName() + ": " + s + "%");

            } catch (Exception e) {
                System.err.println("Error processing file " + file.getName() + ": " + e.getMessage());
            } finally {
                // ปิดการเชื่อมต่อกับ Packet Tracer หลังจากประมวลผลไฟล์เสร็จ
                packettracer.shutDown();
            }
        }

        System.out.println("Program end...");
    }
}
