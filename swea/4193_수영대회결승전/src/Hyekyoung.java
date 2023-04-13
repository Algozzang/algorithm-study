import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Hyekyoung {
	static int N, map[][], A, B, C, D, cnt = -1;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			cnt = -1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());

			bfs();
			sb.append("#" + tc + " " + cnt + "\n");
		}
		System.out.println(sb.toString());
	}

	static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(A, B, 0));
		int nr, nc, r, c, time;
		Point curPoint;

		while (!queue.isEmpty()) {
			curPoint = queue.poll();
			r = curPoint.r;
			c = curPoint.c;
			time = curPoint.time;

			if (r == C && c == D) {
				// 도착지 도착
				cnt = time;
				return;
			}

			for (int i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1 || visited[nr][nc]) continue;

				if (map[nr][nc] == 2) {
					if (time % 3 == 2) {
						// 소용돌이 사라짐
						visited[nr][nc] = true;
						queue.offer(new Point(nr, nc, time + 1));
					} else {
						// 못가니까 기다려
						queue.offer(new Point(r, c, time + 1));
					}
				} else { //갈 수 있음
					visited[nr][nc] = true;
					queue.offer(new Point(nr, nc, time + 1));
				}
			}
		}
	}

	static class Point {
		int r, c, time;

		public Point(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}

	}
}
