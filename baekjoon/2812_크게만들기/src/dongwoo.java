import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Deque<Character> deq = new ArrayDeque<>();
		String text = br.readLine();
		int k = 0;
		for (int i = 0; i < N; i++) {
			char c = text.charAt(i);
			if (deq.isEmpty() || deq.peekLast() >= c || k >= K) {
				deq.addLast(c);
			} else {
				while (!deq.isEmpty() && deq.peekLast() < c && k < K) {
					deq.pollLast();
					k++;
				}
				deq.addLast(c);
			}
		}
		for (int i=0; i<N-K; i++) {
			sb.append(deq.pollFirst());
		}
		System.out.println(sb);
	}
}
