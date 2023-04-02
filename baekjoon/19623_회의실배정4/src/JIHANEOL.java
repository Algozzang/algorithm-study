import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class JIHANEOL {
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] board ;
	public static void main(String[] args) throws IOException {
		br =new  BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		board = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			board[i][0] = Integer.valueOf(st.nextToken());
			board[i][1] = Integer.valueOf(st.nextToken());
			board[i][2] = Integer.valueOf(st.nextToken());
		}
		
		// 끝나는 시간 오름차순 정렬 
		Arrays.sort(board,(o1,o2)->{
		    return	o1[1]-o2[1];
		});
		int[] dp = new int[N];
		dp[0] = board[0][2];
		
		for(int i=1; i<N; i++) {
			int mid = binarySearch(0,i,board[i][0]);
			// -1 이면 내가 처음이기 때문에 전에까지 최대 인원인 디피와 내 인원을 비교해줌
			if(mid==-1) {
				dp[i] = Math.max(board[i][2],dp[i-1]); 
			// 전에까지의 최대 디피와 (내인원+내가 추가할수있는 디피) ?
			}else {
				dp[i] = Math.max(dp[i-1],board[i][2]+dp[mid]);
			}
			
		}
		System.out.println(dp[N-1]);
		
	}
	public static int binarySearch(int s, int e, int target) {
		while(s<e) {
			int mid = (s+e)/2;
			if(target<board[mid][1]) {
				e=mid;
			}else {
				s=mid+1;
			}
		}
		return s-1;
		
	}

}
