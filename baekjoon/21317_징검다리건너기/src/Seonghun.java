import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N; // 돌의 수
	static int K; // 매우 큰 점프 시 필요한 에너지
	static int[] jump1, jump2; // 1칸 이동, 2칸 이동시 필요한 에너지
	static int[][] minEnergy; // 필요한 최소에너지, 0열은 매우 큰 점프를 안쓴, 1열은 매우 큰 점프를 쓴.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// initialize
		N = Integer.parseInt(br.readLine());
		jump1 = new int[N];
		jump2 = new int[N];
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jump1[i] = Integer.parseInt(st.nextToken());
			jump2[i] = Integer.parseInt(st.nextToken());
		}
		K = Integer.parseInt(br.readLine());
		minEnergy = new int[N + 1][2];
		minEnergy[1][1] = 5000; // 여기는 불가능한 위치. 최대 에너지로 채워놓습니다.

		// solution
		for (int i = 2; i <= N; i++) {
			// 한칸 이동
			minEnergy[i][0] = minEnergy[i - 1][0] + jump1[i - 1];
			minEnergy[i][1] = minEnergy[i - 1][1] + jump1[i - 1];
			// 두칸 이동
			if (i > 2) {
				minEnergy[i][0] = Math.min(minEnergy[i][0], minEnergy[i - 2][0] + jump2[i - 2]);
				minEnergy[i][1] = Math.min(minEnergy[i][1], minEnergy[i - 2][1] + jump2[i - 2]);
			}
			// 세칸 이동
			if (i > 3) {
				minEnergy[i][1] = Math.min(minEnergy[i][1], minEnergy[i - 3][0] + K);
			}
		}

		// result
		System.out.println(Math.min(minEnergy[N][0], minEnergy[N][1]));
	}
}
