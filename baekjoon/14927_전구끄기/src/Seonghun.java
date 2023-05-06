import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Seonghun {

	static int N;
	static int[] B; // 입력한 전구 상태
	static int[][] direct = { { -1, 0 }, { 0, -1 }, { 0, 0 }, { 0, 1 }, { 1, 0 } };
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// initialize
		N = Integer.parseInt(br.readLine());
		B = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if (st.nextToken().equals("1")) {
					B[i] |= (1 << j);
				}
			}
		}

		// 첫번째 줄의 전구 클릭 경우의 수를 모두 고려
		for (B[0] = 0; B[0] < (1 << (N + 1)); B[0] += 2) {
			int count = 0; // 스위치 누른 횟수
			int[] bulb = B.clone(); // 전구 초기상태 복사
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					// 위의 전구가 켜져있으면
					if ((bulb[r - 1] & (1 << c)) != 0) {
						// 스위치 누르기
						for (int d = 0; d < 5; d++) {
							int dr = r + direct[d][0];
							int dc = c + direct[d][1];
							if (1 <= dr && dr <= N && 1 <= dc && dc <= N) {
								bulb[dr] ^= (1 << dc);
							}
						}
						count++;
					}
				}
			}
			// 마지막 줄의 전구가 모두 꺼져있으면 끄기 가능
			if (bulb[N] == 0) {
				answer = Math.min(answer, count);
			}
		}

		// result
		System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
	}

}
