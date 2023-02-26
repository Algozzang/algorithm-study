import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class dongwoo {
	static int[][] result;
	static Map<Long, int[][]> dp = new HashMap<>();;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		Long B = Long.parseLong(st.nextToken());
		int[][] A = new int[N][N];
		result = new int[N][N];
		dp.put((long) 1, A);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken())%1000;
			}
		}
		int[][] r = devideAndConquer(B);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(r[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void multiplyMatrix(int[][] A, int[][] B) {
		for (int i = 0; i < B.length; i++) {
			for (int j = 0; j < B.length; j++) {
				int value = 0;
				for (int k = 0; k < B.length; k++) {
					value += A[i][k] * B[k][j];
				}
				result[i][j] = value % 1000;
			}
		}
	}

	private static int[][] devideAndConquer(long n) {
		if (dp.containsKey(n)) {
			return dp.get(n);
		}
		int[][] left = devideAndConquer(n / 2);
		int[][] right = devideAndConquer(n - n / 2);
		int[][] ret = new int[right.length][];
		multiplyMatrix(left, right);
		for (int i = 0; i < right.length; i++) {
			ret[i] = result[i].clone();
		}
		dp.put(n, ret);
		return ret;
	}
}

/*
5 11
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1

=================

5 5
1 2 3 4 5
0 1 2 3 4
0 0 1 2 3
0 0 0 1 2
0 0 0 0 1

1 10 55 220 715 
0 1 10 55 220 
0 0 1 10 55 
0 0 0 1 10 
0 0 0 0 1 


 */
