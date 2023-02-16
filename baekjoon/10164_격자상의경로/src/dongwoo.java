import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dongwoo {

	public static void main(String[] args) throws IOException {
		// 경우의 수 문제
		// i,j 좌표에서의 값이  i+j C j 네요. 	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int i, j;
		long answer = 1;
		if (K==0) {
			i = N-1;
			j = M-1;	
		} else {
			i = (K-1)/M;
			j = (K-1)%M;
			answer = Combination(i+j, j);
			i = N - i - 1;
			j = M - j - 1;
		}
		answer *= Combination(i+j, j);
		
		System.out.println(answer);
	}
	private static long Combination(int n, int r1) {
		long result = 1;
		int r2 = n-r1;
		int r = Math.max(r1, r2);
		for (int i = r+1; i<=n; i++) {
			result *= i;
		}
		for (int i = 2; i<=n-r; i++) {
			result /= i;
		}
		return result;
	}
	
//	1 1 1  1  1  1
//	1 2 3  4  5  6
//	1 3 6  10 15 21
// 	1 4 10 20 35 56
//	1 5 15 35 70 126
//	1 6 21 56 126 252
}

