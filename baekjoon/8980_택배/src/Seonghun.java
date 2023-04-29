import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N; // 마을 수
	static int C; // 트럭의 용량
	static int M; // 박스 정보의 개수
	static int[][] B; // 박스 정보
	static int[] maxCount; // 각 마을을 지날때 싣는 최대 박스 수
	static int answer; // 트럭 한 대로 배송할 수 있는 최대 박스 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		B = new int[M][3];
		maxCount = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				B[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// solution
		Arrays.sort(B, (o1, o2) -> o1[1] - o2[1]); // 받는 마을 번호 오름차순
		for (int i = 0; i < M; i++) {
			int begin = B[i][0];
			int end = B[i][1];
			int box = B[i][2]; // 싣고갈 박스 수
			// 배송 범위를 고려하며 싣고갈 수 있는 최대 박스 수 계산
			for (int j = begin; j < end; j++) {
				box = Math.min(box, C - maxCount[j]);
			}
			// 최대 박스 수 업데이트
			for (int j = begin; j < end; j++) {
				maxCount[j] += box;
			}
			answer += box;
		}

		// result
		System.out.println(answer);
	}

}