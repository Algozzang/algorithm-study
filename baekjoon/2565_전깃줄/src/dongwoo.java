import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class dongwoo {
	static int erasedMin = Integer.MAX_VALUE;
	static int[][] jeonSeon;
	static int N;

	public static void main(String[] args) throws IOException {
		// 그리디 인듯 하다. 겹치는거 판단하는 로직 짜서 각 전선마다 겹치는 수 구하고
		// 제일 많이 겹치는거부터 걷어내자.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		jeonSeon = new int[N][2]; // 0엔 A좌표, 1엔 B 좌표
		int[] length = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jeonSeon[i][0] = Integer.parseInt(st.nextToken());
			jeonSeon[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(jeonSeon, (a1, b1) -> a1[0] - b1[0]);
		int lisLen = 0;
		for (int i = 0; i < N; i++) {
			length[i] = 1;
			for (int j = 0; j < i; j++) {
				if (jeonSeon[j][1] < jeonSeon[i][1]) {
					length[i] = Math.max(length[i], length[j] + 1);
					lisLen = Math.max(lisLen, length[i]);
				}
			}
		}

		System.out.println(N-lisLen);
	}
}
