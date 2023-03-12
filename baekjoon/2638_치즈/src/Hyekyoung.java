import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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

	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 }; // 상하좌우
	static int[][] map;
	static boolean[][] visited;
	static int n, m, time;
	static Queue<Point> aQueue = new ArrayDeque<>(); // 외부공기
	static Queue<Point> cQueue = new ArrayDeque<>(); // 치즈

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		OUTER: for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					checkAir(i, j);
					break OUTER;
				}
			}
		}
		changeCheese(0);
		System.out.println(time);
	}

	static void checkAir(int r, int c) {
		// 외부 공기 2로 표시
		visited[r][c] = true;
		map[r][c] = 2;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] != 0) continue;
			if(visited[nr][nc]) continue;
			checkAir(nr, nc);
		}
	}

	static void changeCheese(int cnt) {
		boolean flag = true; // 남은 치즈가 있는지 확인하기 위함
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					flag = false;
					dfs(i, j);
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 3) {
					map[i][j] = 2;
					checkAir(i, j);
				}
			}
		}
		if (flag) {
			time = cnt;
			return;
		}
		for(int i=0; i<n; i++) Arrays.fill(visited[i], false);
		changeCheese(cnt+1);
	}

	static void dfs(int r, int c) {
		//2변이상 맞닿은 치즈 바꾸기
		visited[r][c] = true;
		if (melting(r, c)) map[r][c] = 3;

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
			if (visited[nr][nc] || map[nr][nc] != 1) continue;
			dfs(nr, nc);
		}
	}

	static boolean melting(int r, int c) {
		//두 변 이상 맞닿았는지 확인
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
			if (map[nr][nc] == 2) cnt++;
		}
		if (cnt >= 2) return true;
		return false;
	}
}
