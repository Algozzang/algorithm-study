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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 도시의 수
		int M = Integer.parseInt(st.nextToken()); // 도로의 수
		int K = Integer.parseInt(st.nextToken()); // 포장 가능한 도로 수
		long[][] distance = new long[N][1];
		for (int i = 1; i < N; i++) {
			Arrays.fill(distance[i], Long.MAX_VALUE);
		}
		List<int[]>[] roads = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			roads[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int city1 = Integer.parseInt(st.nextToken()) - 1;
			int city2 = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			roads[city1].add(new int[] { city1, city2, cost, 0 });
			roads[city2].add(new int[] { city2, city1, cost, 0 });
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[3] == o2[3] ? o1[2] - o2[2] : o1[3] - o2[3]);

		pq.add(new int[] { 0, 0, 0, 0 });
		long min = Long.MAX_VALUE;
		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			System.out.println(Arrays.toString(now));
			for (int[] road : roads[now[1]]) {
				if (distance[road[1]][0] > distance[road[0]][0] + road[2] && min > distance[road[0]][0] + road[2]) {
					distance[road[1]][0] = distance[road[0]][0] + road[2];
					pq.add(new int[] { road[0], road[1], road[2], now[3] });
					if (now[3]+1<=K) {
						pq.add(new int[] { road[0], road[1], 0, now[3] + 1 });						
					}
				}
				if (now[1]==N-1) {
					System.out.println(distance[N-1][0]);
				}
			}
		}
		System.out.println(min);
	}
}