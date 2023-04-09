import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Seonghun {

	static int N; // 도시의 개수
	static int M; // 버스 노선의 개수
	static Bus[] bus; // 버스 노선 정보
	static long[] distance; // 1번 도시에서 각 도시까지의 최소 시간

	static class Bus {
		int from; // 시작 도시
		int to; // 도착 도시
		int time; // 걸리는 시간

		Bus(int from, int to, int time) {
			this.from = from;
			this.to = to;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		bus = new Bus[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			bus[i] = new Bus(from, to, time);
		}
		distance = new long[N + 1];
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[1] = 0;

		// solution (Bellman-Ford)
		for (int i = 0; i < N - 1; i++) {
			for (Bus b : bus) {
				if (distance[b.from] != Long.MAX_VALUE && b.time + distance[b.from] < distance[b.to]) {
					distance[b.to] = b.time + distance[b.from];
				}
			}
		}

		// 음의 사이클 여부 파악
		for (Bus b : bus) {
			if (distance[b.from] != Long.MAX_VALUE && b.time + distance[b.from] < distance[b.to]) {
				System.out.println(-1); // 음의 사이클!
				return;
			}
		}

		// result
		for (int i = 2; i <= N; i++) {
			long d = distance[i];
			System.out.println(d != Long.MAX_VALUE ? d : -1);
		}
	}

}
