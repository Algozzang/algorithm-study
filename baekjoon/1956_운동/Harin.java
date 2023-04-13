package BOJ;

import java.util.*;
import java.io.*;

public class BJ1956_운동 {

    private static int[][] D;
    private static final int MAX = 99999999;
    private static int v, e;
    private static ArrayList<Integer> cycleDist = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        D = new int[v+1][v+1];

        for(int i=1; i<v+1; i++){
            for(int j=1; j<v+1; j++){
                D[i][j] = MAX;
            }
        }

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            D[from][to] = w;
        }

        for(int k=1; k<v+1; k++){
            for(int i=1; i<v+1; i++){
                if(i==k) continue;
                for(int j=1; j<v+1; j++){
                    if(j==k) continue;
                    D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);
                }
            }
        }

        int answer = MAX;

        for(int idx=1; idx<v+1; idx++){
            if(D[idx][idx] != MAX){
                answer = Math.min(answer, D[idx][idx]);
            }
        }

        answer = answer == MAX ? -1 : answer;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
