import java.util.*;
import java.util.Scanner;
import java.util.function.ToIntBiFunction;

public class Placement {

    static boolean firstFit(int memory[], int process, int partition, int memoryUsed) {

        for (int i = 0; i < partition; i++) {
            if (process <= memory[i]) {
                memory[i] -= process;
                memoryUsed += process;
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int partition, process;
        float memorySize = 0, memoryUsed = 0;
        Scanner sc = new Scanner(System.in);
        
        System.out.print("******************** Partition Algorithm *********************");
        System.out.println();

        System.out.print("Enter total number of partition of memory :");
        partition = sc.nextInt();
        System.out.println();

        int memory[] = new int[partition];
        int ram[] = new int[partition];
        System.out.println("Enter size for each partition in order (in K) ~");
        for (int i = 0; i < partition; i++) {
            System.out.print("Partiton " + i + " :");
            ram[i] = sc.nextInt();
            memory[i] = ram[i];
            memorySize += memory[i];
        }
        System.out.println();

        
        System.out.print("Enter total number of processes :");
        process = sc.nextInt();
        System.out.println();

        int processes[] = new int[process];
        System.out.println("Enter size for each processes (in K) ~");
        for (int i = 0; i < process; i++) {
            System.out.print("Process " + i + " :");
            processes[i] = sc.nextInt();
        }
        System.out.println();

        // first fit
        memoryUsed = 0;
        for (int i = 0; i < process; i++) {
            for (int j = 0; j < partition; j++) {
                if (processes[i] <= memory[j]) {
                    memory[j] -= processes[i];
                    memoryUsed += processes[i];
                    break;
                }
            }
        }

        float ratio;
        ratio = memoryUsed / memorySize;
        System.out.println("Memory Utilization for first fit :" + ratio);
        System.out.println();

        // Best fit
        for (int i = 0; i < partition; i++) {
            memory[i] = ram[i];
        }
        memoryUsed = 0;
        for (int i = 0; i < process; i++) {
            int bestFit = 10000000;
            int pos = -1;
            for (int j = 0; j < partition; j++) {
                if (processes[i] <= memory[j] && memory[j] < bestFit) {
                    bestFit = memory[j];
                    pos = j;
                }
            }

            if (pos != -1) {
                memory[pos] -= processes[i];
                memoryUsed += processes[i];
            }

        }

        ratio = memoryUsed / memorySize;
        System.out.println("Memory Utilization for best fit :" + ratio);
        System.out.println();

        // Worst fit
        for (int i = 0; i < partition; i++) {
            memory[i] = ram[i];
        }
        memoryUsed = 0;
        for (int i = 0; i < process; i++) {
            int worstFit = 0;
            int pos = -1;
            for (int j = 0; j < partition; j++) {
                if (processes[i] <= memory[j] && memory[j] > worstFit) {
                    worstFit = memory[j];
                    pos = j;
                }
            }

            if (pos != -1) {
                memory[pos] -= processes[i];
                memoryUsed += processes[i];
            }

        }
        ratio = memoryUsed / memorySize;
        System.out.println("Memory Utilization for worst fit :" + ratio);
        System.out.println();

    }

}
