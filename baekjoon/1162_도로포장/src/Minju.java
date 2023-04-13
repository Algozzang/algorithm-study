import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Minju { // 43% 시간초과코드
	// 포장하고 안하고를 해야되니까 2차원 minTime배열을 쓰자 <= 이생각은 힌트를 봄
	static StringBuilder sb = new StringBuilder();
	static List<ArrayList<Node>> list = new ArrayList<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->Long.compare(a.time, b.time));
	static long[][] minTime; 
	//static boolean[] visited;
	static final long INF = 600_000_000_000_000_000L;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 도시
		int m = Integer.parseInt(st.nextToken()); // 도로
		int kNum = Integer.parseInt(st.nextToken()); // 포장가능도로
		
		minTime = new long[n+1][kNum+1]; // k번까지 포장할수있음
		//visited = new boolean[n+1];
		
		for(int i =0;i<=n;i++) {
			for(int j =0;j<=kNum;j++) {
				minTime[i][j] = INF;
			}
		}
		
		for(int i =0;i<=m;i++) {
			list.add(new ArrayList<Node>());
		}
		
		for(int i =0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a =Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 양방향
			list.get(a).add(new Node(b,c,0));
			list.get(b).add(new Node(a,c,0));
			
		}
		// 시작노드 초기화
		minTime[1][0] = 0; 
		pq.add(new Node(1,0,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int curIdx = cur.e;
			int curK = cur.k;
			//if(visited[curIdx]) continue;
			//visited[curIdx] = true;
			
			for(int i=0;i<list.get(curIdx).size(); i++) {
				Node node = list.get(curIdx).get(i);
				if(minTime[node.e][curK] > minTime[curIdx][curK]+node.time) {
					minTime[node.e][curK] = minTime[curIdx][curK]+node.time;
					pq.add(new Node(node.e, minTime[node.e][curK],cur.k));
				}
				
				if( cur.k<kNum && minTime[node.e][curK+1] > minTime[curIdx][curK]) { // 포장가능함
					minTime[node.e][curK+1] = minTime[curIdx][curK];
					pq.add(new Node(node.e, minTime[node.e][curK+1],cur.k+1));
				}
			}
		}
		System.out.println(minTime[n][kNum]); // 무조건 포장한게 최소
	}
	
	static class Node {

		int e;
		long time;
		int k;
		
		public Node(int e, long time,int k) {
			super();
			this.e = e;
			this.time = time;
			this.k = k;
		}
	}
}
