package BOJ;

import java.util.*;
import java.io.*;

public class BJ1863_스카이라인쉬운거 {

     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int cnt = 0;

        for(int i=0; i<n; i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(stack.isEmpty()){
                stack.push(y);
            }
            else {
                if (stack.peek() > y) {   //이전 높이보다 작은 건물이 있으면 더 이상 이어질 수 없으므로 건물로 카운트
                    Set<Integer> set = new HashSet<>();
                    while (!stack.isEmpty() && stack.peek() > y) {  //새로 들어온 높이보다 높은 숫자 개수만큼 건물로 카운트
                        int h = stack.pop();
                        if (h > 0) set.add(h);
                    }
                    cnt += set.size();
                }
                stack.push(y);
            }
        }

        //남은 건물 있으면 카운트에 추가
        Set<Integer> set = new HashSet<>();
        while(!stack.isEmpty()){
            int h = stack.pop();
            if(h>0) set.add(h);
        }

        cnt += set.size();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(cnt));
        bw.close();
    }
}
