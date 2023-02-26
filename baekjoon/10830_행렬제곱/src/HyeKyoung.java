import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HyeKyoung {
	static int[][] matrix;
	static int n;
	static long b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		b = Long.parseLong(st.nextToken());
		matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				matrix[i][j] = Integer.parseInt(st.nextToken());
		}
		int[][] tmp = divide(b);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) sb.append(tmp[i][j]%1000 + " ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	static int[][] divide(long cnt) {
		if (cnt == 1)
			return matrix;

		int[][] copy = divide(cnt / 2);

		if (cnt % 2 == 0) return calMatrix(copy, copy);
		else return calMatrix(calMatrix(copy, copy), matrix);
	}

	static int[][] calMatrix(int[][] a, int[][] b) {
		int[][] tmp = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					tmp[i][j] += a[i][k] * b[k][j];
					tmp[i][j] %= 1000;
				}
			}
		}
		return tmp;
	}
}
