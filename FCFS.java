import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
class FCFS {
    List<Process> processList;

    FCFS(List<Process> pL) {                                     //pl -> List of Processes
        processList = pL;

    }

    void mainLoop() {
            Queue<Process> q = new ArrayDeque<Process>();
            boolean status = false;
            Process tmp= new Process(0, 0);

            for (int i = 0; i < Process.totalBurstTime; i++)
            {
                    for (int j = 0; j < Process.processNumber; j++)
                    {
                            if (processList.get(j).arrivalTime == i)
                            {                                                                                                              //sprawdzanie czy jakis procesz przybedzie w iterowanym momencie
                                q.add(processList.get(j));                                                           //jesli tak, dodajemy do kolejki
                                System.out.println("\nProces dodany do kolejki");
                            }
                    }

                    if (!status && !q.isEmpty())
                    {
                        tmp = ((ArrayDeque<Process>) q).pop();
                        status = true;
                        System.out.println("\nProces zdjety z kolejki");
                    }

                    tmp.burstTime--;
                    int orderNumber=tmp.number+1;

                    System.out.println("P"+orderNumber+": "+tmp.burstTime);
                    if(tmp.burstTime==0)
                    {
                        System.out.println("Czas oczekiwania: " + tmp.getWaitingTime());
                        status = false;
                    }

                    for(Process var : q)
                    {
                        var.increment_waiting_time();
                    }
            }
            Process.processNumber = -1;
            Process.totalBurstTime = 0;


    }
}

