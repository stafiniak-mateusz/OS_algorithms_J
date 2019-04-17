
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayDeque;


public class FIFO{
    private ArrayList<Integer> callList;
    private int frames;
    private int callNumber;
    private double hits;
    FIFO(ArrayList<Integer> cList, int frameNumber){
        callList = cList;
        frames = frameNumber;
        this.hits=0.0;
        int max=0;
        for(Integer var: callList){
            if(var>max){
                max=var;
            }
        }
        callNumber = max;
    }

    void mainLoop(){
        Queue<Integer> q = new ArrayDeque<Integer>();
        ArrayList<Integer> framesList = new ArrayList<Integer>();

        for (Integer integer : callList) {

            boolean ifRepeated = false;

            for (Integer var : framesList) {

                if (var.equals(integer))
                {
                    System.out.println(Arrays.toString(framesList.toArray()));
                    ifRepeated = true;
                }
            }
            if (ifRepeated) {
                continue;
            }
            if (framesList.size() < frames)
            {

                framesList.add(integer);
                q.add(integer);
            }
            else if (framesList.size() >= frames)
            {
                int tmp = q.remove();
                for (int j = 0; j < framesList.size(); j++)
                {
                    if (framesList.get(j) == tmp) {
                        framesList.add(j, integer);
                        framesList.remove(j + 1);
                        hits++;
                    }
                }
                q.add(integer);
            }
            System.out.println(Arrays.toString(framesList.toArray()));
        }
        float hit_ratio=1-(float)(hits/callList.size());
        System.out.println("Hit ratio: "+hit_ratio);
    }
    /**
     * @return the callList
     */
    public ArrayList<Integer> getCallList() {
        return callList;
    }
    /**
     * @param callList the callList to set
     */
    public void setCallList(ArrayList<Integer> callList) {
        this.callList = callList;
    }
    /**
     * @return the frames
     */
    public int getFrames() {
        return frames;
    }
    /**
     * @param frames the frames to set
     */
    public void setFrames(int frames) {
        this.frames = frames;
    }
}