import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class dongwoo {
	static int N, M;
	static int maxSafeArea;
	static int originalArea = -3;
	static List<int[]> viruses;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		viruses = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					viruses.add(new int[] { i, j });
				}
				if (map[i][j] == 0) {
					originalArea++;
				}
			}
		}

		for (int i = 0; i < N * M - 2; i++) {
			int[] point1 = numberToIJ(i);
			if (map[point1[0]][point1[1]] != 0) {
				continue;
			}
			map[point1[0]][point1[1]] = 3;

			for (int j = i + 1; j < N * M - 1; j++) {
				int[] point2 = numberToIJ(j);
				if (map[point2[0]][point2[1]] != 0) {
					continue;
				}
				map[point2[0]][point2[1]] = 3;
				for (int k = j + 1; k < N * M; k++) {
					int[] point3 = numberToIJ(k);
					if (map[point3[0]][point3[1]] != 0) {
						continue;
					}
					map[point3[0]][point3[1]] = 3;
					checkSafetyArea(clone(map));
					map[point3[0]][point3[1]] = 0;
				}
				map[point2[0]][point2[1]] = 0;
			}
			map[point1[0]][point1[1]] = 0;
		}
		System.out.println(maxSafeArea);

	}

	private static int[][] clone(int[][] map) {
		int[][] cloned = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				cloned[i][j] = map[i][j];
			}
		}
		return cloned;
	}

	private static void checkSafetyArea(int[][] map) {
		Queue<int[]> queue = new ArrayDeque<>();
		for (int[] virus : viruses) {
			queue.add(virus);
		}
		int[] di = { 0, 1, 0, -1 };
		int[] dj = { 1, 0, -1, 0 };
		int infectedArea = 0;
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			map[now[0]][now[1]] = 2;
			for (int i = 0; i < 4; i++) {
				int newI = now[0] + di[i];
				int newJ = now[1] + dj[i];
				int val;
				try {
					val = map[newI][newJ];
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
				if (val == 0) {
					queue.add(new int[] { newI, newJ });
					map[newI][newJ] = 2;
					infectedArea++;
				}
			}
		}
		maxSafeArea = Math.max(maxSafeArea, originalArea - infectedArea);
	}

	private static int[] numberToIJ(int number) {
		int i = number / M;
		int j = number % M;
		return new int[] { i, j };
	}
}
