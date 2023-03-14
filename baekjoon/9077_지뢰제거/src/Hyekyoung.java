import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Hyekyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()), N, x, y, max = 0;
		StringTokenizer st;
		int[][] square = new int[10001][10001];
		for(int i=0; i<T; i++) {
			N = Integer.parseInt(br.readLine());
			for(int j=0; j<=10000; j++) Arrays.fill(square[j], 0);
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				for(int k=x; k<=x+10; k++) {
					if(k>10000) break;
					for(int l = y; l<=y+10; l++) {
						if(l>10000) break;
						square[k][l]++;
						max = Math.max(max, square[k][l]);
					}
				}
			}
			System.out.println(max);
		}
	}

}
