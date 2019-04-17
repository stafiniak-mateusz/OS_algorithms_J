import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.NoSuchElementException;
public class Systemy {
        public static void main(String[] args) throws IOException {

            Scanner reader = new Scanner(System.in);

                boolean flag = true;
                while(flag)
                {
                    System.out.println("Wybierz Algorytm: \n[1] Algorytmy szeregowania procesów\n[2] Algorytmy Zastępowania Stron w Pamięci\n[0] Zakoncz");
                    int choice;
                    choice = reader.nextInt();
                    switch(choice){
                        case 1:
                            ProcessMenu p_menu = new ProcessMenu();
                            p_menu.menu();
                            break;
                        case 2:
                            MemoryMenu m_menu = new MemoryMenu();
                            m_menu.menu();
                            break;
                        case 0:
                            flag=false;
                            break;
                    }
                }


    }
}
class ProcessMenu{
    void menu() throws IOException {

        String filePath = "C:\\Users\\mateu\\OneDrive\\Studia\\Others\\Java\\Systemy\\process.txt";

            ArrayList<Integer> callList = new ArrayList<Integer>();
            List<Integer> burstTime = new ArrayList<Integer>();
            List<Integer> arrivalTime = new ArrayList<Integer>();

            Reader reader = new Reader(filePath);

            int processNumber = reader.nextInt();
            System.out.println("Ilosc Procesow: "+processNumber);

            System.out.print("Fazy procesora: ");
            int pPhase;
            for(int i=0; i<processNumber; i++){
                pPhase = reader.nextInt();
                System.out.print(pPhase+" ");
                burstTime.add(pPhase);
            }

            System.out.print("\nCzasy przybycia: ");
            for(int i=0; i<processNumber; i++){
                pPhase = reader.nextInt();
                System.out.print(pPhase+" ");
                arrivalTime.add(pPhase);
            }
            System.out.println();
            System.out.println();

        List<Process> pl = new ArrayList<Process>();
            for(int i=0; i<processNumber; i++){
                Process p = new Process(arrivalTime.get(i), burstTime.get(i));
                pl.add(p);
            }
        System.out.println("Wybierz Algorytm: \n[1] First Come First Served\n[2] Shortest Job First");
            Scanner choice = new Scanner(System.in);
            switch(choice.nextInt()){
                case 1:
                    FCFS fcfs = new FCFS(pl);
                    fcfs.mainLoop();
                    break;
                case 2:
                    SJF sjf = new SJF(pl);
                    sjf.mainLoop();
                case 0:
                    return;
            }

    }
}

class MemoryMenu
{
    private int frames;
    private ArrayList<Integer> callList = new ArrayList<Integer>();
    private String filePath = "C:\\Users\\mateu\\OneDrive\\Studia\\Others\\Java\\Systemy\\memory.txt";
    private Reader reader = new Reader(filePath);


    void menu() throws IOException {
        frames = reader.nextInt();

        System.out.println("Liczba ramek: "+frames);
        int callLength = reader.nextInt();
        System.out.println("Dlugosc listy wywolan: "+callLength);
        System.out.println("Lista wywolan: ");

        for (int i = 0; i < callLength; i++)
        {
            callList.add(reader.nextInt());
            System.out.println(callList.get(i));
        }

        System.out.println("Wybierz Algorytm: \n[1] First In First Out\n[2] Most Frequent Use");

        Scanner choice = new Scanner(System.in);
        switch(choice.nextInt()){
            case 1:
                //Process.processNumber=-1;
                FIFO fifo = new FIFO(callList, frames);
                fifo.mainLoop();
                break;
            case 2:
                //Process.processNumber=-1;
                MFU mfu = new MFU(callList, frames);
                mfu.mainLoop();
            case 0:
                return;
        }

    }
}

class Reader{
    private Scanner sc;
    Reader(String filePath){
        File file = new File(filePath);
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    int nextInt() throws IOException {
        try {
            return Integer.parseInt(sc.nextLine());

        }
        catch (NoSuchElementException e){
            System.out.println("File not completed");
        }
        return -1;
    }
}