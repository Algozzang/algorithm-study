import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Seonghun {

	static int T; // 테스트 케이스 수
	static int N; // 지뢰의 수
	static int[][] landmine = new int[9992][9992]; // 지뢰 누적합
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 한 번에 제거할 수 있는 최대 지뢰 수...
			int answer = 0; 
			
			// initialize
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < 9992; i++) {
				Arrays.fill(landmine[i], 0);
			}
			
			// solution
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				for (int x = Math.max(0, r - 10); x <= Math.min(9991, r); x++) {
					for (int y = Math.max(0, c - 10); y <= Math.min(9991, c); y++) {
						answer = Math.max(answer, ++landmine[x][y]);
					}
				}
			}
			
			// result
			System.out.println(answer);
		}
	}

}