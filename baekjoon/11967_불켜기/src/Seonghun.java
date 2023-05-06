import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Seonghun {

	static int N, M;
	static boolean[][][][] S; // 방별 불켜는 스위치 표시
	static boolean[][] lighton;
	static boolean[][] reachable;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static Queue<int[]> queue = new ArrayDeque<>();
	static int answer; // 불을 켤 수 있는 방의 최대 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = new boolean[N + 1][N + 1][N + 1][N + 1];
		lighton = new boolean[N + 1][N + 1];
		reachable = new boolean[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			S[x][y][a][b] = true;
		}

		// solution
		lighton[1][1] = true;
		reachable[1][1] = true;
		queue.add(new int[] { 1, 1 });
		while (!queue.isEmpty()) {
			int[] p = queue.poll();
			int x = p[0], y = p[1];
			// 현재 방에서 도달 가능한 방 표시
			for (int d = 0; d < 4; d++) {
				int dx = x + direct[d][0];
				int dy = y + direct[d][1];
				if (inRange(dx, dy) && !reachable[dx][dy]) {
					reachable[dx][dy] = true;
					// 불이 이미 켜져있었다면
					if (lighton[dx][dy]) {
						queue.add(new int[] { dx, dy });
					}
				}
			}
			// 현재 방에서 불킬 수 있는 방 불키기
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (S[x][y][i][j] && !lighton[i][j]) {
						lighton[i][j] = true;
						// 도달 가능한 곳이라면
						if (reachable[i][j]) {
							queue.add(new int[] { i, j });
						}
					}
				}
			}
		}

		// result
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (lighton[i][j]) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}

	static boolean inRange(int x, int y) {
		return 1 <= x && x <= N && 1 <= y && y <= N;
	}

}