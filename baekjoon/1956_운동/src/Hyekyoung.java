import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Hyekyoung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int[][] distance = new int[V + 1][V + 1];
		int a, b, c, INF = 5000000, min = 5000000;

		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i != j) distance[i][j] = INF;
			}
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			distance[a][b] = c;
		}

		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				if (i == k)
					continue;
				for (int j = 1; j <= V; j++) {
					// i에서 k를 거쳐 j로 가는 경로가 있는지 보고 갱신 가능하면 하기
					distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				// 사이클 확인
				if (i != j) min = Math.min(min, distance[i][j] + distance[j][i]);
			}
		}
		System.out.println(min == INF ? "-1" : min);
	}
}