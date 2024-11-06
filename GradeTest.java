import apiipc.generated.system.ActivityFile;
import pse.pt.PacketTracer;

import java.io.File;

class GradeTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Program start...");

        if (args.length != 1) {
            System.err.println("You need to specify a pka file to grade");
            System.exit(1);
        }

        // ใช้ try-with-resources เพื่อให้แน่ใจว่าทรัพยากรจะถูกปิดอย่างเหมาะสม
        try (PacketTracer packetTracer = new PacketTracer("localhost", 39000, true)) {
            File file = new File(args[0]);

            packetTracer.launch();
            packetTracer.connect();
            packetTracer.fileOpen(file.getCanonicalPath());

            ActivityFile activityFile = packetTracer.getActivityFile();
            Double percentageComplete = activityFile.getPercentageComplete();

            System.out.println(percentageComplete + "\t" + args[0]);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
