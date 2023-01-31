import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Minju {

	static int n;
	static boolean[][] person;
	static boolean[] visited;
	static int p1, p2;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// input
		n = Integer.parseInt(br.readLine()); // 전체사람수
		String[] s = br.readLine().split(" ");
		p1 = Integer.parseInt(s[0]);
		p2 = Integer.parseInt(s[1]);
		int m = Integer.parseInt(br.readLine()); // 관계수

		person = new boolean[n + 1][n + 1];
		visited = new boolean[n + 1];
		visited[0] = true;
		for (int i = 0; i < m; i++) {
			String[] str = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			// mark
			person[x][y] = true;
			person[y][x] = true;
		}

		System.out.println(bfs2(p1));

	}
	
	private static int bfs2(int s) { // 시작 row

		int result[] = new int[n + 1]; //촌수 저장할 배열

		Queue<Integer> q = new LinkedList<>();

		// p1 row 삽입
		q.add(s);
		visited[s] = true;

		while (!q.isEmpty()) {
			s = q.poll();
			for (int i = 1; i < n + 1; i++) {
				if (!visited[i] && person[s][i]) { //방문하지 않고 연결된 사람이라면
					visited[i] = true;
					q.add(i);
					result[i] = result[s] + 1; // poll한 부모의 촌수에서 +1
					if(i == p2) return result[p2]; // 타겟이면 촌수반환
				}
			}
		}

		return -1;
	}
}

	/*
	 * private static int bfs1(int s) { //시작 row
	 * 
	 * int result = 0; // 촌수 Queue<Integer> q = new LinkedList<>();
	 * 
	 * // p1 row 삽입 q.add(s); visited[s] = true;
	 * 
	 * while (!q.isEmpty()) { s = q.poll(); result++; //큐에서 빼낼 때마다 촌수 증가
	 * System.out.println(s +" "+ result); boolean loop = false; // row 탐색할거 있었는지
	 * for (int i = 1; i < n + 1; i++) { if (!visited[i] && person[s][i]) { loop =
	 * true; visited[i] = true; q.add(i); if(i == p2) { result -= q.size();
	 * System.out.println(q.size()); return result; } } if(i==n && !loop) result--;
	 * // 다시 부모로 올라간 케이스
	 * 
	 * } }
	 * 
	 * return -1; }
	 */
	
	
