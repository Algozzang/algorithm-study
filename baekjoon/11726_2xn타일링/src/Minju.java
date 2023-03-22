import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Minju {
	// 열심히 그렸더니 1 2 3 5 8 나와서 규칙찾음
	static int n;
	static long dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		dp = new long[n + 1];
		dp[1] = 1;
		if (n > 1)
			dp[2] = 2;
		if (n > 2) {
			for (int i = 3; i <= n; i++) {
				dp[i] = (dp[i - 1] + dp[i - 2])%10007;
			}
		}

		System.out.println(dp[n]);

	}

}
