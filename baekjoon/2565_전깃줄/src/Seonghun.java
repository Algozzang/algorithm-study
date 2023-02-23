import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Seonghun {

	private static int N; // 전깃줄의 개수
	private static int[][] electric; // 전깃줄
	private static int[] maxCount; // 최대로 선택한 전깃줄 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// initialize
		N = Integer.parseInt(br.readLine());
		electric = new int[N + 1][2];
		maxCount = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			electric[i][0] = Integer.parseInt(st.nextToken());
			electric[i][1] = Integer.parseInt(st.nextToken());
		}

		// solution
		Arrays.sort(electric, (o1, o2) -> o1[0] - o2[0]); // 오름차순
		selectWire(0, 0);

		// result
		System.out.println(N - Arrays.stream(maxCount).max().getAsInt());
	}

	// 전깃줄 선택 (n: 선택한 전깃줄 수, p: 이전에 선택한 전깃줄 인덱스)
	public static void selectWire(int n, int p) {
		// 더 볼 필요가 없어요
		if (n < maxCount[p]) {
			return;
		}
		
		// 최대 전깃줄 수 업데이트
		maxCount[p] = n;
		
		// 겹치지 않는 전깃줄 선택하기
		for (int i = p + 1; i <= N; i++) {
			// 현재 전깃줄의 끝부분이 이전에 선택한 전깃줄의 끝부분보다 더 아래에 있어야 해요
			if (electric[p][1] < electric[i][1]) {
				selectWire(n + 1, i);
			}
		}
	}

}
