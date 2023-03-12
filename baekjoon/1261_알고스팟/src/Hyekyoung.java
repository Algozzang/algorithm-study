import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Hyekyoung {
	static class Point {
		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
	static int n, m, map[][], res;
	static Deque<Point> queue = new ArrayDeque<>();
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		bfs();
		System.out.println(res);

	}

	static void bfs() {
		visited[0][0] = true;
		queue.add(new Point(0, 0, 0));

		while (!queue.isEmpty()) {
			Point curPoint = queue.pollFirst();
			if (curPoint.r == m - 1 && curPoint.c == n - 1) {
				res = curPoint.cnt;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nr = curPoint.r + dr[i];
				int nc = curPoint.c + dc[i];

				if (nr < 0 || nr >= m || nc < 0 || nc >= n || visited[nr][nc]) continue;

				if (map[nr][nc] == 0) {
					visited[nr][nc] = true;
					queue.addFirst(new Point(nr, nc, curPoint.cnt));
				} else {
					visited[nr][nc] = true;
					map[nr][nc] = 0;
					queue.addLast(new Point(nr, nc, curPoint.cnt + 1));
				}
			}
		}
	}
}
