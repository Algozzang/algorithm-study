import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HyeKyoung {
	static int[][] bus;
	static boolean[] isVisited;
	static int[] distance;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), m = Integer.parseInt(br.readLine());
		int from, to, c, a, b; // 출발지, 도착지, 비용
		bus = new int[n + 1][n + 1];
		isVisited = new boolean[n + 1];
		distance = new int[n + 1];
		int MAX = 100000000;

		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++) bus[i][j] = MAX;
		Arrays.fill(distance, MAX);

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			bus[from][to] = Math.min(bus[from][to], c); // 같은 경로로 가는 버스가 있으면 더 작은 값으로 저장
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		distance[a] = 0; // 시작지 0으로

		for (int i = 1; i < n; i++) {
			int min = MAX;
			int idx = 0;

			for (int j = 1; j <= n; j++) {
				if (!isVisited[j] && distance[j] < min) { // 출발노드부터 시작, 방문하지 않은 노드 체크
					min = distance[j];
					idx = j;
				}
			}
//			System.out.println(minIdx + " " + min);
			isVisited[idx] = true;

			for (int j = 1; j <= n; j++) {
				if (!isVisited[j] && bus[idx][j] < MAX) // 연결된 노드 값 체크하고 갱신
					distance[j] = Math.min(distance[j], distance[idx] + bus[idx][j]);
			}
		}
		System.out.println(distance[b]);
	}
}
