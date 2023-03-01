import java.io.*;
import java.util.*;

public class JIHANEOL {
	static List<int[]> arr;
	static BufferedReader br;
	static StringTokenizer st;
	static int dp[],answer;
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		arr = new ArrayList<>();
		dp = new int[501];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr.add(new int[] {Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken())});
		}
		arr.sort((o1,o2)->o1[0]-o2[0]);
		for(int i=0; i<arr.size();i++) {
			for(int j=0; j<i; j++) {
				if(arr.get(j)[1]<arr.get(i)[1]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
					if(answer<dp[i]) {
						answer=dp[i];
					}
				}
			}
		}
		System.out.println(arr.size()- answer-1);
		
	}
	
}






