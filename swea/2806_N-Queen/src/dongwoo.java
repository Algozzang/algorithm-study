import java.util.ArrayList;
import java.util.Scanner;

public class dongwoo {
	public static void main(String[] args) {
		// 57~ 1:47
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {	
			int N = sc.nextInt();
			boolean[][] possible = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					possible[i][j] = true;
				}
			}
			int[] result = {0};	
			dfs(N, possible.clone(), 0, result);
			System.out.println("#"+test_case+" "+result[0]);
		}
		
	}
	public static void dfs(int N, boolean[][] map, int depth, int[] result) {
		ArrayList<Integer> searchable = new ArrayList<>();
		for (int i=0; i<N; i++) {
			if (map[depth][i]) {
				searchable.add(i);
			}
		}
		if (depth>=N-1) {
			result[0] += searchable.size();
			return;
		}
		for (int i = 0; i < searchable.size(); i++) {
			int r, c;
			r = depth;
			c = searchable.get(i);
			boolean[][] new_map = deepCopy(map);
			for (int j = depth+1; j < N; j++) {
				new_map[j][c] = false;
				int diff = j - r;
				if (c-diff>=0) new_map[j][c-diff] = false;
				if (c+diff<N) new_map[j][c+diff] = false;
			}
			dfs(N, new_map, depth+1, result);
		}
	}
	public static boolean[][] deepCopy(boolean[][] map) {
		int N = map.length;
		boolean[][] result = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result[i][j] = map[i][j];
			}
		}
		return result;
	}
}
