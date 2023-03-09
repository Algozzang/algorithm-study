import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class dongwoo {
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		boolean[][] visited1 = new boolean[N][M];
		boolean[][] visited2 = new boolean[N][M];
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		q.add(new int[] { 0, 0, 1, 0 }); // {row, column, depth, #of broken wall}
		visited1[0][0] = true;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			if (now[0] == N - 1 && now[1] == M - 1) {
				System.out.println(now[2]);
				return;
			}
			for (int i = 0; i < 4; i++) {
				int newR = now[0] + dr[i];
				int newC = now[1] + dc[i];
				int val;
				try {
					val = map[newR][newC];
				} catch (IndexOutOfBoundsException e) {
					continue;
				}
				if (visited1[newR][newC]) {
					continue;
				}
				if (val == 0) {
					if (now[3] == 1) {
						if (visited2[newR][newC]) {
							continue;
						}
						q.add(new int[] { newR, newC, now[2] + 1, now[3] });
						visited2[newR][newC] = true;
					}
					if (now[3] == 0) {
						q.add(new int[] { newR, newC, now[2] + 1, now[3] });
						visited1[newR][newC] = true;
					}
				} else if (now[3] == 0 && !visited2[newR][newC]) {
					q.add(new int[] { newR, newC, now[2] + 1, now[3] + 1 });
					visited2[newR][newC] = true;
				}

			}
		}
		System.out.println(-1);
	}
}
