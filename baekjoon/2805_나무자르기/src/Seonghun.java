import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Seonghun {

	static int N; // 나무의 수
	static int M; // 가져가려고 하는 나무의 길이
	static Long[] H; // 나무들의 높이
	static long answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = new Long[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			H[i] = Long.parseLong(st.nextToken());
		}
		H[N] = 0L;

		// 내림차순 정렬
		Arrays.sort(H, Collections.reverseOrder());
		
		// 구한 나무 높이의 합
		long sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += (H[i - 1] - H[i]) * i;
			if (sum >= M) {
				answer = H[i] + (sum - M) / i;
				break;
			}
		}

		// result
		System.out.println(answer);
	}

}
