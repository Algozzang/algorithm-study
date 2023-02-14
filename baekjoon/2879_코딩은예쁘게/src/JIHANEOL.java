// 구글에 도움으로 풀었다 .....
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class JIHANEOL {
	// 이해가 안간다.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] target = new int[N];
        int prev = 0;
        int answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            target[i] = Integer.parseInt(st.nextToken())-target[i];
        }
        System.out.println(Arrays.toString(target));
        if(N>1) {
            prev = target[0];
            for (int i = 1; i < N; i++) {
            	
                if(prev*target[i]<0){ // 다르면 
                    answer += Math.abs(prev); // 수학을 잘해야 하나...........
                }else if(Math.abs(prev)>=Math.abs(target[i])){
                        answer += Math.abs(prev)-Math.abs(target[i]);
                }
                prev = target[i];
                System.out.println(prev+" "+ answer);
            }
        }else{
            answer = target[0];
        }
        answer += Math.abs(prev);
        System.out.println(answer);
    }

}