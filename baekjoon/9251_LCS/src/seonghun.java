import java.util.Scanner;

public class seonghun {

	static String str1, str2;
	static int len1, len2;
	static int[][] dp;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// initialize
		str1 = input.next();
		str2 = input.next();
		len1 = str1.length();
		len2 = str2.length();
		dp = new int[len1][len2];

		// solution
		dp[0][0] = (str1.charAt(0) == str2.charAt(0)) ? 1 : 0;
		for (int i = 1; i < len1; i++) {
			dp[i][0] = dp[i - 1][0] == 1 || (dp[i - 1][0] == 0 && str1.charAt(i) == str2.charAt(0)) ? 1 : 0;
		}
		for (int i = 1; i < len2; i++) {
			dp[0][i] = dp[0][i - 1] == 1 || (dp[0][i - 1] == 0 && str2.charAt(i) == str1.charAt(0)) ? 1 : 0;
		}
		for (int i = 1; i < len1; i++) {
			for (int j = 1; j < len2; j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		// result
		System.out.println(dp[len1 - 1][len2 - 1]);
	}

}
