import java.util.*;

public class SJF {
    private List<Process> processList;

    SJF(List<Process> pL){
        processList = pL;

    }

    public void mainLoop(){
        PriorityQueue<Process> buffer = new PriorityQueue<>(Process::compareTo);

        Process tmp = new Process(0, 0);
        System.out.println(Process.totalBurstTime);
        System.out.println(Process.processNumber);

        for (int i = 0; i < Process.totalBurstTime; i++) {
            for (int j = 0; j < Process.processNumber; j++) {
                if (processList.get(j).arrivalTime == i) {                  //sprawdzanie czy jakis procesz przybedzie w iterowanym momencie
                    buffer.add(processList.get(j));                                     //jesli tak, dodajemy do kolejki
                   // System.out.println("Proces dodany do kolejki");

                }
            }


                tmp = buffer.remove();

               // System.out.println("Proces zdjety z kolejki");
                tmp.burstTime--;
                buffer.add(tmp);

                int orderNumber=tmp.number+1;
                System.out.println("P"+orderNumber+": "+ tmp.burstTime);
                if(tmp.burstTime==0){
                    buffer.remove(tmp);
                    System.out.println("P"+orderNumber+" Czas oczekiwania: " + tmp.getWaitingTime());
                }

            for(Process var : buffer)
            {
                var.increment_waiting_time();
            }
        }
        Process.processNumber = -1;
        Process.totalBurstTime = 0;

    }
}
