import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class dongwoo {
	static int[][] memo = new int[100001][1 << 4];
	static int[] command;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] temp = br.readLine().toCharArray();
		command = new int[(temp.length + 1) / 2];
		for (int i = 0; i < ((temp.length + 1) / 2); i++) {
			command[i] = temp[2 * i] - '0';
		}

		System.out.println(dfs(0, 0));
	}

	private static int dfs(int index, int footPosition) {
		if (memo[index][footPosition] != 0) {
			return memo[index][footPosition];
		}
		if (command[index] == 0) {
			return 0;
		}
		if (index == 0) {
			return memo[index][footPosition] = 2 + dfs(index + 1, footPosition | 1 << (command[index] - 1));
		}

		if (((footPosition & (1 << (command[index] - 1))) == (1 << (command[index] - 1)))) {
			int cost =1 + dfs(index + 1, footPosition);
			return memo[index][footPosition] = cost;
		}

		int a = Integer.MAX_VALUE;
		int b = Integer.MAX_VALUE;
		int c = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			if ((footPosition & (1 << i)) != (1 << i)) {
				continue;
			}
			int footPositionCopy = footPosition;
			footPositionCopy = footPositionCopy | 1 << (command[index] - 1);
			footPositionCopy = footPositionCopy & ~(1 << i);
			if (Math.abs(command[index] - 1 - i) == 2) {
				a = 4 + dfs(index + 1, footPositionCopy);
			} else {
				b = 3 + dfs(index + 1, footPositionCopy);
			}
		}
		if (footPosition == 1 || footPosition == 2 || footPosition == 4 || footPosition == 8) {
			c = 2 + dfs(index + 1, footPosition | 1 << (command[index] - 1));
		}
		int cost =  Math.min(a, Math.min(b, c));
		return memo[index][footPosition] = cost;
	}
}
