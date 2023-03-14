import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Minju {
	// 누적합 10x10씩 증가시키기
		static int n,m,x,y, map[][];
		static int max;
		public static void main(String[] args) throws IOException {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			StringTokenizer st;
			int T = Integer.parseInt(br.readLine());
			map = new int[10001][10001];
			for(int tc =0;tc<T; tc++) {
		
				for(int i=0;i<=10000;i++) Arrays.fill(map[i], 0);
				max = 0;
		
				n = Integer.parseInt(br.readLine());
				
				for(int k =0;k<n;k++) {
					st = new StringTokenizer(br.readLine());
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
	
					for(int i=0;i<=10;i++) {
						for(int j =0;j<=10;j++) {
							if((x+i) > 10000 || (y+j) > 10000) continue;
							map[x+i][y+j]++;
						}
					}
				}
				
				
				for(int i = 0; i<=10000;i++) {
					for(int j =0; j<=10000; j++) {
						max = Math.max(max, map[i][j]);
					}
				}
				
				sb.append(max+"\n");
			}
			System.out.println(sb);
		}
}
