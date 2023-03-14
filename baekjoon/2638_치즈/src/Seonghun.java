import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Seonghun {

	static int N, M; // 모눈종이 크기
	static int count; // 치즈의 수
	static int[][] cheese; // 치즈 (1: 치즈, 2: 외부 공기)
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static Queue<int[]> q = new ArrayDeque<>();
	static int answer; // 모든 치즈가 녹아 없어지는데 걸리는 시간

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheese = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
				if (cheese[i][j] == 1) {
					count++;
				}
			}
		}

		// 외부 공기 표시
		q.add(new int[] { 0, 0 });
		cheese[0][0] = 2;
		spreadAir();

		// 치즈가 없어질 때까지..
		while (count > 0) {
			// 치즈 제거
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < M; y++) {
					if (cheese[x][y] == 1) {
						int air = 0; // 접촉한 공기면
						for (int d = 0; d < 4; d++) {
							if (cheese[x + direct[d][0]][y + direct[d][1]] == 2) {
								air++;
							}
						}
						if (air >= 2) { // 너무 접촉했네
							cheese[x][y] = 0;
							q.add(new int[] { x, y });
							count--;
						}
					}
				}
			}
			// 외부 공기가 다시 퍼집니다.
			for (int[] p : q) {
				cheese[p[0]][p[1]] = 2;
			}
			spreadAir();
			answer++;
		}

		// result
		System.out.println(answer);
	}

	// queue에 담긴 좌표를 이용하여 외부 공기가 퍼지도록하는 함수
	static void spreadAir() {
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int x = p[0];
			int y = p[1];
			for (int d = 0; d < 4; d++) {
				int dx = x + direct[d][0];
				int dy = y + direct[d][1];
				if (inRange(dx, dy) && cheese[dx][dy] == 0) {
					cheese[dx][dy] = 2;
					q.add(new int[] { dx, dy });
				}
			}
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

}
