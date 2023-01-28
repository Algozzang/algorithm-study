import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		// 시작 02:30 
		// 끝 3:33:58
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str1 = (" " + br.readLine()).toCharArray();
		char[] str2 = (" " + br.readLine()).toCharArray();
		int SIZE1 = str1.length;
		int SIZE2 = str2.length;
		int[][] lcs = new int[SIZE2][SIZE1];
		for (int i = 1; i < SIZE2; i++) {
			for (int j = 1; j < SIZE1; j++) {
				if (str1[j] == str2[i]) {
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
				} else {
					lcs[i][j] = Math.max(lcs[i][j - 1], lcs[i - 1][j]);
				}
			}
		}
		System.out.println(lcs[SIZE2 - 1][SIZE1 - 1]);

	}
}
