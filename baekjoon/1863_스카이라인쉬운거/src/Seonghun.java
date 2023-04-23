import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Seonghun {

	static int n;
	static int[] height; // 건물들의 높이
	static Stack<Integer> stack = new Stack<>();
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// initialize
		n = Integer.parseInt(br.readLine());
		height = new int[n + 1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			height[i] = y;
		}
		height[n] = 0;
		stack.push(0);

		// solution
		for (int h : height) {
			while (stack.peek() > h) {
				stack.pop();
				answer++;
			}
			if (stack.peek() < h) {
				stack.push(h);
			}
		}

		// result
		System.out.println(answer);
	}

}
