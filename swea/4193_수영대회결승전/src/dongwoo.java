import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class dongwoo {
	static int[] dr = new int[] { 1, 0, -1, 0, 0 };
	static int[] dc = new int[] { 0, -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int[] start = new int[2];
			int[] end = new int[2];
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			boolean[][][] visited = new boolean[N][N][3];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 5) {
						map[i][j] = 0;
					}
				}
			}
			st = new StringTokenizer(br.readLine());
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());

			Queue<int[]> q = new ArrayDeque<>();
			q.add(new int[] { start[0], start[1], 0 });
			visited[start[0]][start[1]][0] = true;

			int r = -1;
			int c = -1;
			int time = -1;
			while (!q.isEmpty()) {
				int[] now = q.poll();
				r = now[0];
				c = now[1];
				time = now[2];
				if (r == end[0] && c == end[1]) {
					break;
				}
				for (int i = 0; i < 5; i++) {
					int newR = r + dr[i];
					int newC = c + dc[i];
					int val;
					try {
						val = map[newR][newC];
					} catch (Exception e) {
						continue;
					}
					if (visited[newR][newC][(time + 1) % 3]) {
						continue;
					}
					if (val == 0) {
						q.add(new int[] { newR, newC, time + 1 });
						visited[newR][newC][(time + 1) % 3] = true;
					}
					if (val == 2 && (time + 1) % 3 == 0) {
						q.add(new int[] { newR, newC, time + 1 });
						visited[newR][newC][(time + 1) % 3] = true;
						map[newR][newC] = 0;
					}
				}
			}
			sb.append("#").append(tc).append(" ");
			if (r == end[0] && c == end[1]) {
				sb.append(time);
			} else {
				sb.append(-1);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
