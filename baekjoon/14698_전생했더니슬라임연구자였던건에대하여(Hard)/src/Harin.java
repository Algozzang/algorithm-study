package BOJ;

import java.util.*;
import java.io.*;

public class BJ14698_전생했더니슬라임연구자였던건에대하여 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            int n = Integer.parseInt(br.readLine());    //슬라임수

            Long[] slime = new Long[n];     //슬라임에너지 저장
            
            Long answer = 1L;
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                slime[i] = Long.parseLong(st.nextToken());
            }

            if(n==1) sb.append(1).append("\n");     //한개면 에너지 필요 없음
            else {
                PriorityQueue<Long> pq = new PriorityQueue<>();     //작은수부터 곱해야 결과가 작아지므로 pq 사용
                for (int i = 0; i < n; i++) {
                    pq.add(slime[i]);
                }

                while(pq.size() > 1){
                    Long e1 = pq.poll();
                    Long e2 = pq.poll();

                    Long newE = (e1*e2);
                    pq.add(newE);       //저장할때 mod 연산하면 안됨
                    answer = answer * (newE%1000000007) % 1000000007;

                }
                sb.append(answer).append("\n");
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }
}
