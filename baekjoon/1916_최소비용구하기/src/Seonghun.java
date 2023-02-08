import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Destination {
	
	int city;
	int cost;
	
	Destination(int city, int cost) {
		this.city = city;
		this.cost = cost;
	}
}

public class Seonghun {

	static int N; // 도시의 개수
	static int M; // 버스의 개수
	static int begin, end; // 출발 도시, 도착 도시
	static int[] minCost; // 각 
	static Map<Integer, List<Destination>> bus = new HashMap<>(); // 출발 도시에 대한 도착 도시 정보
	static Queue<Destination> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost)); // 도착 도시 정보에 대한 우선순위 큐
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// initialize
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		minCost = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int city1 = Integer.parseInt(st.nextToken());
			int city2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if (!bus.containsKey(city1)) {
				bus.put(city1, new ArrayList<>());
			}
			bus.get(city1).add(new Destination(city2, cost));
		}
		st = new StringTokenizer(br.readLine());
		begin = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		// dijkstra
		Arrays.fill(minCost, Integer.MAX_VALUE); // 모든 도시까지의 비용을 최대로 설정
		minCost[begin] = 0; // 출발 지점에 대해서는 비용을 0으로 설정
		pq.offer(new Destination(begin, 0)); // 출발 지점 등록
		while (!pq.isEmpty()) {
			// 출발지점에서 가장 비용이 적은 도시 정보 선택
			Destination now = pq.poll();
			// 현재 도시의 비용이 현재까지 계산한 최소 비용 이하인 경우에만 진행
			// -> 방문했던 도시는 방문하지 않으므로 중복을 제거
			if (now.cost <= minCost[now.city]) {
				// 현재 도시에서 갈 수 있는 도시들을 모두 탐색
				for (Destination next : bus.getOrDefault(now.city, new ArrayList<>())) {
					// 현재 도시를 통해 다음 도시로 갔을 때가 현재까지 계산한 출발지점에서 다음도시까지의 비용보다 작은 경우
					if (now.cost + next.cost < minCost[next.city]) {
						minCost[next.city] = now.cost + next.cost;
						pq.offer(new Destination(next.city, minCost[next.city]));
					}
				}
			}
		}
		
		// result
		System.out.println(minCost[end]);
	}
	
}
