import apiipc.generated.system.*;
import java.io.File;
import pse.pt.PacketTracer;

class Grade
{
public static void main(String args[])
 throws Exception
{
 File file;
 PacketTracer packettracer;
 if (args.length != 1) {
   System.err.println("You need to specify a pka file to grade");
   System.exit(1);
 }
 file = new File(args[0]);
 packettracer = new PacketTracer("localhost", 39000, true);
 packettracer.launch();
 packettracer.connect();
 packettracer.fileOpen(file.getCanonicalPath());

 ActivityFile activityfile = packettracer.getActivityFile();
 Double s = activityfile.getPercentageComplete();

 System.out.println((new StringBuilder()).append(s).append("\t").append(args[0]).toString());

 packettracer.shutDown();
}
}