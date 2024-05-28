package JEP_439;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class JEP_439_Generational_ZGC {
    private static final int num_of_objects = 10_000_000;


    public static void Generational_ZGC() {

        Runtime runtime = Runtime.getRuntime();
        System.out.println("Total memory " + runtime.totalMemory());
        System.out.println("Free memory before generating objects " + runtime.freeMemory());

        Instant startTime = Instant.now();
        for (int i = 0; i <= num_of_objects; i++) {
            String collectMe = new String
                    ("Garbage collect me please");
        }

        System.out.println("Free memory after generating objects " + runtime.freeMemory());
        //Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter to proceed to GC");
        //String proceed = myObj.nextLine();
        System.gc();
        System.out.println("Free memory after running gc " + runtime.freeMemory());

        List<GarbageCollectorMXBean> gcMxBeans = ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean gcMxBean : gcMxBeans) {
            System.out.println("=================================");
            System.out.println("Name: " + gcMxBean.getName());
            System.out.println("Object Name: " + gcMxBean.getObjectName());
            System.out.println("Collection Count: " + gcMxBean.getCollectionCount());
            System.out.println("Collection Time: " + gcMxBean.getCollectionTime());
            System.out.println("Memory Pool Names: " + Arrays.toString(gcMxBean.getMemoryPoolNames()));
            System.out.println("Valid: " + gcMxBean.isValid());
            System.out.println("=================================");
        }


        System.out.println("Enter to exit");

    }
}