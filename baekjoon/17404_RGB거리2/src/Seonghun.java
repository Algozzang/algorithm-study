import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Seonghun {

	static int N;
	static int[][] RGB;
	static int[][] DP;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// initializes
		N = Integer.parseInt(br.readLine());
		RGB = new int[N][3];
		for (int i = 0; i < N; i++) {
			RGB[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		// solution
		for (int s = 0; s < 3; s++) { // 첫번째 행이 시작하는 열 번호
			// RGB 배열을 DP 배열로 복사
			DP = new int[N][3];
			for (int i = 0; i < N; i++) {
				DP[i] = RGB[i].clone();
			}
			// 첫번째 행에서 s열을 제외한 곳을 선택할 수 없도록 설정
			for (int k = 0; k < 3; k++) {
				if (k != s) {
					DP[0][k] = Integer.MAX_VALUE;
				}
			}
			// Dynimic programming
			for (int i = 1; i < N; i++) {
				for (int j = 0; j < 3; j++) {
					int minCost = (j == 0) ? Math.min(DP[i - 1][1], DP[i - 1][2])
								: (j == 1) ? Math.min(DP[i - 1][0], DP[i - 1][2])
										   : Math.min(DP[i - 1][0], DP[i - 1][1]);
					if (minCost == Integer.MAX_VALUE) {
						DP[i][j] = Integer.MAX_VALUE;
					} else {
						DP[i][j] += minCost;
					}
				}
			}
			// 가장 아랫줄에서 s열이 아닌 값들을 비교
			for (int k = 0; k < 3; k++) {
				if (k != s) {
					answer = Math.min(answer, DP[N - 1][k]);
				}
			}
		}

		// result
		System.out.println(answer);
	}

}
