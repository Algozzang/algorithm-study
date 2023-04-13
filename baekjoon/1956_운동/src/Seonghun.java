import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Seonghun {

	static int V, E;
	static int[][] dist;
	static final int INF = 1000000000;
	static int answer = INF; // 사이클의 도로 길이의 합의 최소

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dist = new int[V + 1][V + 1];
		for (int i = 0; i <= V; i++) {
			Arrays.fill(dist[i], INF);
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			dist[a][b] = c;
		}

		// floyd-warshall
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		// result
		for (int v = 1; v <= V; v++) {
			answer = Math.min(answer, dist[v][v]);
		}
		System.out.println(answer != INF ? answer : -1);
	}

}
