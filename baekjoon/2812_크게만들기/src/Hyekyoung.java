import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Hyekyoung {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), k = sc.nextInt(), size = n - k, num;
		String s = sc.next();
		StringBuilder sb = new StringBuilder();
		Deque<Integer> queue = new ArrayDeque<>();
		queue.add(s.charAt(0) - '0');
		for (int i = 1; i < n; i++) {
			num = s.charAt(i) - '0';
			while (!queue.isEmpty() && queue.getLast() < num && k > 0) {
				queue.pollLast();
				k--;
			}
			queue.add(num);
		}

		for (int i=0; i<size; i++) {
			sb.append(queue.pollFirst());
		}
		System.out.println(sb.toString());
	}
}

