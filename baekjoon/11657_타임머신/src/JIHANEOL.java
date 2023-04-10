import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class JIHANEOL {
	static BufferedReader br;
	static StringTokenizer st;
	static class Edge{
		int s,e,cost;

		public Edge(int s, int e, int cost) {
			super();
			this.s = s;
			this.e = e;
			this.cost = cost;
		}
		
	}
	static final int INF= Integer.MAX_VALUE;
	static ArrayList<Edge> list;
	static long distance[];
	public static void main(String[] args) throws Exception{
		br  = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int V = Integer.valueOf(st.nextToken());
		int E = Integer.valueOf(st.nextToken());
		
		list = new ArrayList();
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.valueOf(st.nextToken());
			int e = Integer.valueOf(st.nextToken());
			int c = Integer.valueOf(st.nextToken());
			list.add(new Edge(s, e, c));
		}
		
		distance = new long[V+1];
		Arrays.fill(distance, INF);
		distance[1]=0; 
		
		// 벨만 씨 사용 , 엣지를 중심으로 동작한다.
		for(int i=1; i<V; i++) { // V-1번 돈다. 자신 제외
			for(int j=0; j<E; j++) { // 간선 만큼.
				Edge now = list.get(j);
				
				if(distance[now.s]!= INF && distance[now.e]>distance[now.s]+now.cost) {
					distance[now.e]=distance[now.s]+now.cost;
				}
			}
		}
		boolean flag = false;
		for(int j=0; j<E; j++) { // 간선 만큼. 다시 한번 돌려준다.
			Edge now = list.get(j);
			if(distance[now.s]!= INF && distance[now.e]>distance[now.s]+now.cost) {
				flag=true;
				break;
			}
		}
		
		if(flag) {
			System.out.println("-1");
		}else {
			for(int i=2; i<=V; i++) {
				if(distance[i]==INF) {
					System.out.println("-1");
				}else {
					System.out.println(distance[i]);
				}
			}
		}
		
	}
}












