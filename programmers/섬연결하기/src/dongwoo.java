import java.util.Arrays;

public class dongwoo {
	public static void main(String[] args) {

		int n = 4;
		int[][] costs = new int[][] { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
		Solution s = new Solution();
		if (s.solution(n, costs) == 4) {
			System.out.println("정답");
		} else {
			System.out.println("땡");
		}

	}
}

class Solution {
	int[] union;

	public int solution(int n, int[][] costs) {
		Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
		int answer = 0;
		union = new int[n];
		for (int i = 0; i < union.length; i++) {
			union[i] = i;
		}
		for (int i = 0; i < costs.length; i++) {
			if (union(costs[i][0], costs[i][1])) {
				answer+=costs[i][2];
			}
		}
		return answer;
	}

	private boolean union(int a, int b) {
		b = find(b);
		a = find(a);
		if (b > a) {
			union[b] = a;
			return true;
		}
		if (b < a) {
			union[a] = b;
			return true;
		}
		return false;
	}

	private int find(int a) {
		if (a == union[a])
			return a;
		else
			return union[a] = find(union[a]);
	}
}