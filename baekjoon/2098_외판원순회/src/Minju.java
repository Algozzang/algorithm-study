import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Minju {
	// 외판원 순회 도시가 10개 이하라면 10!이니까 완탐으로 해결가능
	// 문제는 도시수가 <= 16개
	// 같은 경로라면 출발노드 어디서 하든지 똑같으니 임의 하나로 고정시킬 수 있음
	// dfs 를 돌면서 중간에 겹치는 중복경로들을 dp로 해결
	
	static int n , map[][], dp[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		dp = new int[n][(1<<n)-1]; // 도시 경우의수는 (1<<n)-1 : 4개도시 다 방문이라면 1111이니까 15-1
		StringTokenizer st;
		/* input */
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int w = Integer.valueOf(st.nextToken());
				map[i][j] = w;
			}
		}
		// dp init
		for(int i = 0; i < n; i++) {
			Arrays.fill(dp[i],Integer.MAX_VALUE);
		}
		
		System.out.println(dfs(0,1));
	}

	private static int dfs(int city, int visited) { // dfs

		if(visited == (1<<n)-1) { // 모든 도시 방문한 경우
			return map[city][0]; // 현재도시 -> 시작도시 이동거리
		}
		if(dp[city][visited] != Integer.MAX_VALUE) { //dp 값있으면
			return dp[city][visited];
		}
		for(int i =0;i<n;i++) {
			if((visited & (1<<i)) == 0 && map[city][i] != 0) { // 도시재방문
				dp[city][visited] = Math.min(dp[city][visited], dfs(i,visited | (1<<i))+map[city][i]);
			}
		}
		return dp[city][visited];
	}
}
