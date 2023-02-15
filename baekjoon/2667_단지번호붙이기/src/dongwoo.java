import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class dongwoo {
	static char[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int n;
	static int houseN;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		List<Integer> result = new ArrayList<>();
		StringTokenizer st;
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] != '1') {
					continue;
				}
				houseN = 0;
				dfs(i, j);
				result.add(houseN);
			}
		}
		System.out.println(result.size());
		result.sort(null);
		for(int val: result) {
			System.out.println(val);			
		}
	}

	private static void dfs(int i, int j) {
		map[i][j] = '0';
		houseN++;
		for (int k = 0; k < 4; k++) {
			int new_i = i+dx[k];
			int new_j = j+dy[k];
			if (new_i>=0 && new_j>=0 && new_i<n && new_j<n) {
				if (map[new_i][new_j]=='1') {
					dfs(new_i, new_j);
				}
			}
		}
	}
}
