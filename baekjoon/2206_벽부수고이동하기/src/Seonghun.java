import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Seonghun {

	static int N, M; // 미로 크기
	static int[][] maze; // 미로
	static int[][][] minDist; // 해당 위치까지 오는데 걸린 최소 거리
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Node {
		int x, y;
		int dist;
		int wall;

		public Node(int x, int y, int dist, int wall) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.wall = wall;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new int[N][M];
		minDist = new int[N][M][2];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = str.charAt(j) - '0';
				Arrays.fill(minDist[i][j], Integer.MAX_VALUE);
			}
		}

		// solution
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0, 0, 1, 0));
		minDist[0][0][0] = 1;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int x = node.x;
			int y = node.y;
			int d = node.dist;
			int w = node.wall;
			for (int dir = 0; dir < 4; dir++) {
				int dx = x + direct[dir][0];
				int dy = y + direct[dir][1];
				if (inRange(dx, dy)) {
					// 벽이 없으면
					if (maze[dx][dy] == 0) {
						if (minDist[x][y][w] + 1 < minDist[dx][dy][w]) {
							minDist[dx][dy][w] = d + 1;
							queue.add(new Node(dx, dy, d + 1, w));
						}
					}
					// 벽이 있는데 아직 벽을 부순적이 없다면
					else if (w == 0) {
						if (minDist[x][y][w] + 1 < minDist[dx][dy][w + 1]) {
							minDist[dx][dy][w + 1] = d + 1;
							queue.add(new Node(dx, dy, d + 1, w + 1));
						}
					}
				}
			}
		}

		// result
		int answer = Math.min(minDist[N - 1][M - 1][0], minDist[N - 1][M - 1][1]);
		System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

}
