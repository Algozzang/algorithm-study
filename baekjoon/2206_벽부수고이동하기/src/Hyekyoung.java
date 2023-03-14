import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Hyekyoung {
	static class Point {
		int r, c, cnt, wall;

		public Point(int r, int c, int cnt, int wall) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.wall = wall;
		}
	}

	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
	static int n, m, map[][], res = -1, visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new int[n][m];
		
		for(int i=0; i<n; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		bfs();
		System.out.println(res);
	}

	static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(0, 0, 1, 0));
		while (!queue.isEmpty()) {
			Point curPoint = queue.poll();

			if (curPoint.r == n - 1 && curPoint.c == m - 1) {
				res = curPoint.cnt;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nr = curPoint.r + dr[i];
				int nc = curPoint.c + dc[i];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				if(visited[nr][nc] <= curPoint.wall) continue;
				//벽을 한 번도 안 부수고온 쪽이 먼저 탐색했다면, visited[nr][nc]에는 0이 담기므로 벽을 부수고온 쪽에서는 탐색할 필요가 없다.
				//벽을 한 번 부수고온 쪽이 먼저 탐색한다면, visited[nr][nc]에는 1이 담기므로 벽을 안 부수고온 쪽에서 새로 탐색
				
				if (map[nr][nc] == 0) {
					visited[nr][nc] = curPoint.wall;
					queue.add(new Point(nr, nc, curPoint.cnt + 1, curPoint.wall));
				} else {
					if (curPoint.wall == 0) {
						visited[nr][nc] = curPoint.wall+1;
						queue.add(new Point(nr, nc, curPoint.cnt + 1, curPoint.wall + 1));
					}
				}
			}
		}
	}
}
