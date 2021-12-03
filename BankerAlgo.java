import java.util.*;
import java.util.Scanner;

class BankerAlgo {

    static boolean check(int need[][], int available[], int process, int resource) {
        for (int i = 0; i < resource; i++) {
            if (need[process][i] == -1 || need[process][i] > available[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int process, resource;
        Scanner sc = new Scanner(System.in);

        System.out.print("******************** Banker's Algorithm *********************");
        System.out.println();

        System.out.print("Enter total number of processes :");
        process = sc.nextInt();
        System.out.println();

        System.out.print("Enter total number of resources :");
        resource = sc.nextInt();
        System.out.println();

        System.out.println("Enter the maximum values of resources available ~");
        int available[] = new int[resource];
        for (int i = 0; i < resource; i++) {
            System.out.print("R" + i + " :");
            available[i] = sc.nextInt();
        }
        System.out.println();

        System.out.println("Enter number of resource allocated to ~");
        int processes[][] = new int[process][resource];
        for (int i = 0; i < process; i++) {
            System.out.println("Process " + i + " ~");
            for (int j = 0; j < resource; j++) {
                System.out.print("R" + j + " :");
                processes[i][j] = sc.nextInt();
                available[j] -= processes[i][j];
            }
            System.out.println();
        }

        System.out.println("Enter the max resource allocation possible for ~");
        int max[][] = new int[process][resource];
        for (int i = 0; i < process; i++) {
            System.out.println("Process " + i + " ~");
            for (int j = 0; j < resource; j++) {
                System.out.print("R" + j + " :");
                max[i][j] = sc.nextInt();
            }
            System.out.println();
        }

        int need[][] = new int[process][resource];
        for (int i = 0; i < process; i++) {
            for (int j = 0; j < resource; j++) {
                need[i][j] = max[i][j] - processes[i][j];
            }
        }

        int done = 0;
        System.out.print("The safe sequence with no deadlock is : ");

        while (done < process) {

            for (int i = 0; i < process; i++) {

                if (check(need, available, i, resource)) {

                    done = done + 1;
                    System.out.print("P" + i + " ");
                    for (int j = 0; j < resource; j++) {
                        available[j] += processes[i][j];
                        need[i][j] = -1;
                    }

                }

            }
        }

    }
}