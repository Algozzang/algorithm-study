import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JIHANEOL {
	static int N,M, dp[],visit[];
	static BufferedReader br;
	static StringTokenizer st;
	static int si(String s) {return Integer.valueOf(s);}
	
	static class path implements Comparable<path>{
		int n;
		int c;
		
		public path(int n, int c) {
			this.n=n;
			this.c=c;
		}
		@Override
		public int compareTo(path o) {
			// TODO Auto-generated method stub
			return this.c - o.c;
		}
	}
	static ArrayList<path>[] graph;
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		N = si(br.readLine());
		M = si(br.readLine());
		
		graph = new ArrayList[N+1];
		dp = new int[N+1];
		visit = new int[N+1];
		for(int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i=0; i<M; i++) { // 그래프 값 넣기
			st = new StringTokenizer(br.readLine());
			int from = si(st.nextToken());
			int to = si(st.nextToken());
			int cost = si(st.nextToken());
			graph[from].add(new path(to, cost));
		}
		st = new StringTokenizer(br.readLine());
		int start = si(st.nextToken());
		int end = si(st.nextToken());
		Arrays.fill(dp, Integer.MAX_VALUE); // 최대치로 충전
		
		dik(start);
		System.out.println(dp[end]);
	}
	public static void dik(int node) {
		PriorityQueue<path> pq = new PriorityQueue<>(); //가치가 가장 최소인놈 뽑아내기
		dp[node] = 0; // 0으로
		pq.add(new path(node, 0));
		while(!pq.isEmpty()) {
			path now = pq.poll();
			if(visit[now.n]==1) continue;
			visit[now.n]= 1;
			for(path next:graph[now.n]) {
				if(dp[next.n]>dp[now.n]+next.c) { // 값 갱신
					dp[next.n] = dp[now.n]+next.c;
					pq.add(new path(next.n, dp[next.n]));
				}
			}
		}
	}	
}