import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class seonghun {

	static int K, N;
	static Long[] primes;
	static Queue<Long> pq = new PriorityQueue<Long>();
	static long answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		primes = new Long[K];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			pq.offer(primes[i] = Long.parseLong(st.nextToken()));
		}

		// solution
		while (N-- > 0) {
			answer = pq.poll();
			for (long prime : primes) {
				pq.offer(answer * prime);
				if (answer % prime == 0) {
					break;
				}
			}
		}

		// result
		System.out.println(answer);
	}

}
