import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class JIHANEOL {
	static int dp[],N,answer,K;
	static List<int[]> cost; //0 작은점프 , 1 큰점프
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.valueOf(br.readLine()); // 돌의 개수 1~N
		answer=Integer.MAX_VALUE;
		cost = new ArrayList<>(); 
		cost.add(new int[] {});
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());
			cost.add(new int[] {a,b});
		}
		K = Integer.valueOf(br.readLine());
		dp = new int[N+1];
		dp[1]=0;
		DP(2, false);
		if(N==1) {
			System.out.println(0);
		}else {
			System.out.println(answer);
		}
	}
	public static void DP(int n,boolean flag) {
		if(n==N+1) {
			answer=Math.min(answer,dp[n-1]);
			
			return;
		}
		if(n ==2) {
			dp[2] = cost.get(1)[0];
			DP(n+1, flag);
			return;
		}
		if(n ==3) {
			dp[3] = Math.min(dp[2]+cost.get(2)[0], cost.get(1)[1]);
			DP(n+1, flag);
			return;
		}
		// k 선택 안한다.
			
				dp[n] =Math.min(dp[n-1]+cost.get(n-1)[0], dp[n-2]+cost.get(n-2)[1]);
				DP(n+1, flag);				
			
			// k 선택 한다.
			if(!flag) {
				dp[n] = Math.min(dp[n-3]+K, Math.min(dp[n-1]+cost.get(n-1)[0], dp[n-2]+cost.get(n-2)[1]));
				DP(n+1, !flag);			
			}
	}
}
