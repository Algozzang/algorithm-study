import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
LIS 알고리즘
하나씩 설치해서 설치 가능한 경우의 수 중 max 값을 구해서 전체 n에서 빼자

DP ㅁ모대난..
 */
public class Minju {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /* input */
        int n = Integer.parseInt(br.readLine()); // 전깃줄개수

        int[][] line = new int[n][2]; // 전깃줄 정보 담을 배열 : [0] 시작 번호 [1] 끝 번호
        int[] dp = new int[n+1]; // 각 전깃줄 마다 전깃줄 최대 저장 개수
        for (int i =0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j =0;j<2;j++) {
                line[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 그림처럼 왼쪽 전봇대 값들 오름차순 정렬
        Arrays.sort(line,(o1,o2)-> Integer.compare(o1[0], o2[0]));
        // dp
        for (int i =0;i<n;i++) {
            dp[i] = 1; // 현재 왼쪽 전봇대에서 하나 고르고
            for (int j =0;j<i;j++) { // 자신보다 왼쪽 위에값 선들 보면서
                if(line[i][1] > line[j][1]) {  // 직전 애들 중 자기보다 작은값(위에 있는 값) 있으면
                    dp[i] = Math.max(dp[i], dp[j]+1); // 그친구의 dp값 +1 or 현재 값 중 MAX
                }
            }
        }
        //System.out.println(Arrays.toString(dp));
        Arrays.sort(dp);
        System.out.println(n - dp[n]);
    }
}
