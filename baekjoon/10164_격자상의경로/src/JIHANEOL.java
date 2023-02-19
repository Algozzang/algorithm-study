import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JIHANEOL {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int matrix[][] = new int[N][M];
		
		int kI = 0;
		int kJ = 0;
		int cnt = 1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(i ==0 || j == 0) matrix[i][j] = 1;
				else {
					matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
				}
				
				if(cnt == K) {
					kI = i;
					kJ = j;
				}
				cnt++;
			}
		}
		
		System.out.println(matrix[kI][kJ] * matrix[N-kI-1][M-kJ-1]);
		
	}

}