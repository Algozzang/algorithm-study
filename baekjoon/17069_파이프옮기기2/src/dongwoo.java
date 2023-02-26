import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dongwoo {
	static int N;
	static int[][] map;
	static long[][][] dp;
	static long available;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new long[N][N][3];
		// 좌표와 방향이 같으면 가짓수가 같다.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		long sum = dfs(0, 1, 0);
		System.out.println(sum);
	}

	private static long dfs(int r, int c, int status) { // status 0: 가로 1:세로 2:대각
		if (dp[r][c][status] != 0) {
			return dp[r][c][status];
		}
		if (r == N - 1 && c == N - 1) {
			return 1;
		}
		long sum = 0;
		// 오른방향
		if (status != 1 && c + 1 < N) {
			if (map[r][c + 1] == 0) {
				sum += dfs(r, c + 1, 0);
			}
		}
		// 아래방향
		if (status != 0 && r + 1 < N) {
			if (map[r + 1][c] == 0) {
				sum += dfs(r + 1, c, 1);
			}
		}
		// 대각방향
		if (r + 1 < N && c + 1 < N) {
			if (map[r + 1][c + 1] == 0 && map[r + 1][c] == 0 && map[r][c + 1] == 0) {
				sum += dfs(r + 1, c + 1, 2);
			}
		}
		dp[r][c][status] = sum;
		return sum;
	}
}