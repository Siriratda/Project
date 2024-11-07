import apiipc.generated.system.ActivityFile;
import pse.pt.PacketTracer;

import java.io.File;

class GradeTest {
    public static void main(String[] args) {
        System.out.println("Program start...");

        if (args.length != 1) {
            System.err.println("You need to specify a pka file to grade");
            System.exit(1);
        }

        File file = new File(args[0]);
        
        try (PacketTracer packetTracer = new PacketTracer("localhost", 39000, true)) {
            packetTracer.launch();
            packetTracer.connect();
            packetTracer.fileOpen(file.getCanonicalPath());

            ActivityFile activityFile = packetTracer.getActivityFile();
            Double percentageComplete = activityFile.getPercentageComplete();

            System.out.println(percentageComplete + "\t" + args[0]);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
