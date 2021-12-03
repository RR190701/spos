import java.util.*;
import java.util.Scanner;
import java.util.Queue;
public class PageReplacement {
    

    public static void main(String[] args) {
        int process, frames;    

        Queue<Integer> q = new LinkedList<>();       
        Queue<Integer> leastUsed = new LinkedList<>();
        Set<Integer> hs = new HashSet<Integer>();
        
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.print("******************** Page Replacement Algorithm *********************");
        System.out.println();

        System.out.print("Enter total number of processes :");
        process = sc.nextInt();
        System.out.println();

        System.out.println("Enter all the processes in order ~");
        int processes[] = new int[process];
        for (int i = 0; i < process; i++) {
            System.out.print("P" + i + " :");
            processes[i] = sc.nextInt();
        }
        System.out.println();

        System.out.print("Enter number of frames in memory :");
        frames = sc.nextInt();
        System.out.println();

        //FIFO
        int pageHit =0;
        int pageFault = 0;
        for(int i =0; i<process; i++){

            if( hs.contains(processes[i])){
                pageHit +=1;
            }

            else if(q.size() == frames){
                int topProcess = q.remove();
                q.add(processes[i]);
                hs.remove(topProcess);
                hs.add(processes[i]);
                pageFault +=1;
            }
            else{
                q.add(processes[i]);
                hs.add(processes[i]);
                pageFault +=1;

            }
        }
        
System.out.println("FIFO ~");
System.out.println("page Hit :" + pageHit);
System.out.println("page Fault :" + pageFault);
System.out.println();


//LRU
pageFault =0;
pageHit =0;
hs.clear();
for(int i =0; i<process; i++){


    if( hs.contains(processes[i])){
        pageHit +=1;
    }

    else if(hs.size() == frames){

        int LRU_process = -1;
        int LRU_count = 0;

        for (Integer item: hs) {
            int count = 0;
            int j =i;

            while(processes[j] != item){
                count++;
                j--;
            }
            
            if(LRU_process == -1 || LRU_count < count ){
                    LRU_process = processes[j];
                    LRU_count = count;
            }
            
        }


        hs.remove(LRU_process);
        hs.add(processes[i]);
        pageFault++;


    }
    else{
        hs.add(processes[i]);
        pageFault +=1;

    }
}
System.out.println("LRU ~");
System.out.println("page Hit :" + pageHit);
System.out.println("page Fault :" + pageFault);
System.out.println();


 
//Optimal
pageFault =0;
pageHit =0;
hs.clear();
for(int i =0; i<process; i++){


    if( hs.contains(processes[i])){
        pageHit +=1;
    }

    else if(hs.size() == frames){

        int LRU_process = -1;
        int LRU_count = 0;

        for (Integer item: hs) {
            int count = 0;
            int j =i;

            while(j < process){
                if(processes[j] == item){
                    break;
                }
                count++;
                j++;

            }

            if(j == process){
                LRU_process = item;
                break;
            }
            
            if(LRU_process == -1 || LRU_count < count ){
                    LRU_process = processes[j];
                    LRU_count = count;
            }
            
        }


        hs.remove(LRU_process);
        hs.add(processes[i]);
        pageFault++;


    }
    else{
        hs.add(processes[i]);
        pageFault +=1;

    }
}
System.out.println("Optimal ~");
System.out.println("page Hit :" + pageHit);
System.out.println("page Fault :" + pageFault);
System.out.println();

    }
}
