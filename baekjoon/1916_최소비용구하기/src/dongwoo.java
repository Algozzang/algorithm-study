import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class dongwoo {

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		List<List<Bus>> buses = new ArrayList<>(N + 1);	// 출발지 별로 버스 관리 
		for (int i = 0; i < N + 1; i++) {
			buses.add(new ArrayList<Bus>());
		}

		int costs[] = new int[N + 1];				// 각 지역으로 가는 최소 비용 저장 
		Arrays.fill(costs, Integer.MAX_VALUE);

		for (int i = 0; i < M; i++) {				// 버스 입력 
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			buses.get(from).add(new Bus(from, to, cost));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		PriorityQueue<Bus> pq = new PriorityQueue<Bus>();		// 버스로 우선순위 큐(그냥 큐 쓰면 시간초과)
		pq.addAll(buses.get(start));
		costs[start] = 0;
		while (!pq.isEmpty()) {
			Bus bus = pq.poll();
			if (costs[bus.start] < costs[bus.end] - bus.cost) { // 오버플로우 조심 
				pq.addAll(buses.get(bus.end));
				costs[bus.end] = costs[bus.start] + bus.cost;
			}
		}
		System.out.println(costs[end]);
	}

}

class Bus implements Comparable<Bus> {
	int start;
	int end;
	int cost;

	public Bus(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Bus target) {
		return this.cost <= target.cost ? -1 : 1;	// (매개변수보다 this가)1이면 크다 -1이면 작다. 0은 같음 
	}
}