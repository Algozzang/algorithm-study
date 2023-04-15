import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Seonghun {

	static int N, K;
	static int length; // 목표 길이
	static String number; // 입력한 숫자
	static StringBuilder stack = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		length = N - K;
		number = br.readLine();

		// solution
		for (int i = 0; i < N; i++) {
			char n = number.charAt(i);
			// 스택의 가장 위의 숫자가 현재 순서의 숫자보다 작으면 pop
			while (0 < K && 0 < stack.length() && stack.charAt(stack.length() - 1) < n) {
				stack.deleteCharAt(stack.length() - 1);
				K--;
			}
			stack.append(n);
		}

		// result
		System.out.println(stack.substring(0, length));
	}

}
