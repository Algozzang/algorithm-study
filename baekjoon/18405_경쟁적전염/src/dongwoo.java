import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class dongwoo {
	static int[] dr = new int[] { 0, 1, 0, -1 };
	static int[] dc = new int[] { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); // 바이러스 종류
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[3] == o2[3] ? o1[2] - o2[2] : o1[3] - o2[3]);
		// {r, c, virusNumber, time}
		int[][] map = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					pq.add(new int[] { i, j, map[i][j], 0 });
					visited[i][j] = true;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()); // S초 뒤
		int X = Integer.parseInt(st.nextToken()); // R 좌표 + 1
		int Y = Integer.parseInt(st.nextToken()); // C 좌표 + 1
		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			int r = now[0];
			int c = now[1];
			for (int i = 0; i < 4; i++) {
				int newR = r + dr[i];
				int newC = c + dc[i];
				int val;
				try {
					val = map[newR][newC];
				} catch (Exception e) {
					continue;
				}
				if (!visited[newR][newC] && val == 0 && now[3] + 1 <= S) {
					map[newR][newC] = now[2];
					visited[newR][newC] = true;
					pq.add(new int[] { newR, newC, now[2], now[3] + 1 });
				}
			}
		}
		System.out.println(map[X - 1][Y - 1]);
	}
}
