import java.io.*;
import java.util.*;

public class JIHANEOL {
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] mp = { {0,2,2,2,2}, //행 왼발 , 열 오른발
					      {2,1,3,4,3},
					      {2,3,1,3,4},
					      {2,4,3,1,3},
					      {2,3,4,3,1}
						};
	static int[][][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		dp = new int[100001][5][5];
		
		for(int i=0; i<100001; i++) { // 큰수 만들어주기
			for (int j = 0; j < 5; j++) {
				for (int j2 = 0; j2 < 5; j2++) {
					dp[i][j][j2] = 100001*4; // 10만 길이에 최대 4만 넣어졌다 해도 이것보다 큰거는 안나온다.
				}
			}
		}
		int cnt =1;
		dp[0][0][0] = 0;
		while(true) {
			int num = Integer.valueOf(st.nextToken()); // 옮길 위치 값
			
			if(num==0) {
				break;
			}
			// 오른발 먼저
			for(int i=0; i<5; i++) {
				if(i==num) continue; // 양발이 같을수는없지요
				
				for(int j=0; j<5; j++) {
					dp[cnt][i][num] = Math.min(dp[cnt-1][i][j]+mp[j][num], dp[cnt][i][num]);
				}
				
			}
			// 왼발 
			for(int j=0; j<5; j++) {
				if(j==num) continue; // 양발이 같을수는없지요
				
				for(int i=0; i<5; i++) {
					dp[cnt][num][j] = Math.min(dp[cnt-1][i][j]+mp[num][i], dp[cnt][num][j]);
				}
				
			}
			cnt++;
		}
		cnt--;
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				answer = Math.min(answer, dp[cnt][i][j]);
			}
		}
		System.out.println(answer);
	}

}


