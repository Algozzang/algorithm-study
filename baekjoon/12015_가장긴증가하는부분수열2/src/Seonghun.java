import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Seonghun {

	static int N;
	static int[] numbers;
	static List<Integer> LIS = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// initialize
		N = Integer.parseInt(br.readLine());
		numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		// solution
		LIS.add(numbers[0]);
		for (int i = 1; i < N; i++) {
			int len = LIS.size();
			int number = numbers[i];
			if (number > LIS.get(len - 1)) {
				LIS.add(number);
			} else {
				int idx = Collections.binarySearch(LIS, number);
				if (idx < 0) {
					LIS.set(-idx - 1, number);
				}
			}
		}

		// result
		System.out.println(LIS.size());
	}

}
