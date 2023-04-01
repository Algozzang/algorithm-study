import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> L = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			if (L.isEmpty() || A[i] > L.get(L.size() - 1)) {
				L.add(A[i]);
				continue;
			}
			int lowerBound = findLowerBound(L, A[i]);
			L.set(lowerBound, A[i]);
		}
		System.out.println(L.size());
	}

	private static int findLowerBound(List<Integer> L, int val) {
		int res = Collections.binarySearch(L, val);
		if (res < 0) {
			res = -res - 1;
		}
		return res;
	}
}
