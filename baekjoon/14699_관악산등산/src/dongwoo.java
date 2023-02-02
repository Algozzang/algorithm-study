import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class dongwoo {
	public static int[] heights;
	public static int[] NofVisit;
	public static Map<Integer, List<Integer>> upperNodes = new HashMap<>(); // 키값기준 아래에서 위로 올라가는 간선만 저장
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp = br.readLine().split(" ");
		int n = Integer.parseInt(tmp[0]);
		int m = Integer.parseInt(tmp[1]);

		NofVisit = new int[n + 1];
		heights = new int[n + 1];
		Map<Integer, Integer> heightToNode = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i < n + 1; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
			heightToNode.put(heights[i], i);
		}
		
		// 연결 선을 더 높이 있는 노드만 저장
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			if (heights[n1]>heights[n2]) {
				if(!upperNodes.containsKey(n2)) {
					upperNodes.put(n2, new ArrayList<Integer>());
				}
				upperNodes.get(n2).add(n1);
			}
			if (heights[n1]<heights[n2]) {
				if(!upperNodes.containsKey(n1)) {
					upperNodes.put(n1, new ArrayList<Integer>());
				}
				upperNodes.get(n1).add(n2);
			}
		}
		
		int[] sortedHeight = heights.clone();
		Arrays.sort(sortedHeight);
		// 높이가 높은 노드부터 차례로 탐색하면서 방문할 수 있는 쉼터 개수 계산 NofVist에 저장.
		
		for(int i=1; i<n+1; i++) {
			int height = sortedHeight[n+1-i];
			int node = heightToNode.get(height);
			if (!upperNodes.containsKey(node)) {
				NofVisit[node] = 1;
			} else {
				int partial_max = 0;
				for (int up_node:upperNodes.get(node)) {
					partial_max = Math.max(partial_max, NofVisit[up_node]);
				}
				NofVisit[node] = partial_max + 1;
			}
		}
		
		for(int i=1; i<n+1; i++) {			
			System.out.println(NofVisit[i]);
		}
		
	}

}
