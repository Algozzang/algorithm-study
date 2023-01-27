import java.util.Scanner;
import java.io.FileInputStream;

class HyeKyoung {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(), cnt;
		long result;

		for (int test_case = 1; test_case <= T; test_case++) {
			result = 1;
			String str = sc.next();
			for (int i = 0; i < str.length(); i++) {
				cnt = 1;
				if (i > 0) {
					if (str.charAt(i) != str.charAt(i - 1))
						cnt++;
				}
				if (i < str.length() - 1) {
					if (str.charAt(i) != str.charAt(i + 1))
						cnt++;
				}
				if (cnt > 1 && i != 0 && i != str.length() - 1) {
					if (str.charAt(i + 1) == str.charAt(i - 1))
						cnt--;
				}
				result *= cnt;
				result %= 1000000007;
			}
			System.out.printf("#%d %d\n", test_case, result);
		}
	}
}