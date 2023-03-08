import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		st = new StringTokenizer(br.readLine());

		for (int j = 0; j < M; j++) {
			int height = Integer.parseInt(st.nextToken());
			for (int i = 0; i < height; i++) {
				map[i][j] = 1;
			}
		}
		int rain = 0;
		for (int i = 0; i < N; i++) {
			int start = Integer.MAX_VALUE;
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					if (j - 1 > start) {
						rain += (j - start - 1);
						start = j;
					} else {
						start = j;
					}
				}
			}
		}
		System.out.println(rain);
	}
}