import java.util.Arrays;
import java.util.Scanner;

public class seonghun {

	static int N; // 쉼터의 수
	static int M; // 두 쉼터 사이를 연결하는 길의 수
	static int[] H; // 각 쉼터의 높이
	static boolean[][] climb; // 등반 가능 여부
	static int[] answer;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// initialize
		N = input.nextInt();
		M = input.nextInt();
		H = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			H[i] = input.nextInt();
		}
		climb = new boolean[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			// 쉼터의 높이가 낮은곳에서 높은곳으로만 이동
			int s1 = input.nextInt();
			int s2 = input.nextInt();
			if (H[s1] < H[s2]) {
				climb[s1][s2] = true;
			} else {
				climb[s2][s1] = true;
			}
		}
		answer = new int[N + 1];
		Arrays.fill(answer, 1);
		input.close();
		
		// solution
		for (int i = 1; i <= N; i++) {
			answer[i] = climbing(i);
		}
		
		// result
		for (int i = 1; i <= N; i++) {
			System.out.println(answer[i]);
		}
	}

	private static int climbing(int s) {
		if (answer[s] == 1) {
			for (int i = 1; i <= N; i++) {
				if (climb[s][i]) {
					// answer[s] - 1 : 위에 몇개의 쉼터가 있는지
					// climbing(i) : i에서 출발했을 때 방문하는 쉼터의 최대 수
					answer[s] = Math.max(answer[s] - 1, climbing(i)) + 1;
				}
			}
		}
		return answer[s];
	}

}
