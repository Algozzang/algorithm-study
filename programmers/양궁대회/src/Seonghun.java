import java.util.Arrays;

public class Seonghun {

	public static void main(String[] args) {
		Solution s1 = new Solution();
		Solution s2 = new Solution();
		Solution s3 = new Solution();
		Solution s4 = new Solution();
		System.out.println(Arrays.toString(s1.solution(5, new int[] { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 })));
		System.out.println(Arrays.toString(s2.solution(1, new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 })));
		System.out.println(Arrays.toString(s3.solution(9, new int[] { 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 })));
		System.out.println(Arrays.toString(s4.solution(10, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 })));
	}
}

class Solution {

	private int max; // 최대 점수 차이
	private int[] answer; // 정답

	public int[] solution(int n, int[] info) {
		// initialize
		answer = new int[11];

		// 큰 점수부터 맞히는지 선택
		archery(n, 0, info, new int[11]);

		// result
		return max != 0 ? answer : new int[] { -1 };
	}

	public void archery(int n, int i, int[] apeech, int[] ryan) { // n : 남은 화살 수, i : 점수 인덱스
		// 화살을 모두 사용한 경우
		if (n == 0) {
			int score = scoreGap(apeech, ryan);
			if (score > max) {
				max = score;
				answer = ryan.clone();
			} else if (score == max) {
				// 낮은 점수를 더 많이 맞힌 경우 고려
				for (int x = 10; x >= 0; x--) {
					if (ryan[x] > answer[x]) {
						answer = ryan.clone();
					} else if (ryan[x] < answer[x]) {
						break;
					}
				}
			}
			return;
		}
		// 0점을 선택할 때까지 화살이 남은 경우
		if (i == 10) {
			ryan[i] = n;
			archery(0, i + 1, apeech, ryan);
			ryan[i] = 0;
			return;
		}
		// 현재 점수를 선택하는 경우
		if (n > apeech[i]) {
			ryan[i] = apeech[i] + 1;
			archery(n - ryan[i], i + 1, apeech, ryan);
			ryan[i] = 0;
		}
		// 현재 점수를 선택하지 않는 경우
		archery(n, i + 1, apeech, ryan);
	}

	// 라이언과 어피치의 점수 차이를 계산
	public int scoreGap(int[] apeech, int[] ryan) {
		int apeechScore = 0;
		int ryanScore = 0;
		for (int i = 0; i < 10; i++) {
			if (apeech[i] != 0 || ryan[i] != 0) {
				if (apeech[i] >= ryan[i]) {
					apeechScore += (10 - i);
				} else {
					ryanScore += (10 - i);
				}
			}
		}
		return ryanScore - apeechScore;
	}

}
