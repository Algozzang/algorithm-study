import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> s = new Stack<>();
		int answer = 0;
		s.add(0);
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i <= N; i++) {
			int x;
			int y = 0;
			if (i<N) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());				
			}
			int before = s.peek();
			if (y>before) {
				s.add(y);
			}
			if (y<before) {
				while (!s.isEmpty()&&s.peek()>y) {
					s.pop();
					answer++;					
				}
				if (s.peek()<y) {
					s.add(y);
				}
			}
		}
		System.out.println(answer);
	}
}
