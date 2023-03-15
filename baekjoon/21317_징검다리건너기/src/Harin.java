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
        int[] dp_jump = new int[n+1];
        int[] dp_bigjump = new int[n+1];
        int[] dp_superjump = new int[n+1];

        //작은점프만 했을때의 에너지는 그냥 누적
        for(int i=1; i<=n; i++){
            if(i == 1) continue;
            dp_jump[i] = dp_jump[i-1] + jump[i-1];
        }

        /*
        큰 점프가 포함된 경우
        1. [i-2]까지 최소값으로 온 후 [i-2]에서 큰 점프
        2. [i-1]까지 최소값으로 온 후 [i-1]에서 작은 점프
        */
        if(n>=2) dp_bigjump[2] = dp_jump[2];

        for(int i=1; i<=n; i++){
            if(i<3) continue;
            int case1 = Math.min(dp_jump[i-2], dp_bigjump[i-2]) + bigjump[i-2];
            int case2 = Math.min(dp_jump[i-1], dp_bigjump[i-1]) + jump[i-1];

            dp_bigjump[i] = Math.min(case1, case2);
        }

        /*
        * 매우 큰 점프는 돌이 4개 이상이어야 한다
        * 매우 큰 점프가 포함된 경우
        * 1. [i-3]까지 최소값으로 온 후 [i-3]에서 매우 큰 점프
        * 2. [i-2]까지 매우 큰 점프를 포함하고 [i-2]에서 큰 점프
        * 3. [i-1]까지 매우 큰 점프를 포함하고 [i-1]에서 작은 점프
        * */

        for (int i = 1; i <= n; i++) {
            if(i<4) continue;
            int case1 = Math.min(dp_bigjump[i - 3], dp_jump[i - 3]) + superjump;
            int case2 = dp_superjump[i - 2] == 0 ? Integer.MAX_VALUE : dp_superjump[i - 2] + bigjump[i - 2];
            int case3 = dp_superjump[i - 1] == 0 ? Integer.MAX_VALUE : dp_superjump[i - 1] + jump[i - 1];

            int min = Math.min(case1, case2);
            dp_superjump[i] = Math.min(min, case3);
        }

//        System.out.println("dp_jump : " + Arrays.toString(dp_jump));
//        System.out.println("dp_bigjump : " + Arrays.toString(dp_bigjump));
//        System.out.println("dp_superbigjump : " + Arrays.toString(dp_superjump));

        int answer = Math.min(dp_jump[n], dp_bigjump[n]);
        answer = Math.min(answer, dp_superjump[n]);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }

}
