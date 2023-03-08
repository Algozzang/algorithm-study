import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Seonghun {

	static int N; // 숫자의 개수
	static int M; // 숫자의 변경 횟수
	static int K; // 구간의 합을 구하는 횟수
	static long[] numbers; // 입력한 숫자들
	static long[] segtree; // 세그먼트 트리

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder answer = new StringBuilder();

		// initialize
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		numbers = new long[N + 1];
		segtree = new long[N * 4];
		for (int i = 1; i <= N; i++) {
			numbers[i] = Long.parseLong(br.readLine());
		}

		// segment tree 구성
		makeTree(1, 1, N);

		// 변경 및 구간의 합 계산
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			// 숫자 변경하기
			if (a == 1) {
				updateNumber(1, 1, N, b, c);
				numbers[b] = c;
			}
			// 구간의 합 구하기
			if (a == 2) {
				answer.append(getSum(1, 1, N, b, (int) c)).append('\n');
			}
		}

		// result
		System.out.println(answer);
		bw.flush();
		bw.close();
		br.close();
	}

	static long makeTree(int index, int begin, int end) {
		if (begin == end) {
			return segtree[index] = numbers[begin];
		} else {
			int mid = (begin + end) / 2;
			return segtree[index] = makeTree(index * 2, begin, mid) + makeTree(index * 2 + 1, mid + 1, end);
		}
	}

	static void updateNumber(int index, int begin, int end, int b, long c) {
		// 값 업데이트
		segtree[index] += (c - numbers[b]);

		// 더 내려가서 업데이트할 노드가 존재하면
		if (begin != end) {
			int mid = (begin + end) / 2;
			if (b <= mid) { // 왼쪽 노드 업데이트
				updateNumber(index * 2, begin, mid, b, c);
			} else { // 오른쪽 노드 업데이트
				updateNumber(index * 2 + 1, mid + 1, end, b, c);
			}
		}
	}

	static long getSum(int index, int begin, int end, int b, int c) {
		// 구간에 잘 찾아온 경우
		if (b == begin && c == end) {
			return segtree[index];
		}
		// 내려가면서 범위를 찾자
		int mid = (begin + end) / 2;
		if (c <= mid) {
			return getSum(index * 2, begin, mid, b, c);
		} else if (mid < b) {
			return getSum(index * 2 + 1, mid + 1, end, b, c);
		} else {
			return getSum(index * 2, begin, mid, b, mid) + getSum(index * 2 + 1, mid + 1, end, mid + 1, c);
		}
	}

}