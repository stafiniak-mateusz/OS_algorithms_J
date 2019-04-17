public class Process implements Comparable<Process>{
    int arrivalTime;
    int burstTime;
    static int totalBurstTime=0;
    static int processNumber=-1;
    private int waitingTime;


    public int getWaitingTime() {
        return waitingTime;
    }

    int number;
    Process(int aT, int bT){
        arrivalTime =aT;
        burstTime=bT;
        waitingTime=0;
        //number = processNumber;
        totalBurstTime+=burstTime;
        processNumber++;
        number = processNumber;
    }
    @Override
    public int compareTo(Process process ){
        return  burstTime -process.burstTime ;

    }
    void increment_waiting_time()
    {
        waitingTime+=1;
    }
}
