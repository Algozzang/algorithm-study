import java.util.Arrays;

public class Seonghun {
	public static void main(String[] args) {
		int n = 4;
		int[][] costs = new int[][] { 
			{0, 1, 1},
			{0, 2, 2},
			{1, 2, 5},
			{1, 3, 1},
			{2, 3, 8}
		};
		Solution s = new Solution();
		if (s.solution(n, costs) == 4) {
			System.out.println("정답");
		} else {
			System.out.println("땡");
		}		
	}
}

class Solution {

	static int[] p; // 각 노드의 상위 노드 저장
	static int answer; // 모든 섬을 연결하는데 필요한 최소 비용

	// Kruskal
	public int solution(int n, int[][] costs) {
		// 루트 노드 초기화
		p = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = i;
		}

		// 건설 비용 오름차순 정렬
		Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);

		// 연결 상태에 따라 MST에 간선 추가 여부 결정
		for (int i = 0; i < costs.length; i++) {
			int root1 = root(costs[i][0]);
			int root2 = root(costs[i][1]);
			if (root1 != root2) {
				p[root1] = root2;
				answer += costs[i][2];
			}
		}

		return answer;
	}

	// 루드 노드 반환
	int root(int n) {
		return (n == p[n]) ? n : (p[n] = root(p[n]));
	}
}