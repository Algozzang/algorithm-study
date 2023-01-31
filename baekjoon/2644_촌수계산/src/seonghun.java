import java.util.Scanner;

public class seonghun {

	static int answer;
	static int n, m;
	static int person1, person2;
	static boolean[][] relation;
	static boolean[] selected;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// initialize
		answer = -1;
		n = input.nextInt();
		person1 = input.nextInt();
		person2 = input.nextInt();
		m = input.nextInt();
		relation = new boolean[n + 1][n + 1];
		selected = new boolean[n + 1];
		for (int i = 0; i < m; i++) {
			int x = input.nextInt();
			int y = input.nextInt();
			relation[x][y] = true;
			relation[y][x] = true;
		}

		// solution
		selected[person1] = true;
		kinship(person1, 0);

		// result
		System.out.println(answer);
	}

	public static void kinship(int p, int degree) {
		if (p == person2) {
			answer = (answer == -1) ? degree : Math.min(answer, degree);
			return;
		}
		for (int i = 1; i <= n; i++) {
			if (relation[p][i] && !selected[i]) {
				selected[i] = true;
				kinship(i, degree + 1);
			}
		}
	}

}
