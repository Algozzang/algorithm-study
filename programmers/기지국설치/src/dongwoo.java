class dongwoo {
	public static void main(String[] args) {
		Solution s1 = new Solution();
		int n = 11;
		int[] stations = new int[] { 4, 11 };
		int w = 1;
		int s1Result = s1.solution(n, stations, w);
		System.out.println(s1Result);
		// expected result = 3;
	}
}

class Solution {
	public int solution(int n, int[] stations, int w) {
		int answer = 0;
		int size = stations.length;
		int[][] schedule = new int[size][2];
		for (int i = 0; i < size; i++) {
			schedule[i][0] = stations[i] - w - 1; // start
			schedule[i][1] = stations[i] + w - 1; // end
		}
		int i = 0;
		int apart = 0;
		int coverage = 2 * w + 1;

		while (apart < size) {
			int diff = schedule[apart][0] - i;
			answer += divideCeil(diff, coverage);	// 나눠서 올림하는 함수 
			i = schedule[apart++][1] + 1;
		}
		int diff = n - i;
		answer += divideCeil(diff, coverage);
		return answer;
	}

	private int divideCeil(int number, int devide) {
		int res = 0;
		if (number > 0) {
			res += number / devide;
			if (number % devide > 0) {
				res += 1;
			}
		}
		return res;
	}
}