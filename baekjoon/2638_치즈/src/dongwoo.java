import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
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
		boolean[][] isOuter = new boolean[N][M];
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		q.add(new int[] { 0, 0, 0 }); // row, column, hour
		isOuter[0][0] = true;
		int hour = 0;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			hour = now[2];
			for (int i = 0; i < 4; i++) {
				int newR = now[0] + dr[i];
				int newC = now[1] + dc[i];
				int val;
				try {
					val = map[newR][newC];
				} catch (IndexOutOfBoundsException e) {
					continue;
				}
				if (isOuter[newR][newC]) {
					continue;
				}
				if (val == 0) {
					q.add(new int[] { newR, newC, now[2] });
					isOuter[newR][newC] = true;
				} else if (val == 1) {
					map[newR][newC]++;
				} else if (val == 2) {
					q.add(new int[] { newR, newC, now[2] + 1 });
					isOuter[newR][newC] = true;
				}
			}
		}
		System.out.println(hour);
	}
}
