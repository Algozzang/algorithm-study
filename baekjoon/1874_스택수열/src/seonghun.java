import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	static int n, number;
	static boolean possible;
	static Stack<Integer> stack;
	static StringBuilder answer;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// initialize
		n = sc.nextInt();
		possible = true;
		stack = new Stack<>();
		answer = new StringBuilder();
		
		// solution
		int seq = 1; // 현재 스택에 추가할 숫자
		for (int i = 0; i < n; i++) {
			number = sc.nextInt();
			
			// 입력한 숫자와 seq를 비교
			if (seq <= number) {
				while (seq <= number) {
					stack.push(seq++);
					answer.append('+');
				}
				stack.pop();
				answer.append('-');
			} else {
				if (stack.peek() == number) {
					stack.pop();
					answer.append('-');
				} else
					possible = false;
			}
		}

		// result
		if (possible)
			System.out.println(Stream.of(answer.toString().split("")).collect(Collectors.joining("\n")));
		else
			System.out.println("NO");
	}

}