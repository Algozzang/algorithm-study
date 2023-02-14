import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Minju {
	/*
	 * Dijkstra 공부함
	 * 
	 * 시간 초과 
	 * => visited bool배열로 방문체크 추가
	 * 
	 * INF 값
	 * => 간선 가중치의 최댓값 * (점정개수 -1) 보다 큰 값을 사용해야 함
	 * 비용의 최댓값은 스타트노드와 엔드노드가 일직선상으로 가장 멀게 있는 경우!
	 * 
	 */
	static int n,m;
	static PriorityQueue<Node> pq = new PriorityQueue<>((c1,c2)-> Integer.compare(c1.cost, c2.cost)); // 비용 낮은 오름차순 정렬
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>(); // 어떤노드에서 -> Node 이동할곳
	static int[] costSum; // 최소 비용 저장할 배열
	static boolean[] visited; // 방문하지 않은 노드 체크
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		/* input */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		costSum = new int[n+1]; //1 ~n 까지 최소비용 저장
		visited = new boolean[n+1]; //1 ~n 까지 방문 여부 저장
		Arrays.fill(costSum, 100000*(n-1)); //최솟값 판별하기 위해 비용의 최댓값으로 init
		
		for(int i =0;i<n+1;i++) { // 0~n 까지 노드 생성
			graph.add(new ArrayList<Node>()); 
		}
		StringTokenizer st;
		// 그래프에 추가하기
		for(int i =0;i<m;i++) { // m 개수만큼
			st = new StringTokenizer(br.readLine());
			graph.get(Integer.parseInt(st.nextToken())).add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))); //연결 노드 , 비용
		}
	
		
		st = new StringTokenizer(br.readLine());
		int sNode = Integer.parseInt(st.nextToken());
		int eNode = Integer.parseInt(st.nextToken());
		
		costSum[sNode] = 0;
		pq.add(new Node(sNode, 0)); //입력 받은 스타트 노드를 넣고 자기자신이므로 비용은 0
		
		// 우선순위큐 다익시작
		while(!pq.isEmpty()) {
			
			Node node = pq.poll();  // 현재 가장 작은 값을 가지고 있는 노드를 꺼내서
			//다른 노드들 비용 계산해주기
			if(visited[node.index]) continue; // 방문했으면 더이상 그 노드로는 탐색하지 않음
			visited[node.index] = true;
			// 지금 노드에서 갈 수 있는 노드들 계산
			for(int i =0;i<graph.get(node.index).size();i++) {
				Node nextNode = graph.get(node.index).get(i); //연결된 노드 가져오기
				if( node.cost + nextNode.cost <costSum[nextNode.index]) { //계산한 값이 현재 들어있는 비용보다 작으면 update
					costSum[nextNode.index] = node.cost + nextNode.cost;
					//업데이트되었으면 우선순위큐에 넣기
					pq.add(new Node(nextNode.index, costSum[nextNode.index])); // 기존 값을 삭제하지 않고 넣는 이유는 다른 경로 탐색하면서 더 최소의 경우가 나올 수 있기 때문
				}
			}
			
		}
		
		System.out.println(costSum[eNode]);
		
	}
}

class Node implements Comparable<Node>{ //노드 번호와 비용 저장

	int index;
	int cost;
	public Node(int index, int cost) {
		super();
		this.index = index;
		this.cost = cost;
	}
	@Override
	public int compareTo(Node o) {
		//비용이 적으면 높은 우선순위를 가짐
		if(this.cost < o.cost) return -1;
		return 1;
	}
	
}