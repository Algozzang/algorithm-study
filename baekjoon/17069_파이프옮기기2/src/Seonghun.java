import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Seonghun {

	private static int N;
	private static int[][] space;
	private static long[][][] pathCount; // (가로, 세로, 대각선)에 대한 경로 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// initialize
		N = Integer.parseInt(br.readLine());
		space = new int[N + 1][N + 1];
		pathCount = new long[N + 1][N + 1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// solution
		pathCount[1][2][0] = 1; // (1, 2)에 가로 파이프로 시작
		for (int i = 1; i <= N; i++) {
			for (int j = 3; j <= N; j++) {
				if (space[i][j] == 0) {
					if (i == 1) {
						pathCount[i][j][0] += pathCount[i][j - 1][0];
					} else {
						pathCount[i][j][0] += pathCount[i][j - 1][0] + pathCount[i][j - 1][2];
						pathCount[i][j][1] += pathCount[i - 1][j][1] + pathCount[i - 1][j][2];
						if (space[i - 1][j] == 0 && space[i][j - 1] == 0) {
							pathCount[i][j][2] += pathCount[i - 1][j - 1][0] + pathCount[i - 1][j - 1][1] + pathCount[i - 1][j - 1][2];
						}
					}
				}
			}
		}

		// result
		System.out.println(pathCount[N][N][0] + pathCount[N][N][1] + pathCount[N][N][2]);
	}

}
