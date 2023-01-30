import java.util.Scanner;

public class HyeKyoung {
	static boolean[] button = new boolean[10];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), m = sc.nextInt(), cnt = 0, result = Math.abs(n - 100);
		int n1;
		for (int i = 0; i < m; i++) {
			button[sc.nextInt()] = true;
		}

		for (int i = 0; i < 999999; i++) {
			if (Math.abs(i - n) > result)
				continue;
			cnt = 0;
			n1 = i;
			if (n1 == 0) {
				if (button[n1])
					continue;
				result = Math.min(result, 1 + n);
			} else {
				while (true) {
					if (n1 == 0) {
						result = Math.min(result, cnt + Math.abs(n - i));
						break;
					}

					cnt++;
					if (button[n1 % 10])
						break;
					n1 /= 10;
				}
			}
		}

		System.out.println(result);
	}

}
