import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Hyekyoung {
	static int N, M, K;
	static List<Node>[] city;
	static long time[][], INF = Long.MAX_VALUE;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int from, to, cost;
		long min = Long.MAX_VALUE;
		city = new ArrayList[N + 1];
		time = new long[N + 1][K + 1];
		visited = new boolean[N + 1][K + 1];

		for (int i = 2; i <= N; i++)
			Arrays.fill(time[i], INF);

		for (int i = 1; i <= N; i++) {
			city[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			city[from].add(new Node(to, cost, 0));
			city[to].add(new Node(from, cost, 0));
		}
		dijkstra();
		for (int i = 0; i <= K; i++) {
			min = Math.min(min, time[N][i]);
		}
		System.out.println(min);
		// time[N][K]가 최소가 안되는 경우가 있나보다 k보다 n이 작으면 그럴수도 있나
	}

	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0, 0));
		Node cur;
		int idx, cnt;
		while (!pq.isEmpty()) {
			cur = pq.poll();
			idx = cur.idx;
			cnt = cur.cnt;

			if (visited[idx][cnt]) continue;
			visited[idx][cnt] = true;

			for (Node next : city[idx]) {
				if (time[next.idx][cnt] > time[idx][cnt] + next.cost) {
					time[next.idx][cnt] = time[idx][cnt] + next.cost;
					pq.offer(new Node(next.idx, time[next.idx][cnt], cnt));
					// 포장 안하고 가기
				}
				if (cnt < K && time[next.idx][cnt + 1] > time[idx][cnt]) {
					time[next.idx][cnt + 1] = time[idx][cnt];
					pq.offer(new Node(next.idx, time[next.idx][cnt + 1], cnt + 1));
					// 포장하기
				}
			}
		}

	}

	static class Node implements Comparable<Node> {
		int idx, cnt;
		long cost;

		public Node(int idx, long cost, int cnt) {
			super();
			this.idx = idx;
			this.cost = cost;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return (int) (this.cost - o.cost);
		}
	}
}
