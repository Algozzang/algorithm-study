import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class seonghun {

	static int N;
	static int[][] paper;
	static int[] answer = new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// initialize
		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		for (int i = 0; i < N; i++) {
			paper[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		// solution
		cutting(N, 0, 0);

		// result
		for (int a : answer) {
			System.out.println(a);
		}
	}

	public static void cutting(int n, int x, int y) {
		boolean allSame = true;
		int value = paper[x][y];

		// 범위 내에서 모두 같은 값인지 확인
		for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				if (paper[i][j] != value) {
					allSame = false;
					break;
				}
			}
			if (!allSame) {
				break;
			}
		}

		// 값이 모두 같은 값인 경우 카운팅
		if (allSame) {
			answer[value + 1]++;
		} else {
			// 값이 모두 같지는 않은 경우 종이 자르기
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					cutting(n / 3, x + i * (n / 3), y + j * (n / 3));
				}
			}
		}
	}

}
