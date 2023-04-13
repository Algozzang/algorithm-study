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
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		List<int[]>[] edges = new ArrayList[V + 1];
		for (int i = 0; i < edges.length; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges[start].add(new int[] { start, end, cost });
		}
		int[] distance = new int[V + 1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		int result = Integer.MAX_VALUE;
		for (int start = 1; start <= V; start++) {
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[start] = 0;
			for (int[] edge : edges[start]) {
				pq.add(edge);
			}
			while (!pq.isEmpty()) {
				int[] now = pq.poll();
				if (distance[now[1]] > distance[now[0]] + now[2]) {
					distance[now[1]] = distance[now[0]] + now[2];
					for (int[] edge : edges[now[1]]) {
						if (start == edge[1]) {
							result = Math.min(result, distance[edge[0]] + edge[2]);
						}
						if (distance[edge[0]] + edge[2] < result) {
							pq.add(edge);
						}
					}
				}
			}
		}
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
}
