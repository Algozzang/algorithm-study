package BOJ;

import java.io.*;
import java.util.*;

public class BJ2812_크게만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String number = br.readLine();

        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<n; i++){         //스택에 숫자 하나씩 넣으면서
            int nextNum = number.charAt(i) - '0';

            if(stack.isEmpty()){
                stack.push(nextNum);
            }
            else{
                while(!stack.isEmpty()){
                    if(stack.peek() >= nextNum) break;  
                    if(k == 0) break;   //k만큼만 제거
                    stack.pop();        //스택에 있는 숫자가 새로 들어오는 숫자보다 작으면 pop해서 제거한다
                    k--;                
                }
                stack.push(nextNum);
            }
        }

        if(k > 0){      //작은 숫자만 들어와서 위 과정에저 k개만큼 다 제거하지 못했으면 뒤에서 남은 숫자 제거 (ex. 5 2 54321)
            for(int i=0; i<k; i++){
                stack.pop();
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Integer num : stack){
            sb.append(num);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }
}
