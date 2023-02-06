import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class seonghun {

	static int N;
	static int[] values;
	static int twoSum;
	static int answer1, answer2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// initialize
		N = Integer.parseInt(br.readLine());
		values = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		// 오름차순 정렬
		Arrays.sort(values);

		// 두 포인터가 만날때까지 이동
		int point1 = 0;
		int point2 = N - 1;
		answer1 = values[point1];
		answer2 = values[point2];
		twoSum = Math.abs(answer1 + answer2);

		while (point1 < point2) {
			// 합의 절댓값을 비교
			int sum = values[point1] + values[point2];
			if (Math.abs(sum) <= twoSum) {
				answer1 = values[point1];
				answer2 = values[point2];
				twoSum = Math.abs(sum);
			}
			// 합이 음수이면 왼쪽, 양수이면 오른쪽 포인터 이동
			if (sum < 0) {
				point1++;
			} else if (sum > 0) {
				point2--;
			} else {
				break;
			}
		}

		// result
		System.out.printf("%d %d", answer1, answer2);
	}

}
