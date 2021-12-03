import java.util.*;

class round_robin
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println ("enter no of process:");
        int n = sc.nextInt();
        System.out.println ("enter quantum time:");
        int qt = sc.nextInt();
        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int btt[] = new int[n];
        int ct[] = new int[n];
        int ta[] = new int[n];
        int wt[] = new int[n];
        int f[] = new int[n];
        		    Queue<Integer> q
            = new LinkedList<>();

        int ma=0,currtime=0 ,z=n,completedprocess=0;
        float avgwt=0, avgta=0;

        for(int i=0;i<n;i++)
        {
            System.out.println ("enter process " + (i+1) + " arrival time:");
            at[i] = sc.nextInt();
            System.out.println ("enter process " + (i+1) + " brust time:");
            bt[i] = sc.nextInt();
            btt[i]=bt[i];
            pid[i] = i+1;
            f[i] = 0;
            if(bt[i]>ma){ma=bt[i];}
        }
      
        while(completedprocess<n){
            z=n;
            for(int j=0;j<n;j++){
                if(at[j]<=currtime && f[j]==0){
                    int x=bt[j];
                    for(int k=0;k<Math.min(x,qt);k++){
                        bt[j]--;currtime++;
                        q.add(j +1);
                    }
                    if(bt[j]==0){
                        f[j]=1;
                        ct[j]=currtime;
                        completedprocess++;
                    }
                    z=j;
                }
            }
            if(z==n){
                currtime++;
                System.out.print("idle");
            }
        }
        for(int i = 0; i < n; i++) {
            ta[i] = ct[i] - at[i];
            wt[i] = ta[i] - btt[i];
            avgta+=ta[i];
            avgwt+=wt[i];
        }

        System.out.println("\npid \t\tAt \t\tBt\t\tCt\t\tTut\t\tWt");
        for(int i=0;i<n;i++)
        {
            avgwt+= wt[i];
            avgta+= ta[i];
            System.out.println(pid[i]+"\t\t"+at[i]+"\t\t"+btt[i]+"\t\t"+ct[i]+"\t\t"+ta[i]+"\t\t"+wt[i]);
        }
        System.out.println ("\naverage turn around time is "+ (float)(avgta/n));
        System.out.println ("average waiting time is "+ (float)(avgwt/n));
        		System.out.println("\nGantt Chart: ");
                for (Integer item: q) {
         String process =  Integer.toString(item);
         System.out.print("P"+ process+ " ");
        }	
        sc.close();
    }
}