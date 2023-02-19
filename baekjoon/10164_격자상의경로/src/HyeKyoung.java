import java.util.Scanner;

public class HyeKyoung {
	static int[][] array;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt(), m = s.nextInt(), k = s.nextInt();
		int r = 0, c = 0, res;
		array = new int[n][m];

		if (k != 0) {
			r = k / m; c = (k - 1) % m;
			res=calPath(0, 0, r, c) * calPath(r, c, n-1, m-1);
		}
		else res = calPath(0, 0, n-1, m-1);
		System.out.print(res);
	}

	static int calPath(int sr, int sc, int er, int ec) {
		for(int i=sr; i<=er; i++) {
			for(int j=sc; j<=ec; j++) {
				if(i==sr || j==sc) array[i][j]=1;
				else array[i][j]=array[i-1][j]+array[i][j-1];
			}
		}
		return array[er][ec];
	}
}
