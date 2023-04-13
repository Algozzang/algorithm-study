import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Minju {
	static int v, e, map[][];
	static StringTokenizer st;
	static final int INF = 5000000;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		map = new int[v + 1][v + 1];
	
		// init
		for (int i = 0; i <= v; i++)
			Arrays.fill(map[i], INF);
				// input
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		// 플로이드 워샬
		for (int i = 1; i <= v; i++) { // 경유
			for (int j = 1; j <= v; j++) { // 출발
				if (i == j) continue; // 출발 경유 x
				for (int k = 1; k <= v; k++) { // 목적
					if (k == i || j == k) continue; // 출발목적x 경유목적x
					if (map[j][k] > map[j][i] + map[i][k]) {
						map[j][k] = map[j][i] + map[i][k];
					}
				}
			}
		}

		
		int result = Integer.MAX_VALUE;
		for (int i = 1; i <= v; i++) { 
			for (int j = 1; j <= v; j++) {
				int cycleSum = 0;
				if(map[i][j] != INF && map[i][j] >0 ) {
					if(map[j][i] != INF && map[j][i] >0 ) {
						cycleSum += (map[i][j] + map[j][i]);
					}
				}
				if(cycleSum != 0) result = Math.min(result, cycleSum);
			}
		}

		System.out.println(result==Integer.MAX_VALUE ? -1: result);
				
	}

}
