import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class dongwoo {
	static int[] dr = new int[] { 0, -1, 1, 0, 0 };
	static int[] dc = new int[] { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 맵 크기
			int M = Integer.parseInt(st.nextToken()); // 시간
			int K = Integer.parseInt(st.nextToken()); // 군집 갯수
			int[][][] map = new int[N][N][3]; // 3차원 : 이동방향, 군집 수, 현재 방향을 결정한 군집의 수
			int[][][] map2 = new int[N][N][3]; // 3차원 : 이동방향, 군집 수, 현재 방향을 결정한 군집의 수
			Queue<int[]> q = new ArrayDeque<>(); // 좌표와 시간 저장.
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				map[r][c][0] = direction;
				map[r][c][1] = size;
				map[r][c][2] = size;
				q.add(new int[] { r, c, 0 });
			}
			int flag = 0;
			while (!q.isEmpty()) {
				int[] now = q.peek();
				int r = now[0];
				int c = now[1];
				int time = now[2];
				if (time == M) {
					break;
				}
				q.poll();
				if (flag != time) {
					map = map2;
					map2 = new int[N][N][3];
					flag = time;
				}
				int direction = map[r][c][0];
				int size = map[r][c][1];
				int newR = r + dr[direction];
				int newC = c + dc[direction];
				if (map2[newR][newC][1] == 0) { // 처음 간거라면 q에 추가 ( 중복 방지 )
					if (newR == 0 || newR == N - 1 || newC == 0 || newC == N - 1) {
						if (size / 2 != 0) { // 금밟았을땐 사라지지 않을때만 큐에 추가
							size /= 2;
							if (direction % 2 == 0) {
								direction -= 1;
							} else {
								direction += 1;
							}
							q.add(new int[] { newR, newC, time + 1 });
						}
					} else {
						q.add(new int[] { newR, newC, time + 1 });
					}
				}
				// 새로운 위치에 값 세팅
				if (map2[newR][newC][2] < size) {
					map2[newR][newC][2] = size;
					map2[newR][newC][0] = direction;
				}
				map2[newR][newC][1] += size;
			}
			int sum = 0;
			while (!q.isEmpty()) {
				int[] now = q.poll();
				int r = now[0];
				int c = now[1];
				sum+=map2[r][c][1];
			}
			System.out.println("#"+tc+" "+sum);
		}
	}
}
