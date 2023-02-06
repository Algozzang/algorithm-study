import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Minju {
	//dp 설정했으니까 방문처리빼야했는데 안빼서 오래걸린 문제
	
	static int N, M;
	static int[] height; //각 높이를 저장하는 배열
	//static boolean[] visited; //dfs 방문체크 배열
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 그래프
	static int[] visitedNum; //dp
	
	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		//visited = new boolean[N + 1]; // 1~n
		height = new int[N + 1];
		visitedNum = new int[N+1];
		
		for (int i = 1; i < N + 1; i++) {// 높이 입력받기
			height[i] = Integer.parseInt(st.nextToken());
		}

		// graph init
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<Integer>());
		}

		// 위로만 갈 수 있는 단방향 그래프 생성
		/*
		 * 1: 4 5 
		 * 2: 1 4 
		 * 3 
		 * 4: 3 
		 * 5
		 */
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (height[a] > height[b]) { // b 높이가 더 작으면 b -> a 이동가능
				graph.get(b).add(a);
			} else {
				graph.get(a).add(b);
			}
			System.out.println(graph);
		}
		// 모든 쉼터 방문 횟수 0으로 설정, 하나씩 갔다오면서 ++해줄거임
		Arrays.fill(visitedNum, 0); 
		
		for (int i = 1; i < N + 1; i++) {
			search(i); // dfs : 노드 마다 max횟수 구하기
		}
		
		//output
		for(int i =1;i<N+1;i++) {
			sb.append(visitedNum[i]).append("\n"); //쉼터마다 개수 반환
		}
		System.out.println(sb);
	}


	private static int search(int n) { // n번쉼터에서 출발
		
		if(visitedNum[n] >0) return visitedNum[n]; //이미 체크한 쉼터라면 바로 반환
	
		for (int i = 0; i < graph.get(n).size(); i++) {
			int up = graph.get(n).get(i);
			visitedNum[n] = Math.max(visitedNum[n], search(up)); // 현재 값과 다음 갈 곳 중 max
				
		}
		// 더 탐색할 곳 없으면 하나씩 꺼내오면서 ++
		return ++visitedNum[n];
		// 3꺼내오면서 1, 4꺼내오면서 2, 1꺼내오면서 3
	}
}

/* 예제
5 5
3 1 6 4 7
1 4
2 1
3 4
4 2
5 1

반례
5 5
1 3 2 4 5
1 2
2 5
1 3
3 4
4 5
*/

