import java.util.Arrays;

//간많프 간선이 적으면 크루스칼!
public class Hyekyoung {
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

class Edge implements Comparable<Edge> {
	int from, to, weight;

	public Edge(int from, int to, int weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight, o.weight);
	}
}

class Solution {
	int V, E;
	Edge[] edgeList;
	int[] parents;

	void makeSet() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}

	int findSet(int a) {
		if (a == parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}

	boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot) return false;

		parents[bRoot] = aRoot;
		return true;
	}

	public int solution(int n, int[][] costs) {
		V = n;
		E = costs.length;

		edgeList = new Edge[E];

		for (int i = 0; i < E; i++) {
			edgeList[i] = new Edge(costs[i][0], costs[i][1], costs[i][2]);
		}

		Arrays.sort(edgeList);
		makeSet();
		int result = 0, count = 0;

		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				result += edge.weight;
				if (++count == V - 1) break;
			}
		}

		return result;
	}
}