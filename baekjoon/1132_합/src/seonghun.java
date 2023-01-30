import java.util.Arrays;
import java.util.Scanner;

public class seonghun {

	static int N;
	static String[] numbers;
	static long[] scores = new long[10]; // A~J의 자릿수 비중 표시
	static boolean[] nonZero = new boolean[10]; // A~J 0값 가능 여부

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// initialize
		N = scanner.nextInt();
		numbers = new String[N];
		for (int i = 0; i < N; i++)
			numbers[i] = scanner.next();

		scanner.close();

		// 각 알파벳에 대해 자릿수에 따른 비중 계산
		for (String n : numbers) {
			int len = n.length();
			for (int i = len - 1; i >= 0; i--) {
				int idx = n.charAt(i) - 'A';
				scores[idx] += Math.pow(10, len - i - 1);
				// 첫째 자리이면 0이 될 수 없음
				if (i == 0) {
					nonZero[idx] = true;
				}
			}
		}

		// 0값이 될 수 있으며 비중이 가장 작은 알파벳을 찾기
		int idx = -1;
		long minScore = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			if (!nonZero[i] && scores[i] < minScore) {
				minScore = scores[i];
				idx = i;
			}
		}
		if (idx != -1) {
			scores[idx] = 0; // 0으로 할당할 알파벳 비중을 0으로
		}

		// 오름차순으로 정렬하고 인덱스에 대한 곱 계산
		Arrays.sort(scores);
		Arrays.setAll(scores, i -> i * scores[i]);

		// result
		System.out.println(Arrays.stream(scores).sum());
	}

}