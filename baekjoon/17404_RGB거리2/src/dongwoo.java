import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class dongwoo {
	static int n;
	static int[][] costs;
	static int[][] dp1;
	static int[][] dp2;
	static int[][] dp3;
	static int minCost = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		costs = new int[n][3];	// 색칠 하는데에 드는 비용 
		dp1 = new int[n][3];	// 맨 위에서 1번째 색 선택 한 경우 
		dp2 = new int[n][3];	// 맨 위에서 2번째 색 선택 한 경우 
		dp3 = new int[n][3];	// 맨 위에서 3번째 색 선택 한 경우 

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			costs[i][0] = Short.parseShort(st.nextToken());
			costs[i][1] = Short.parseShort(st.nextToken());
			costs[i][2] = Short.parseShort(st.nextToken());

		}

		calcDp(dp1, 0);
		calcDp(dp2, 1);
		calcDp(dp3, 2);
		
		int[] arr = new int[] { dp1[n - 1][1], dp1[n - 1][2], dp2[n - 1][0], dp2[n - 1][2], dp3[n - 1][0], dp3[n - 1][1] };
		Arrays.sort(arr);
		System.out.println(arr[0]);
	}

	private static void calcDp(int[][] dp, int startColor) {
		int color1 = (1 + startColor) % 3;
		int color2 = (2 + startColor) % 3;
		dp[0][startColor] = costs[0][startColor];		// dp에 시작 컬러 더해주기  
		// 1번째 행에는 두 개만 가능하니까 나머지 하나 최댓값으로 초기화(다음 행에서선택하지 않게 유도)
		dp[1][startColor] = Integer.MAX_VALUE;			
		dp[1][color1] = dp[0][startColor] + costs[1][color1];
		dp[1][color2] = dp[0][startColor] + costs[1][color2];

		for (int i = 2; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				int from1 = (1 + j) % 3;
				int from2 = (2 + j) % 3;
				dp[i][j] = Math.min(dp[i - 1][from1], dp[i - 1][from2]) + costs[i][j];
			}
		}
	}

}
