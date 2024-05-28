package JEP_451;

import com.sun.tools.attach.VirtualMachine;

import java.lang.management.ManagementFactory;

public class JEP_451_Dynamic_loading {

    public static void agentLoader() throws Exception {

        System.setProperty("jdk.attach.allowAttachSelf", "true");

        // Get the process ID of the current VM.
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];

        // Create a VirtualMachine object for the current VM.
        VirtualMachine vm = VirtualMachine.attach(pid);

        // Load the agent JAR file into the current VM.
        vm.loadAgent("Agent_Jar/SimpleVersion-1.0-SNAPSHOT.jar");

        // Detach the VirtualMachine object from the current VM.
        vm.detach();
    }
}