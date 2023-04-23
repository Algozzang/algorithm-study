import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Seonghun {

	static int T; // 테스트 케이스 수
	static int N; // 슬라임의 수
	static final long M = 1000000007;
	static PriorityQueue<Long> slime;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// initialize
			long energy = 1L;
			N = Integer.parseInt(br.readLine());
			slime = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				slime.add(Long.parseLong(st.nextToken()));
			}

			// solution
			while (slime.size() > 1) {
				long value = slime.poll() * slime.poll();
				energy = (energy * (value % M)) % M;
				slime.add(value);
			}
			answer.append(energy + "\n");
		}

		// result
		System.out.println(answer);
	}

}
