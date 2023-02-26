import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HyeKyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] home = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		long[][][] dp = new long[n + 1][n + 1][3]; // 0:가로 1:세로 2:대각선
		dp[1][2][0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 3; j <= n; j++) {
				if (home[i][j] == 1) continue;

				if (home[i][j - 1] == 0) dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][2];
				if (home[i - 1][j] == 0) dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2];
				if (home[i - 1][j - 1] == 0 && home[i][j - 1] == 0 && home[i - 1][j] == 0)
					dp[i][j][2] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
			}
		}
		System.out.println(dp[n][n][0] + dp[n][n][1] + dp[n][n][2]);
	}

}
