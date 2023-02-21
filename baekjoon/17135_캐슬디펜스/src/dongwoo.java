import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class dongwoo {
	static final int[] dc = { -1, 0, 1 };
	static final int[] dr = { 0, -1, 0 };
	static int N, M, D;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M];
		int[][] mapBackUp = new int[N + 1][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				mapBackUp[i][j] = map[i][j];
			}
		}

		int monsterKilledBest = 0;
		for (int archor1 = 0; archor1 < M - 2; archor1++) {
			for (int archor2 = archor1 + 1; archor2 < M - 1; archor2++) {
				for (int archor3 = archor2 + 1; archor3 < M; archor3++) {
					mapInitialize(mapBackUp);
					monsterKilledBest = Math.max(monsterKilledBest, simulate(archor1, archor2, archor3));
				}
			}
		}
		System.out.println(monsterKilledBest);
	}

	private static void mapInitialize(int[][] mapBackUp) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = mapBackUp[i][j];
			}
		}
		
	}

	private static int simulate(int archor1, int archor2, int archor3) {
		int result = 0;
		for (int archorR = N; archorR >= 0; archorR--) {
			int[] target1 = findNearestEnemy(archorR, archor1, archorR, 0);
			int[] target2 = findNearestEnemy(archorR, archor2, archorR, 0);
			int[] target3 = findNearestEnemy(archorR, archor3, archorR, 0);
			if (map[target1[0]][target1[1]] == 1) {
				map[target1[0]][target1[1]] = 0;
				result++;
			}
			if (map[target2[0]][target2[1]] == 1) {
				map[target2[0]][target2[1]] = 0;
				result++;
			}
			if (map[target3[0]][target3[1]] == 1) {
				map[target3[0]][target3[1]] = 0;
				result++;
			}
		}
		return result;
	}

	private static int[] findNearestEnemy(int R, int C, int archorR, int distance) {
		Queue<int[]> nearest = new ArrayDeque<>();
		nearest.add(new int[] { R, C, 0 });
		int[] now = null;
		while (!nearest.isEmpty()) {
			now = nearest.poll();
			if (map[now[0]][now[1]] == 1) {
				break;
			}
			for (int i = 0; i < 3; i++) {
				int newR = now[0] + dr[i];
				int newC = now[1] + dc[i];
				if (newR != archorR && newC >= 0 && newC < M && newR >= 0 && now[2] + 1 <= D) {
					nearest.add(new int[] { newR, newC, now[2] + 1 });
				}
			}
		}

		return now;
	}
}
