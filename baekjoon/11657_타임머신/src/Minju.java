import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Minju { // 벨만 포드 

	static final int INF = 70000000;
	static Edge[] egdes;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		long[] minTime = new long[n+1];
		egdes = new Edge[m+1];
		
		
		Arrays.fill(minTime, INF);
		
		for(int i =0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			egdes[i] = new Edge(a,b,c);
		}
		
		// 1번에서 시작
		minTime[1] = 0;
		
		for(int i =0; i< n; i++) {
			// 모든 간선 확인
			for(int j =0;j<m;j++) {
				Edge cur = egdes[j];
				if(minTime[cur.s] == INF) continue;
				if(minTime[cur.e] > minTime[cur.s] + cur.time ) minTime[cur.e] = minTime[cur.s] + cur.time;
				
			}
		}
		// 간선한번더돌아서 음수 사이클 확인 
		for(int j =0;j<m;j++) {
			Edge cur = egdes[j];
			if(minTime[cur.s] == INF) continue;
			if(minTime[cur.e] > minTime[cur.s] + cur.time ) { // 사이클있네?
				System.out.println("-1\n");
				return;
			}
		}
		for(int i =2; i<= n; i++) {
			if(minTime[i] == INF) sb.append("-1\n"); // 못가는경로
			else sb.append(minTime[i]+"\n");
		}
		System.out.println(sb);

	}
	
	static class Edge{
		int s;
		int e;
		int time;
		public Edge(int s, int e, int time) {
			super();
			this.s = s;
			this.e = e;
			this.time = time;
		}
	}
}
