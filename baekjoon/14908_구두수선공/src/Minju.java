import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Minju {
    public static void main(String[] args) throws IOException {
        StringBuilder sb= new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        WorkTime[] workTimes = new WorkTime[N];
        StringTokenizer st = null;
        for(int i =0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            workTimes[i] = new WorkTime(i+1, Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(workTimes);

        for(WorkTime wt : workTimes){
            sb.append(wt.idx + " ");
        }
        System.out.println(sb);
    }

}

class WorkTime implements Comparable<WorkTime>{
    int idx;
    int day;
    int pay;

    public WorkTime(int idx, int day, int pay) {
        this.idx = idx;
        this.day = day;
        this.pay = pay;
    }
    
    @Override
    public int compareTo(WorkTime o) {
        double divThis = (double)this.day / this.pay;
        double divOther = (double)o.day / o.pay;

        if(divThis == divOther){
            return this.idx - o.idx;
        }else if (divThis > divOther){
            return 1;
        }else return -1;

    }
}