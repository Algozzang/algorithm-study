import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//외판원 순회
public class HyeKyoung {
	static int[][] cost, dp;
	static int N, INF = 11000000;
	static int visitedAll;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		visitedAll = (1 << N) - 1;
		cost = new int[N][N];
		dp = new int[N][(1 << N) - 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) Arrays.fill(dp[i], INF);

		System.out.println(tsp(1, 0));
	}

	static int tsp(int visited, int city) {
		if (visited == visitedAll) {
			if (cost[city][0] != 0) return cost[city][0]; // 시작도시로 연결 x
			return INF;
		}

		if (dp[city][visited] != INF) return dp[city][visited];

		for (int i = 0; i < N; i++) {
			if ((visited & (1 << i)) == 0 && cost[city][i] != 0) { // 방문하지 않았고 갈 수 있는 경우
				// dp[i][j]: 현재 있는 도시가 i이고 이미 방문한 도시들의 집합이 j일때, 방문하지 않은 나머지 도시들을 모두 방문한 뒤 출발 도시로 돌아올 때 드는 최소 비용
				dp[city][visited] = Math.min(dp[city][visited], tsp(visited | (1 << i), i) + cost[city][i]);
			}
		}
		return dp[city][visited];
	}
}


