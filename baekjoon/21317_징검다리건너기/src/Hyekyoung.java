import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Hyekyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), max = 1000000;
		StringTokenizer st;
		int[][] jump = new int[n+1][2];
		int[][] dp = new int[n+1][2];
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			jump[i][0] = Integer.parseInt(st.nextToken());	//작은 점프
			jump[i][1] = Integer.parseInt(st.nextToken());	//큰 점프
		}
		int k = Integer.parseInt(br.readLine());
		for(int i=1; i<n; i++) dp[i][0] = dp[i][1] = max;
		dp[1][0] = jump[0][0];
		if(n>1) {
			dp[2][0] = Math.min(jump[0][1], dp[1][0] + jump[1][0]);
			
			for(int i=3; i<n; i++) {
				dp[i][0] = Math.min(dp[i-2][0] + jump[i-2][1], dp[i-1][0] + jump[i-1][0]);
				dp[i][1] = Math.min(dp[i-3][0]+k, dp[i][1]); //세칸 건너뛴 친구랑 이미 왕크게 뛴 애랑 둘 중에 큰거
				dp[i][1] = Math.min(dp[i][1], Math.min(dp[i-2][1] + jump[i-2][1], dp[i-1][1] + jump[i-1][0]));
			}
		}
		
		System.out.println(Math.min(dp[n-1][0], dp[n-1][1]));
	}

}
