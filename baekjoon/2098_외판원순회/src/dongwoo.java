import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class dongwoo {
	static int[][] adjMatrix;
	static int N;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		adjMatrix = new int[N][N];
		dp = new int[N][(1 << N) - 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cost = dfs(0, 0, 1);
		System.out.println(cost);

	}

	private static int dfs(int visited, int now, int selected) {
		if (selected == N) {
			if (adjMatrix[now][0] != 0) {
				return adjMatrix[now][0];
			} else {
				return Integer.MAX_VALUE/2;
			}
		}
		int res = Integer.MAX_VALUE/2;
		if (dp[now][visited] != Integer.MAX_VALUE) {
			return dp[now][visited];
		}
		int original = visited;
		visited = visited | (1 << now);
		for (int i = 0; i < N; i++) {
			if ((visited & (1 << i)) == 0 && adjMatrix[now][i] != 0) {
				int next = adjMatrix[now][i] + dfs(visited, i, selected + 1);
				res = Math.min(res, next);
			}
		}
		dp[now][original] = res;
		return res;
	}
}
