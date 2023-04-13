import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Seonghun {

	static int N; // 도시의 수
	static int M; // 도로의 수
	static int K; // 포장할 도로의 수
	static long[][] T; // 서울에서 다른 도시로의 최소 시간
	static List<Destination>[] path; // 도로 인접 리스트
	static PriorityQueue<Destination> heap = new PriorityQueue<>((o1, o2) -> Long.compare(o1.time, o2.time));

	static class Destination {
		int city;
		long time;
		int packing;

		Destination(int city, long time, int packing) {
			this.city = city;
			this.time = time;
			this.packing = packing;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		T = new long[N + 1][K + 1];
		path = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			path[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			path[a].add(new Destination(b, time, -1));
			path[b].add(new Destination(a, time, -1));
		}

		// solution
		for (int i = 2; i <= N; i++) {
			Arrays.fill(T[i], Long.MAX_VALUE);
		}
		heap.add(new Destination(1, 0, 0));
		while (!heap.isEmpty()) {
			Destination curr = heap.poll();
			if (T[curr.city][curr.packing] < curr.time) {
				continue;
			}
			for (Destination next : path[curr.city]) {
				// 포장 안해
				if (curr.time + next.time < T[next.city][curr.packing]) {
					T[next.city][curr.packing] = curr.time + next.time;
					heap.add(new Destination(next.city, T[next.city][curr.packing], curr.packing));
				}
				// 포장 해
				if (curr.packing < K && curr.time < T[next.city][curr.packing + 1]) {
					T[next.city][curr.packing + 1] = curr.time;
					heap.add(new Destination(next.city, T[next.city][curr.packing + 1], curr.packing + 1));
				}
			}
		}

		// result
		System.out.println(Arrays.stream(T[N]).min().getAsLong());
	}

}
