import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Hyekyoung {

	static int N, M, cnt = 1;
	static boolean[][] map, visited;
	static List<Point>[] switchMap;
	static List<Point> room = new ArrayList<>();
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
	static Queue<Point> queue = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		switchMap = new ArrayList[N * N + 1];
		map = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];
		int x, y, a, b;

		for (int i = 1; i <= N * N; i++) switchMap[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			switchMap[(x - 1) * N + y].add(new Point(a, b));
		}
		lightOn();
		System.out.println(cnt);
	}

	static void lightOn() {
		map[1][1] = true;
		Point curPoint;
		Point p;
		int nr, nc, r, c, pr, pc;
		queue.offer(new Point(1, 1));
		visited[1][1] = true;

		while (!queue.isEmpty()) {
			curPoint = queue.poll();
			r = curPoint.r;
			c = curPoint.c;

			for (Point point : switchMap[(r - 1) * N + c]) {
				pr = point.r;
				pc = point.c;
				if (!visited[pr][pc]) {
					map[pr][pc] = true;
					cnt++;
					room.add(new Point(pr, pc));
				}
				// 현재 위치에서 불을 켤 수 있는곳은 켜고 리스트에 추가
			}

			for (int i=0; i<room.size(); i++) {
				p = room.get(i);
				pr = p.r;
				pc = p.c;
				if (isConnected(r, c, pr, pc) && !visited[pr][pc]) {
					visited[pr][pc] = true;
					queue.offer(new Point(pr, pc));
					room.remove(i);
					i--;
				}
				// 리스트에 있는 목록 중 갈 수 있는 곳은 큐에 넣고 리스트에서 빼기
			}

		}
	}

	static boolean isConnected(int sr, int sc, int er, int ec) {
		// 현재 위치에서 갈 수 있는 곳인지 판단
		queue.offer(new Point(sr, sc));
		int r, c, nr, nc;
		Point curPoint;
		boolean[][] check = new boolean[N + 1][N + 1];
		check[sr][sc] = true;

		while (!queue.isEmpty()) {
			curPoint = queue.poll();
			r = curPoint.r;
			c = curPoint.c;

			if (r == er && c == ec) {
				return true;
			}

			for (int i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];

				if (nr < 1 || nc < 1 || nr > N || nc > N) continue;
				if (!map[nr][nc] || check[nr][nc]) continue;

				check[nr][nc] = true;
				queue.offer(new Point(nr, nc));
			}
		}
		return false;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}
