import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Hyekyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), x, y, cnt = 0;
		Stack<Integer> stack = new Stack<>();
		StringTokenizer st;
		stack.push(0);
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			if (stack.isEmpty())
				stack.push(y);
			else {
				if (stack.peek() < y) {
					// 더 높은 건물이 들어오면 무조건 +1
					stack.push(y);
					cnt++;
				} else {
					// 낮은 건물이 들어오면 스택에서 하나씩 빼보고 다 빼고 나면
					// 스택 제일 위에 있는 애랑 같으면 이어진 건물, 아니면 새로운 건물 +1
					while (stack.peek() > y) {
						stack.pop();
					}
					if (stack.peek() != y) {
						stack.push(y);
						cnt++;
					}
				}
			}
		}
		System.out.println(cnt);
	}

}