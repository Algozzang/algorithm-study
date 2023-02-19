import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HyeKyoung {
	static boolean[][] isVisited;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int n, cnt = 0;
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		isVisited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++)
				map[i][j] = str.charAt(j) - '0';
		}

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 0) {
					isVisited[i][j] = true;
					continue;
				}

				if (!isVisited[i][j]) {
					apartmentCnt(i, j);
					list.add(cnt); // 다음 위치로 넘긴 좌표가 모두 방문했던가 0
					cnt = 0;
				}
			}
		System.out.println(list.size());
		Collections.sort(list);
		for (int x : list) System.out.println(x);
	}

	static void apartmentCnt(int r, int c) {
		cnt++;

		isVisited[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

			if (isVisited[nr][nc] || map[nr][nc] == 0) continue;
			else apartmentCnt(nr, nc);
		}
	}
}