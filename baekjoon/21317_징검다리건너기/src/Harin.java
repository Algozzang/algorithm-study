package ProgrammingProblems.BOJ;

import java.util.*;
import java.io.*;

public class Harin {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        int[] jump = new int[n+1];
        int[] bigjump = new int[n+1];
        for(int i=1; i<n; i++){
            st = new StringTokenizer(br.readLine());
            jump[i] = Integer.parseInt(st.nextToken());
            bigjump[i] = Integer.parseInt(st.nextToken());
        }
        int superjump = Integer.parseInt(br.readLine());

        //dp 배열은 i번째 돌까지 왔을때 소모한 에너지
        int[] dp = new int[n+1];    //작은 점프, 큰 점프만 고려
        int[] dp2 = new int[n+1];   //매우 큰 점프 고려


        for(int i=1; i<n+1; i++){
            if(i<3){    //세번째 돌 이전까지는 작은 점프만 가능
                dp[i] = dp[i-1] + jump[i-1];
            }
            else{
                //1. i번째 돌에 작은 점프로 도착했을때
                //2. 큰 점프로 도착했을때
                dp[i] = Math.min(dp[i-1] + jump[i-1], dp[i-2] + bigjump[i-2]);
            }
        }

        for(int i=1; i<n+1; i++){
            if(i>=6){
                int case1 = dp2[i-1] + jump[i-1];   //앞서 매우 큰 점프를 하고 i번째 돌에 작은 점프로 도착
                int case2 = dp2[i-2] + bigjump[i-2];   //앞서 매우 큰 점프를 하고 i번째 돌에 큰 점프로 도착
                int case3 = dp[i-3] + superjump;    //i번째 돌에 매우 큰 점프로 도착

                dp2[i] = Math.min(case1, case2);
                dp2[i] = Math.min(dp2[i], case3);
            }
            else if(i>=5){
                dp2[i] = Math.min(dp2[i-1] + jump[i-1], dp[i-3] + superjump);   //앞서 매우 큰 점프를 하고 i번째 돌에 작은 점프로 도착
            }
            else if(i>=4){
                dp2[i] = dp[i-3] + superjump;
            }
            else{
                dp2[i] = dp[i];
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(Math.min(dp[n], dp2[n])));
        bw.close();
    }

}
