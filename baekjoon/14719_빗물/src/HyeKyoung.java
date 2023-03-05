import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HyeKyoung {
	static long[] numbers;
	static long[] tree;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int a, b;
		long c;

		tree = new long[4 * N];
		numbers = new long[N + 1];

		for (int i = 1; i <= N; i++) {
			numbers[i] = Long.parseLong(br.readLine());
		}
		setTree(1, N, 1);
		for (int i = 0; i < K + M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Long.parseLong(st.nextToken());
			if (a == 1) {
				changeNum(b, c - numbers[b], 1, N, 1);
				numbers[b] = c;
			}
			else sb.append(getSum(b, (int) c, 1, N, 1) + "\n");
		}
		System.out.println(sb.toString());
	}

	static void setTree(int start, int end, int idx) {
		if (start == end) {
			tree[idx] = numbers[start];
			return;
		}
		int mid = (start + end) / 2;
		setTree(start, mid, 2 * idx);
		setTree(mid + 1, end, 2 * idx + 1);

		tree[idx] = tree[2 * idx] + tree[2 * idx + 1];
	}

	static void changeNum(int a, long diff, int start, int end, int idx) {
		// 구간합도 변경
		tree[idx] += diff;
		if (start == end) return;

		int mid = (start + end) / 2;
		if (a >= start && a <= mid) changeNum(a, diff, start, mid, 2 * idx);
		else if (a >= mid + 1 && a <= end) changeNum(a, diff, mid + 1, end, 2 * idx + 1);
	}

	// 구간 a-b가 범위 내에 있는지 탐색
	static long getSum(int a, int b, int start, int end, int idx) {
		if (a > end || b < start) return 0;
		if (a <= start && b >= end) return tree[idx];

		int mid = (start + end) / 2;
		long left = getSum(a, b, start, mid, 2 * idx);
		long right = getSum(a, b, mid + 1, end, 2 * idx + 1);
		
		return left + right;
	}
}