import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Hyekyoung {
	static int n, k, s, x, y, map[][];
	static int[] dr = { 0, 0, -1, 1 }, dc = { -1, 1, 0, 0 };

	static Queue<Virus> queue = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		int input;
		List<Virus> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				input = Integer.parseInt(st.nextToken());
				map[i][j] = input;
				if (input > 0) list.add(new Virus(i, j, input, 0));
			}
		}

		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		Collections.sort(list, new Comparator<Virus>() {
			// 한번만 정렬하면 그 후는 순서대로 알아서 들어감
			@Override
			public int compare(Virus o1, Virus o2) {
				return o1.virus - o2.virus;
			}
		});
		for (Virus virus : list) {
			queue.add(virus);
		}
		bfs();
		System.out.println(map[x - 1][y - 1]);
	}

	static void bfs() {
		Virus b;
		int r, c, virus, nr, nc, sec;

		while (!queue.isEmpty()) {
			b = queue.poll();
			r = b.r;
			c = b.c;
			virus = b.virus;
			sec = b.sec;
			for (int i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] > 0 || sec == s) continue;

				map[nr][nc] = virus;
				queue.add(new Virus(nr, nc, virus, sec + 1));
			}
		}
	}

	static class Virus {
		int r, c, virus, sec;

		public Virus(int r, int c, int virus, int sec) {
			super();
			this.r = r;
			this.c = c;
			this.virus = virus;
			this.sec = sec;
		}

	}
}