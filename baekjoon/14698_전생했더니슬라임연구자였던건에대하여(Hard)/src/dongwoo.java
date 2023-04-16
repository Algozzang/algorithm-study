import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class dongwoo {
	static int MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			long cost = 1;
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			PriorityQueue<Long> pq = new PriorityQueue<>();
			for (int i = 0; i < N; i++) {
				pq.add(Long.parseLong(st.nextToken()));
			}
			while (pq.size() > 1) {
				long a = pq.poll();
				long b = pq.poll();
				pq.add(a * b);
				cost *= (a % MOD * b % MOD);
				cost %= MOD;
			}
			sb.append(cost).append("\n");
		}
		System.out.println(sb);
	}
}
