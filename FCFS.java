import java.util.Scanner;

public class FCFS {
    public static void main(String[] atgs) {

        System.out.println("Enter the number of process");
        Scanner in = new Scanner(System.in);
        int noOfProcess = in.nextInt();

        int pid[] = new int[noOfProcess];
        int bt[] = new int[noOfProcess];
        int at[] = new int[noOfProcess];
        int ct[] = new int[noOfProcess];
        int ta[] = new int[noOfProcess];
        int wt[] = new int[noOfProcess];

        for(int i = 0; i < noOfProcess; i++) {
            System.out.println("Enter process " + (i+1) + " arrival time: ");
            at[i] = in.nextInt();
            System.out.println("Enter process " + (i+1) + " brust time: ");
            bt[i] = in.nextInt();
            pid[i] = i+1;
        }
        in.close();

        int temp;
        for (int i = 0; i < noOfProcess; i++) {
            for (int j = i+1; j < noOfProcess; j++) {

                if(at[i] > at[j]) {
                    temp = at[i];
                    at[i] = at[j];
                    at[j] = temp;

                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;
                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;
                }
            }
        }

        System.out.println();
        ct[0] = bt[0] + at[0];
        for(int i = 1; i < noOfProcess; i++) {
            if(at[i]<=ct[i-1])
            ct[i] = ct[i - 1] + bt[i];
            else if(at[i]>ct[i-1])
            ct[i]=at[i]+bt[i];
        }

        for(int i = 0; i < noOfProcess; i++) {
            ta[i] = ct[i] - at[i];
            wt[i] = ta[i] - bt[i];
        }

        System.out.println("Process\t\tAT\t\tBT\t\tCT\t\tTAT\t\tWT");
        for(int i = 0; i < noOfProcess; i++) {
            System.out.println(pid[i]+"\t\t" + at[i] + "\t\t" + bt[i]+ "\t\t" + ct[i]+ "\t\t" + ta[i]+ "\t\t" + wt[i]);
        }

        float sum=0;
        for(int i=0;i<noOfProcess;i++)
        sum+=ta[i];
        System.out.println("Average Turn-atound Time(TAT): "+sum/noOfProcess);

        sum=0;
        for(int i=0;i<noOfProcess;i++)
        sum+=wt[i];
        System.out.println("Average Waiting Time(WT): "+sum/noOfProcess);

        System.out.println("Gantt Chart for FCFS: ");
        for(int i = 0; i < noOfProcess; i++) {
            System.out.print("P" + pid[i] +" ");
        }
    }
}