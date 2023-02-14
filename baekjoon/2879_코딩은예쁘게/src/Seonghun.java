import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Seonghun {

	static int N;
	static int[] now; // 현재 탭의 개수들
	static int[] tar; // 올바른 탭의 개수들
	static int[] gap; //
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// initialize
		N = Integer.parseInt(br.readLine());
		now = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		tar = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		gap = new int[N + 2];
		for (int i = 0; i < N; i++) {
			gap[i + 1] = tar[i] - now[i];
		}

		// solution
		int maxVal = 0;
		for (int i = 1; i <= N + 1; i++) {
			if (gap[i - 1] * gap[i] > 0) { // 연속된 수의 부호가 바뀌지 않는 경우
				if (Math.abs(gap[i]) > maxVal) {
					maxVal = Math.abs(gap[i]);
				} else {
					answer += (maxVal - Math.abs(gap[i]));
					maxVal = Math.abs(gap[i]);
				}
			} else { // 연속된 수의 부호가 바뀌는 경우
				answer += maxVal;
				maxVal = Math.abs(gap[i]);
			}
		}

		// result
		System.out.println(answer);
	}

}
