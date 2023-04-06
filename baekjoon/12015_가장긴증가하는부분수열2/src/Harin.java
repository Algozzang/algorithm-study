package BOJ;

import java.util.*;
import java.io.*;

public class BJ12015_가장증가하는부분수열2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        ArrayList<Integer> lis = new ArrayList<>();
        lis.add(numbers[0]);

        for(int i=1; i<n; i++){

            int low = 0;
            int high = lis.size();
            int key = numbers[i];

            if(lis.get(high-1) < key) lis.add(key);

            else {
                while (low <= high) {
                    int mid = (low + high)/2;
                    int midval = lis.get(mid);

                    if(low == high){
                        lis.set(mid, key);
                        break;
                    }
                    else if (midval < key)
                        low = mid + 1;
                    else if(midval > key) {
                        high = mid;
                    }
                    else{
                        break;
                    }
                }

            }
        }

        sb.append(lis.size());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }
}
