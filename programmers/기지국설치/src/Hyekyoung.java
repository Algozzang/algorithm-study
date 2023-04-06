
public class Hyekyoung {
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
		int start = 1;
		int end;
		for (int i : stations) {
			end = i - w;
			if (start < end) {
				answer += (end - start) / (2 * w + 1);
				if ((end - start) % (2 * w + 1) > 0) answer++;
			}
			start = i + w + 1;
		}
		if (start <= n) {
			end = n;
			answer += (end - start + 1) / (2 * w + 1);
			if ((end - start + 1) % (2 * w + 1) > 0)
				answer++;
		}
		return answer;
	}
}