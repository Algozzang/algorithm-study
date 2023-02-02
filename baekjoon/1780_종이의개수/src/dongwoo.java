import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class dongwoo {
	public static int[] result = new int[] { 0, 0, 0 };
	public static int[][] jongE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		jongE = new int[n][n];
		for (int i = 0; i < n; i++) {
			String[] lineTemp = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				jongE[i][j] = Integer.parseInt(lineTemp[j]);
			}
		}
		search(n, 0, 0);
		for (int i=0; i<3; i++) {
			System.out.println(result[i]);
		}
	}

	public static void search(int size, int sx, int sy) {
		int num = jongE[sy][sx];
		boolean isDiff = false;
		if (size == 1) {
			chuga(num);
			return;
		}
		for (int i = sy; i < sy + size; i++) {
			for (int j = sx; j < sx + size; j++) {
				if (num != jongE[i][j]) {
					isDiff = true;
					break;
				}
			}
			if (isDiff) {
				break;
			}
		}
		if (isDiff) {
			for (int i = sy; i < sy + size; i+=size/3) {
				for (int j = sx; j < sx + size; j+=size/3) {
					search(size / 3, j, i);
				}
			}
		} else {
			chuga(num);
		}
	}

	public static void chuga(int num) {
		if (num == -1) {
			result[0] += 1;
		}
		if (num == 0) {
			result[1] += 1;
		}
		if (num == 1) {
			result[2] += 1;
		}
		return;
	}
}
