package 양궁대회;

import java.util.Arrays;

public class Minju {

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
	static int[] lion;
	static int maxDiffScore;
	static int[] maxLion;
	static int[] answer;
	static int[] num = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

	public int[] solution(int n, int[] info) { // 어피치가 n발 쏘고, 어피치 과녁점수가 info에
		answer = null;

		lion = new int[n];
		// 중복조합으로 완전탐색
		comb(0, 0, n, info);

		// 라이언이 이길경우가 없는 경우
		if(answer == null) answer = new int[]{-1};
		
		return answer;
	}

	private void comb(int cur, int cnt, int n, int[] info) {

		if(cnt == 0) {
			lion = new int[n];
		}
		
		if (cnt == n) { // 다 골랐으면

			// 점수계산
			int peachScore = 0;
			int lionScore = 0;
			// 중복조합 라이언 인포에 넣기
			int[] lionInfo = new int[11];
			for (int i = 0; i < n; i++) {
				lionInfo[lion[i]]++;
			}
			// 많이 맞춘 친구에게 점수 계산
			for (int i = 0; i <= 10; i++) {
				if(info[i] == lionInfo[i]) {
					if(info[i] == 0) continue;// 둘다 쏜 화살 없으면 넘어감
					else peachScore += (10 - i);// 같으면 어피치가 점수 가져감
				}else if (info[i] < lionInfo[i])lionScore += (10 - i);
				else peachScore += (10 - i);
			}

			if (peachScore >= lionScore) return; // 라이언이 이길 수 없으면 넘김
			
			int diff = lionScore - peachScore;

			if (maxDiffScore == diff && answer != null) { // 현 차이값이 같으면 낮은 점수를 더 많이 맞힌 경우 리턴
				for(int i =10;i>=0;i--) {
					if (answer[i] == lionInfo[i]) continue;
					else if (answer[i] < lionInfo[i]) {
						maxDiffScore = diff;
						answer = lionInfo;
						return;
					}else {
						return; // 현재 조합이 작은 점수를 더 많이 가지고 있으면 리턴
					}
				}
			}
			if (maxDiffScore < diff) { // 현 차이값이 더 크면 최대차이값 업데이트
				maxDiffScore = diff;
				answer = lionInfo;
				return;
			}
			return;
		}

		// 10~0까지 과녁 중복조합
		for (int i = cur; i <= 10; i++) {
			lion[cnt] = num[i];
			comb(i, cnt + 1, n, info);
		}

	}
}