import java.util.Arrays;

public class dongwoo {
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
	public int maxDiff = -1;
	public final int n = 11;
	public int[] Apeach;
	public int[] answer;

	public int[] solution(int r, int[] info) {
		answer = new int[11];
		Apeach = info;
		H(n, r, 0, new int[11], 0);
		if (maxDiff <= 0) {
			return new int[] { -1 };
		}
		return answer;
	}

	private int calcScoreDiff(int[] Apeach, int[] Ryan) {
		int scoreDiff = 0;
		for (int i = 0; i < n; i++) {
			if (Apeach[i] == Ryan[i] && Ryan[i] == 0) {
				continue;
			}
			if (Apeach[i] < Ryan[i]) {
				scoreDiff += (n - i - 1);
			} else {
				scoreDiff -= (n - i - 1);
			}
		}
		return scoreDiff;
	}

	private void H(int n, int r, int depth, int[] arr, int start) {
		if (depth == r) {
			int diff = calcScoreDiff(Apeach, arr);
			if (maxDiff < diff) {
				maxDiff = diff;
				answer = arr.clone();
			}
			if (maxDiff == diff) {
				for (int i = 0; i < n; i++) {
					if (answer[n - 1 - i] < arr[n - 1 - i] && arr[n - 1 - i] != 0) {
						answer = arr.clone();
						break;
					}
					if (answer[n - 1 - i] > arr[n - 1 - i] && answer[n - 1 - i] != 0) {
						break;
					}
				}
			}
			return;
		}
		for (int i = start; i < 11; i++) {
			arr[i]++;
			H(n, r, depth + 1, arr, i);
			arr[i]--;
		}
	}
}
