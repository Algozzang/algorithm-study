import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class dongwoo {
	static boolean[][] map;
	static int[] di = new int[] { 0, 0, 1, 0, -1 };
	static int[] dj = new int[] { 0, 1, 0, -1, 0 };
	static int min = Integer.MAX_VALUE;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[N+1][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					map[i + 1][j] = true;
				}
			}
		}
		for (int i = 0; i < 1<<N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) == (1 << j)) {
					map[0][j] = true;
				} else {
					map[0][j] = false;
				}
			}
			checkLine(0, 0);
		}
		
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}

	private static void checkLine(int i, int clickCnt) {
		List<Integer> searchable = new ArrayList<>();
		for (int j = 0; j < N; j++) {
			if (map[i][j]) {
				searchable.add(j);
			}
		}
		if (i == N) {
			if (searchable.isEmpty()) {
				min = Math.min(min, clickCnt);
			}
			return;
		}
		for (int j = 0; j < searchable.size(); j++) {
			int pos = searchable.get(j);
			clickSwitch(i + 1, pos);
		}
		checkLine(i + 1, searchable.size() + clickCnt);
		for (int j = 0; j < searchable.size(); j++) {
			int pos = searchable.get(j);
			clickSwitch(i + 1, pos);
		}

	}

	private static void clickSwitch(int i, int j) {
		for (int k = 0; k < 5; k++) {
			int newI = i + di[k];
			int newJ = j + dj[k];
			boolean val;
			try {
				val = map[newI][newJ];
			} catch (Exception e) {
				continue;
			}
			map[newI][newJ] = !val;
		}
	}
}
