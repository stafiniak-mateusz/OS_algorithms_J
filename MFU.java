import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
public class MFU
{
    private HashMap<Integer, Integer> callTable;
    private ArrayList<Integer> callList;
    private ArrayList<Integer> framesList;
    private int frames;
    private double hits;
    public String path_to_out;
    BufferedWriter out;
    //number of frames declared by user
    MFU(ArrayList<Integer> cList, int frameNumber) throws IOException                                      //creating HashMap for algorithm's use and asigning number of frames
    {
        this.path_to_out="C:\\Users\\mateu\\OneDrive\\Studia\\Others\\Java\\Systemy\\Results.txt";
        this.hits=0.0;
        this.frames = frameNumber;                      
        this.callList = cList;

        int max=0;
        for(Integer var: cList)
        {
            if(var>max)
            {
                max=var;
            }
        }
        callTable = new HashMap<Integer, Integer>();
        System.out.println("MAX: "+max);
        for(int i=0; i<=max; i++)
        {
            callTable.put(i, 0);
        }
        //BufferedWriter out = new BufferedWriter(new FileWriter(path_to_out));
        
    }

    void mainLoop() throws IOException {
        this.path_to_out="C:\\Users\\mateu\\OneDrive\\Studia\\Others\\Java\\Systemy\\Results.txt";
        File file = new File(path_to_out);
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        System.out.println(frames);
        out.write(frames+"");
        out.newLine();
        framesList = new ArrayList<Integer>();
        for (Integer integer : callList) {
            boolean ifRepeated = false;
            for (Integer var : framesList) {
                if (var.equals(integer)) {
                    ifRepeated = true;
                }
            }

            if (ifRepeated) {
                System.out.println(Arrays.toString(framesList.toArray()));
                out.write(String.valueOf(framesList.toArray()));
                out.newLine();
                increment_pages(integer);
                continue;
            }

            if (framesList.size() < frames) {

                framesList.add(integer);
                System.out.println(framesList.size());

                increment_pages(integer);
            } else if (framesList.size() >= frames) {
                int tmp = max_in_map(framesList);
                int k = 0;
                for (Integer var : framesList) {

                    if (var == tmp) {
                        framesList.set(k, integer);
                        hits++;
                        increment_pages(integer);
                    }
                    k++;
                }
                //System.out.println(Arrays.toString(framesList.toArray()));
            }

            System.out.println(Arrays.toString(framesList.toArray()));
            out.write(String.valueOf(Arrays.toString(framesList.toArray())));
            out.newLine();
        }
        float hit_ratio=1-(float)(hits/callList.size());
        System.out.println("Hit ratio: "+hit_ratio);
        out.write("Hit ratio: "+hit_ratio);
        out.newLine();
    }
    
    private int max_in_map(ArrayList<Integer> framesList){
        Map.Entry<Integer, Integer> maxEntry = null;
        for(Map.Entry<Integer, Integer> entry: callTable.entrySet())
        {
            for(Integer var : framesList)
            {
                if(Objects.equals(entry.getKey(), var))
                {
                    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                    {
                        maxEntry = entry;
                    }
                }

            }

        }

        assert maxEntry != null;
        return maxEntry.getKey();
    }

    private void increment_pages(int integer){

            callTable.put(integer, callTable.get(integer) + 1);

            
    }
}