import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Minju {
    static int n, k, stone[][], dp[], dynamic[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        stone = new int[n+1][2];// [0] : 한칸점프 [1]:두칸점프
        //dp = new int[n+1];
        dynamic = new int[n+1][2]; //[0]: 징검다리 건너지 않을 때, [1]: 징검다리 건너는거 포함할 때
        StringTokenizer st = null;
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            stone[i][0] = Integer.parseInt(st.nextToken());
            stone[i][1] = Integer.parseInt(st.nextToken());
        }
        //Arrays.fill(dp,100000);

        // 디피배열 초기화
        for(int i =0;i<n+1;i++)  Arrays.fill(dynamic[i],100000);
        k = Integer.parseInt(br.readLine());

        //디피 3개돌까지 초기화 세팅
        stone[0][0] = 0; stone[0][1] = 0;
        dynamic[0][0] = 0;
        dynamic[1][0] = 0;
        if (n > 1) dynamic[2][0] = stone[1][0];
        if (n > 2) dynamic[3][0] = Math.min(dynamic[2][0]+stone[2][0], stone[1][1]); //돌3개있을때 한칸씩 or 첫돌에서 2개점프
        if(n>3) dp(4);

        System.out.println(Math.min(dynamic[n][0], dynamic[n][1]));

    }

    private static void dp(int i) {

        if(i >= n+1) return;

        dynamic[i][0] = Math.min((dynamic[i - 1][0] + stone[i-1][0]), (dynamic[i - 2][0] + stone[i - 2][1]));
        dynamic[i][1] = Math.min((dynamic[i - 1][1] + stone[i-1][0]), Math.min((dynamic[i - 2][1] + stone[i - 2][1]), dynamic[i-3][0] + k)); // 그전에 1칸2칸점프전에 사용해서 왔거나 지금사용하거나
        dp(i+1);
        
        /* 실패코드 매개변수 veryBig boolean
        if(veryBig){ // 매우 큰 점프 썼으면
            int num = Math.min((dp[i - 1] + stone[i-1][0]), (dp[i - 2] + stone[i - 2][1]));
            dp[i] = Math.min(dp[i], num);
            dp(i + 1, true);
        }else {// 아직안썼으면
            // 쓰거나
            int num = dp[i-3]+k;
            dp[i] = Math.min(dp[i], num);
            dp(i + 1, true);

            // 안쓰거나
            num = Math.min((dp[i - 1] + stone[i-1][0]), (dp[i - 2] + stone[i - 2][1]));
            dp[i] = Math.min(dp[i], num);
            dp(i + 1, false);
        }
         */
    }
}
