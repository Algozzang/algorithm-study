import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Seonghun {

	private static int N;
	private static int[][] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long B;
		int[][] A;

		// initialize
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		A = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}

		// solution
		answer = matPower(A, B);

		// result
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.write(answer[i][j] + " ");
			}
			bw.newLine();
		}
		bw.flush();
	}

	// A 배열의 B제곱 반환
	public static int[][] matPower(int[][] A, long B) {
		if (B == 1) {
			return A;
		} else {
			int[][] mat = matPower(A, B / 2);
			int[][] matmat = matMult(mat, mat);
			return (B % 2 == 0) ? matmat : matMult(matmat, A);
		}
	}

	// X 배열과 Y 배열의 곱 반환
	public static int[][] matMult(int[][] X, int[][] Y) {
		int[][] result = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					result[i][j] = (result[i][j] + X[i][k] * Y[k][j]) % 1000;
				}
			}
		}
		return result;
	}

}
