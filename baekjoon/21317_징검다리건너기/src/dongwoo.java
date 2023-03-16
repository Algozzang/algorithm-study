import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dongwoo {
	static int N, K;
	static int[][] memo;
	static int[][] stones;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		stones = new int[N][2];
		memo = new int[N][2]; // 해당 돌까지 갈때 들었던 최소 비용, 0번인덱스는 빅점프 안하고, 1번 인덱스는 빅점프 하고
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			stones[i][0] = Integer.parseInt(st.nextToken());
			stones[i][1] = Integer.parseInt(st.nextToken());
			memo[i + 1][0] = Integer.MAX_VALUE;
			memo[i + 1][1] = Integer.MAX_VALUE;
		}
		K = Integer.parseInt(br.readLine());
		dfs(false, 0);

		System.out.println(Math.min(memo[N - 1][0], memo[N - 1][1]));
	}

	private static void dfs(boolean bigJump, int stone) {
		if (!bigJump) { // 빅점프 아직 안했을 경우
			if (stone + 1 < N && memo[stone + 1][0] > memo[stone][0] + stones[stone][0]) {
				memo[stone + 1][0] = memo[stone][0] + stones[stone][0];
				dfs(false, stone + 1);
			}
			if (stone + 2 < N && memo[stone + 2][0] > memo[stone][0] + stones[stone][1]) {
				memo[stone + 2][0] = memo[stone][0] + stones[stone][1];
				dfs(false, stone + 2);
			}
			if (stone + 3 < N && !bigJump && memo[stone + 3][1] > memo[stone][0] + K) {
				memo[stone + 3][1] = memo[stone][0] + K;
				dfs(true, stone + 3);
			}
		} else { // 빅점프를 이미 한 경우
			if (stone + 1 < N && memo[stone + 1][1] > memo[stone][1] + stones[stone][0]) {
				memo[stone + 1][1] = memo[stone][1] + stones[stone][0];
				dfs(true, stone + 1);
			}
			if (stone + 2 < N && memo[stone + 2][1] > memo[stone][1] + stones[stone][1]) {
				memo[stone + 2][1] = memo[stone][1] + stones[stone][1];
				dfs(true, stone + 2);
			}
		}

	}
}