import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class dongwoo {
	static int[][][] memo;
	static int[] command;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] temp = br.readLine().replace(" ", "").toCharArray();
		command = new int[temp.length];
		memo = new int[5][5][temp.length];
		for (int i = 0; i < temp.length; i++) {
			command[i] = temp[i] - '0';
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Arrays.fill(memo[i][j], Integer.MAX_VALUE);
			}
		}
		int i = 0;
		memo[0][0][0] = 0;
		while (command[i] != 0) {
			for (int j = 0; j < 5; j++) {
				for (int k = j; k < 5; k++) {
					if (memo[j][k][i] != Integer.MAX_VALUE) {
						int smaller = j;
						int bigger = k;

						if (command[i] >= bigger) {
							memo[j][command[i]][i + 1] = Math.min(memo[j][k][i] + getForce(k, command[i]),
									memo[j][command[i]][i + 1]);
							memo[k][command[i]][i + 1] = Math.min(memo[j][k][i] + getForce(j, command[i]),
									memo[k][command[i]][i + 1]);
							continue;
						}
						if (command[i] <= smaller) {
							memo[command[i]][j][i + 1] = Math.min(memo[command[i]][j][i + 1],
									memo[j][k][i] + getForce(k, command[i]));
							memo[command[i]][k][i + 1] = Math.min(memo[j][k][i] + getForce(j, command[i]),
									memo[command[i]][k][i + 1]);
							continue;
						}
						if (smaller < command[i] && command[i] < bigger) {
							memo[smaller][command[i]][i + 1] = Math.min(memo[j][k][i] + getForce(k, command[i]),
									memo[smaller][command[i]][i + 1]);
							memo[command[i]][bigger][i + 1] = Math.min(memo[j][k][i] + getForce(j, command[i]),
									memo[command[i]][bigger][i + 1]);
							continue;
						}
					}
				}
			}
			i++;
		}
		int res = Integer.MAX_VALUE;
		for (int j = 0; j < 5; j++) {
			for (int k = 0; k < 5; k++) {
				res = Math.min(memo[j][k][command.length - 1], res);
			}
		}
		System.out.println(res);
	}

	private static int getForce(int start, int to) {
		if (start == to) {
			return 1;
		}
		if (start == 0) {
			return 2;
		}
		if (Math.abs(start - to) == 2) {
			return 4;
		}
		return 3;
	}
}
