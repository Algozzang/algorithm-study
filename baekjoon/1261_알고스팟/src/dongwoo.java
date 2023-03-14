import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class dongwoo {
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		// bfs로 거리계산
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[][] distance = new int[N][M];
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1, o2) -> o1[2] - o2[2]);
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
		q.add(new int[] { 0, 0, 0 });
		distance[0][0] = 0;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			if (now[0] == N - 1 && now[1] == M - 1) {
				System.out.println(now[2]);
				break;
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
				if (val != 0 && distance[newR][newC] > now[2] + 1) {
					distance[newR][newC] = now[2] + 1;
					q.add(new int[] { newR, newC, now[2] + 1 });
				} else if (val == 0 && distance[newR][newC] > now[2]) {
					distance[newR][newC] = now[2];
					q.add(new int[] { newR, newC, now[2] });
				}
			}
		}

	}
}
