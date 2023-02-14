import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HyeKyoung {
	static int[][] rgb, dp;
	static int n, cost = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		rgb = new int[n + 1][3];
		dp = new int[n + 1][3];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) rgb[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j) dp[1][j] = rgb[1][j];
				else dp[1][j] = 1000000; // 선택한 색 외에는 큰값(Integer.MAX..주면 오버플로우 나서 -21억 정도가 최솟값이 되어버림..)
			}
			color(2);
			if (i == 0) cost = Math.min(cost, Math.min(dp[n][1], dp[n][2]));
			if (i == 1) cost = Math.min(cost, Math.min(dp[n][0], dp[n][2]));
			if (i == 2) cost = Math.min(cost, Math.min(dp[n][0], dp[n][1]));
		}
		System.out.println(cost);
	}

	// 0:빨강 1:초록 2:파랑
	static void color(int i) {
		if (i > n) return;

		dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + rgb[i][0]; // red 선택하는 경우
		dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + rgb[i][1]; // 초록 선택하는 경우
		dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + rgb[i][2]; // 파랑 선택하는 경우
		// 앞에서 선택한 것 중 더 적은 비용을 선택해서 저장

		color(i + 1);
	}
}
