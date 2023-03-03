import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Seonghun {

	static int N; // 도시의 수
	static int[][] cost; // 도시 이동 비용 행렬
	static int[][] memo; // 현재 도시와 방문 경로에 따라 최선의 값을 저장
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// initialize
		N = Integer.parseInt(br.readLine());
		cost = new int[N][N];
		memo = new int[N][1 << N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// result
		System.out.println(minCost(0, 1)); // 0번 도시에서 출발할때의 최소 비용
	}

	static int minCost(int curr, int visited) {
		// 모든 도시에 방문했어
		if (visited == (1 << N) - 1) {
			if (cost[curr][0] != 0) { // 출발지로 돌아갈 수 있어..
				return cost[curr][0];
			}
			return INF;
		}
		// 앞으로의 최선의 길을 이미 계산했었나?
		if (memo[curr][visited] != 0) {
			return memo[curr][visited];
		}
		// 다음은 어디로 가지?
		int minCost = INF;
		for (int next = 1; next < N; next++) {
			int msk = 1 << next;
			int cst = cost[curr][next];
			if ((visited & msk) == 0 && cst != 0) { // 방문한적 없고, 갈 수 있는 길이야
				int minCst = minCost(next, visited | msk); // 다녀올게, 기다려..
				if (minCst != INF) {
					minCost = Math.min(minCost, cst + minCst);
				}
			}
		}
		// 계산한 최소 비용이야
		return memo[curr][visited] = minCost;
	}

}