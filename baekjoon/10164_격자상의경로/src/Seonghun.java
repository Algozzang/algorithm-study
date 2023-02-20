import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Seonghun {

	static int N, M, K;
	static int[][] pathCount; // 경로의 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		pathCount = new int[N][M];

		// solution
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == 0 || j == 0) {
					pathCount[i][j] = 1;
				} else {
					pathCount[i][j] = pathCount[i - 1][j] + pathCount[i][j - 1];
				}
			}
		}

		// result
		if (K != 0) {
			int x = (K - 1) / M;
			int y = (K - 1) % M;
			System.out.println(pathCount[x][y] * pathCount[N - x - 1][M - y - 1]);
		} else {
			System.out.println(pathCount[N - 1][M - 1]);
		}
	}

}
