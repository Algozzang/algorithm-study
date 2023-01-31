import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HyeKyoung {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long sum = 0;
		List<String> list = new ArrayList<>();
		long[] alphabet = new long[10]; // 알파벳들의 점수
		boolean[] im = new boolean[10];
		boolean[] checkZero = new boolean[10];
		int[] a = new int[10];
		for (int i = 0; i < n; i++) {
			String str = sc.next();
			list.add(str);
			checkZero[str.charAt(0) - 'A'] = true; // 0이 맨 앞자리에 안나오도록 체크
		}
		for (String string : list) {
			long scr = 1;
			for (int i = string.length() - 1; i >= 0; i--) {
				alphabet[string.charAt(i) - 'A'] += scr;
				scr *= 10;
			}
		}
		for (int i = 0; i < 10; i++) {
			long min = Long.MAX_VALUE;
			int idx = -1;
			for (int j = 0; j < 10; j++) {
				if (im[j])
					continue;

				if (i == 0 && checkZero[j]) {
					continue;
				}

				if (alphabet[j] < min) {
					min = alphabet[j];
					idx = j;
				}

			}
			im[idx] = true;
			a[i] = idx;
		}

		for (String string : list) {
			long scr = 1,  s = 0;
			for (int i = string.length() - 1; i >= 0; i--) {

				for (int j = 0; j < 10; j++) {
					if (a[j] + 'A' == string.charAt(i)) {
						s = j * scr;
						break;
					}
				}
				sum += s;
				scr *= 10;
			}

		}
		System.out.println(sum);
	}

}
