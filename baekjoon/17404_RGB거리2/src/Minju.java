import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Minju { // 17404
	// 각 좌우 양옆이 달라야함
	// 집의수 1000인데 경우가 3가지니 완탐안됨 -> dp -> r칠했다면 g칠하거나 b칠하거나
	// 3가지 칠한 경우로 dp 재귀를 다 돌렸는데 안돼서 구글링함

	static int n;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[][] color;
	static int[][] dp;

	static int min; // 출력값

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 집의 수
		color = new int[n][3]; // 0: r 1: g 2: b
		dp = new int[n][3]; // 무슨 집에 무슨색 그리고 값은 저장된 최소비용

		// input
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			color[i][0] = Integer.parseInt(st.nextToken()); // r
			color[i][1] = Integer.parseInt(st.nextToken()); // g
			color[i][2] = Integer.parseInt(st.nextToken()); // b
		}

		min = 1_000_000;

		// 첫번째집 칠하기
		for (int i = 0; i < 3; i++) { // r/g/b 3가지 경우의 수
			for (int j = 0; j < 3; j++) {
				if (i == j) { // 3가지 색상 첫번째집 입력받은 값으로 저장
					dp[0][j] = color[0][i];
				}else { 
					dp[0][j] = 1_000_000; // 첫번째집말고는 비용의 최댓값으로 설정해두기
				}
			}

			for (int j = 1; j < n; j++) { // 다음집부터 마지막집까지 r/g/b 모든 경우 칠하기
				dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + color[j][0]; // 전집 g,b 중 최솟값과 r칠한 비용 더하기
				dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + color[j][1];
				dp[j][2] = Math.min(dp[j - 1][1], dp[j - 1][0]) + color[j][2];

			}
			
			// 조건!!
			// 0번째집 색과 n-1번째 집색이 같으면 칠할 수 없음
			for (int j = 0; j < 3; j++) {
				if (i == j) continue; // 같을 때 체크안함
				min = Math.min(dp[n-1][j], min); // 마지막 집 r/g/b값 다를 때 그 중 최솟값 저장
			}
			
		}

		System.out.println(min);
	}

}
