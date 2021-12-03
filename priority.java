import java.util.Scanner;
 
//using low value as high priority
public class priority{


    public static void main(String[] atgs) {
        System.out.println("Enter the number of process");
        Scanner in = new Scanner(System.in);
        int noOfProcess = in.nextInt();
    
        int pid[] = new int[noOfProcess];
        int bt[] = new int[noOfProcess];
        int at[] = new int[noOfProcess];
        int pi[] = new int[noOfProcess];
        int ct[] = new int[noOfProcess];
        int ta[] = new int[noOfProcess];
        int wt[] = new int[noOfProcess];
    
        for(int i = 0; i < noOfProcess; i++) {
            System.out.println("Enter process " + (i+1) + " arrival time: ");
            at[i] = in.nextInt();
            System.out.println("Enter process " + (i+1) + " brust time: ");
            bt[i] = in.nextInt();
            System.out.println("Enter process " + (i+1) + " priority: ");
            pi[i] = in.nextInt();
            pid[i] = i+1;
        }
        in.close();
        int temp;
        for (int i = 0; i < noOfProcess; i++) 
        {

            for (int j = 0; j < noOfProcess - i - 1; j++) 
            {
                if (at[j] > at[j + 1]) 
                {
                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;
                  
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;
                  
                    temp = pi[j];
                    pi[j] = pi[j + 1];
                    pi[j + 1] = temp;
                  
                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;

                }
                if (at[j] == at[j + 1]) 
                {
                    if (pi[j] > pi[j + 1]) 
                    {
                    
                        temp = at[j];
                        at[j] = at[j + 1];
                        at[j + 1] = temp;
                     
                        temp = bt[j];
                        bt[j] = bt[j + 1];
                        bt[j + 1] = temp;
                        
                        temp = pi[j];
                        pi[j] = pi[j + 1];
                        pi[j + 1] = temp;
                      
                        temp = pid[j];
                        pid[j] = pid[j + 1];
                        pid[j + 1] = temp;

                    }
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

        System.out.println("Process\t\tAT\t\tBT\t\tPI\t\tCT\t\tTAT\t\tWT");
        for(int i = 0; i < noOfProcess; i++) {
            System.out.println(pid[i]+"\t\t" + at[i] + "\t\t" + bt[i]+ "\t\t" + pi[i]+ "\t\t"  + ct[i]+ "\t\t" + ta[i]+ "\t\t" + wt[i]);
        }

        float sum=0;
        for(int i=0;i<noOfProcess;i++)
        sum+=ta[i];
        System.out.println("Average Turn-atound Time(TAT): "+sum/noOfProcess);

        sum=0;
        for(int i=0;i<noOfProcess;i++)
        sum+=wt[i];
        System.out.println("Average Waiting Time(WT): "+sum/noOfProcess);

        System.out.println("Gantt Chart : ");
        for(int i = 0; i < noOfProcess; i++) {
            System.out.print("P" + pid[i] +" ");
        }
    }
}
