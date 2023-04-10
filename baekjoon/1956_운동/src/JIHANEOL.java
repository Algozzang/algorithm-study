import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class JIHANEOL {
	static int map[][],dp[][],V,E;
	 static final int INF = 99999999;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.valueOf(st.nextToken());
		E = Integer.valueOf(st.nextToken());
		
		map = new int[V+1][V+1];
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.valueOf(st.nextToken());
			int e = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());
			map[s][e]=cost;
		}
		  for (int i = 1; i <=V; i++) {
	            for (int j = 1; j <= V; j++) {
	                if (i == j) continue;
	                if (map[i][j] == 0) map[i][j] = INF;
	            }
	        }
		
		for(int k=1; k<=V; k++) { // 경유
			for (int i = 1; i <= V; i++) { //출발
				if(i==k) continue;
				for (int j = 1; j <= V; j++) { // 도착
					map[i][j] = Math.min(map[i][k]+map[k][j], map[i][j]);
				}
			}
		}
		int ans = INF;
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i==j)continue;
				if(map[i][j]!=INF && map[j][i]!=INF) {
					if(ans>map[i][j]+map[j][i]) {
						ans = map[i][j]+map[j][i];
					}
				}
				
			}
		}
		int res = ans==INF?-1:ans;
		System.out.println(res);
		
	}
}

















