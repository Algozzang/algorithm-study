import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dongwoo {
	static int N, M, K;
	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int treeLength = (int) Math.pow(2, Math.ceil(log2(N)) + 1);
		long[] tree = initTree(treeLength);
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (command == 1) {
				changeNum(b, c, tree, treeLength);
			}
			if (command == 2) {
				long res = getPartialSum(b - 1, (int) c - 1, 0, treeLength / 2 - 1, tree, treeLength, 1);
				sb.append(res).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static long getPartialSum(int b, int c, int start, int end, long[] tree, int treeLength, int nowInd) {
		if (start == b && end == c) {
			return tree[nowInd];
		}
		// 왼쪽과 오른쪽으로 나눠서 재귀
		long sum = 0;
		if (b < (start + end + 1) / 2) {
			sum += getPartialSum(b, Math.min(c, (start + end - 1) / 2), start, (start + end - 1) / 2, tree, treeLength,
					nowInd * 2);
		}
		if (c > (start + end - 1) / 2) {
			sum += getPartialSum(Math.max(b, (start + end + 1) / 2), c, (start + end + 1) / 2, end, tree, treeLength,
					nowInd * 2 + 1);
		}

		return sum;
	}

	private static void changeNum(int b, long c, long[] tree, int treeLength) {
		int now = treeLength / 2 + b - 1;
		tree[now] = c;
		while (now / 2 > 0) {
			now /= 2;
			tree[now] = tree[now * 2] + tree[now * 2 + 1];
		}
	}

	private static long[] initTree(int treeLength) throws IOException {
		long[] tree = new long[treeLength];
		for (int i = 0; i < N; i++) {
			tree[treeLength / 2 + i] = Long.parseLong(br.readLine());
		}
		for (int i = treeLength / 2 - 1; i > 0; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}

		return tree;
	}

	private static double log2(int n) {
		return Math.log10(n) / Math.log10(2);
	}
}
