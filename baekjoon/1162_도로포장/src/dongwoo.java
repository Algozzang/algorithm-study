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
		long[][] distance = new long[N + 1][K + 2];
		for (int i = 2; i < N + 1; i++) {
			Arrays.fill(distance[i], Long.MAX_VALUE);
		}
		List<int[]>[] roads = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			roads[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int city1 = Integer.parseInt(st.nextToken());
			int city2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			roads[city1].add(new int[] { city1, city2, cost, 0 });
			roads[city2].add(new int[] { city2, city1, cost, 0 });
		}
		// 포장 안하는것, 가까운 거리 우선 다익스트라
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[3] == o2[3] ? o1[2] - o2[2] : o1[3] - o2[3]);
		pq.add(new int[] { 0, 1, 0, 0 });
		long min = Long.MAX_VALUE;	// 현재까지 목적지 도달 했을때 든 시간중 가장작은값 저장 
		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			for (int[] road : roads[now[1]]) {
				// 비포장 
				if (distance[road[1]][now[3]] > distance[road[0]][now[3]] + road[2]
						&& min > distance[road[0]][now[3]] + road[2]) {
					distance[road[1]][now[3]] = distance[road[0]][now[3]] + road[2];
					pq.add(new int[] { road[0], road[1], road[2], now[3] });
				}
				// 포장 
				if (now[3] + 1 <= K && distance[road[1]][now[3] + 1] > distance[road[0]][now[3]]
						&& min > distance[road[0]][now[3]]) {
					distance[road[1]][now[3] + 1] = distance[road[0]][now[3]];
					if (distance[road[1]][now[3] + 1] > distance[road[1]][K + 1]) {
						continue;
					}
					distance[road[1]][K + 1] = Math.min(distance[road[1]][K + 1], distance[road[1]][now[3] + 1]);
					pq.add(new int[] { road[0], road[1], road[2], now[3] + 1 });
				}
				// min 값 업데이트 
				if (road[1] == N) {
					min = Math.min(min, Math.min(distance[N][now[3] + 1], distance[N][now[3]]));
				}
			}
		}
		System.out.println(min);
	}
}