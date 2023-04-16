import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Hyekyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()), N, k = 1000000007;
		long cost, a, b, energy;
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Long> pq = new PriorityQueue<>();
		StringTokenizer st;
		for (int tc = 0; tc < T; tc++) {
			cost = 1;
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				pq.add(Long.parseLong(st.nextToken()));

			while (pq.size() > 1) {
				// 에너지 작은 두개 곱해주고 다시 넣어주기
				a = pq.poll();
				b = pq.poll();
				energy = a * b;
				cost = (cost % k) * (energy % k) % k;
				pq.add(energy);
			}
			sb.append(cost + "\n");
			pq.clear();
			// 큐 안비워주면 참사
		}
		System.out.println(sb.toString());
	}

}
