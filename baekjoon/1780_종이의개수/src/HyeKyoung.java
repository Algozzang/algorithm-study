import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리: 317032kb, 시간: 784ms
//http://boj.kr/b110b4d295a84a0c992161b5a5593d84
public class HyeKyoung {
	static int[][] paper;
	static int[] cnt = new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		paper = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				paper[i][j] = Integer.parseInt(st.nextToken());
		}
		cutPaper(0, 0, n);
		for (int x : cnt) {
			System.out.println(x);
		}
	}

	static void cutPaper(int r, int c, int size) {
		int n = paper[r][c];
		boolean same = true;
		OUTER: for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (paper[i][j] != n) {
					same = false;
					break OUTER;
				}
			}
		}
		if (same) {
			cnt[n + 1]++;
			return;
		}
		size /= 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				cutPaper(r + size * i, c + size * j, size);
		}
	}
}
