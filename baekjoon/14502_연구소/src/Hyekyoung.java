import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Hyekyoung {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static int n, m, num, selected[], max = 0, safe = 0, birus;
	static int[][] map, mapCopy;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		num = n * m;
		selected = new int[3];
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) safe++;
			}
		}
		comb(0, 0);
		System.out.println(max);
	}

	static void comb(int start, int cnt) {
		if (cnt == 3) {
			mapCopy = new int[n][];
			for (int i = 0; i < n; i++) mapCopy[i] = map[i].clone();
			for (int i = 0; i < 3; i++) {
				int r = selected[i] / m, c = selected[i] % m;
				mapCopy[r][c] = 1;
			}
			safeZone();
			return;
		}

		for (int i = start; i < num; i++) {
			if (map[i / m][i % m] != 0) continue;
			selected[cnt] = i;
			comb(i + 1, cnt + 1);
		}
	}

	static void safeZone() {
		birus = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 2) bfs(i, j);
			}
		}
		max = Math.max(max, safe - birus - 3);
	}

	static void bfs(int sr, int sc) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(sr, sc));
		Point curPoint;
		while (!queue.isEmpty()) {
			curPoint = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curPoint.r + dr[i], nc = curPoint.c + dc[i];
				if (nr < 0 || nr >= n || nc < 0 || nc >= m || mapCopy[nr][nc] != 0) continue;
				queue.offer(new Point(nr, nc));
				if (mapCopy[nr][nc] == 0) mapCopy[nr][nc] = 2;
				birus++;
			}
		}
	}
}