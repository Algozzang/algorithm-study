import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M; // 미로 크기
	static int[][] maze; // 미로
	static int[][] memo; // 해당 위치까지 오는데 부순 최소 벽의 수
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		maze = new int[N][M];
		memo = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = str.charAt(j) - '0';
			}
			Arrays.fill(memo[i], Integer.MAX_VALUE);
		}

		// solution
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { 0, 0 });
		memo[0][0] = 0;
		while (!queue.isEmpty()) {
			int[] p = queue.poll();
			int x = p[0];
			int y = p[1];
			for (int d = 0; d < 4; d++) {
				int dx = x + direct[d][0];
				int dy = y + direct[d][1];
				if (inRange(dx, dy) && memo[x][y] + maze[dx][dy] < memo[dx][dy]) {
					memo[dx][dy] = memo[x][y] + maze[dx][dy];
					queue.add(new int[] { dx, dy });
				}
			}
		}

		// result
		System.out.println(memo[N - 1][M - 1]);
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

}
