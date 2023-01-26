
import java.util.ArrayList;
import java.util.Scanner;

public class 지한얼 {
		static ArrayList<int []> arr;
//		static boolean[] visited;
		static int L,n,answer;
		
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int test=1; test<=t; test++) {
			answer=0;
			n=sc.nextInt();
			L=sc.nextInt();
			
//			visited = new boolean[n];
			arr = new ArrayList<>();
			for(int i=0; i<n;i++) { // 값넣기
				arr.add(new int[]{sc.nextInt(),sc.nextInt()});
			
			}
			
			dfs(0, 0,0);
			
			System.out.println("#"+test+" "+answer);
			
		
		}
		}
	
	 static void dfs(int total_T,int total_K,int depth) {
		 if(total_K>L) return ;
		 
		 if (depth == n) {
				if (total_T > answer) {
					answer=total_T;
				}
				return;
			}
		 
		 int[] temp=arr.get(depth);
		 
		 dfs(total_T+temp[0], total_K+temp[1], depth+1);
		 dfs(total_T, total_K, depth+1);
		 
		 
		 
//		 for(int i=0; i<arr.size();i++) { 
//			 if(!visited[i]) {
//				 visited[i]=true;
//				int[] temp=arr.get(i);
//				dfs(total_T+temp[0],total_K+temp[1],depth+1);
//				visited[i]=false;
//				
//				if(answer<total_T) answer=total_T;
//			 }
//		 }
//		 
		 
	 }
				
	}

