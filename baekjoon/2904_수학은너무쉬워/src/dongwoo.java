import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> soinsubunhae = new HashMap<>();	// 등장하는 모든 숫자를 곱해서 소인수분해 때려놓은거 
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			disAssemble(numbers[i], soinsubunhae);
		}

		int maximumValue = 1;
		Map<Integer, Integer> soinsubunhaeNanungeo = new HashMap<>();	// 소인수분해 한 결과를 N으로 나눈거 
		for (Integer i : soinsubunhae.keySet()) {
			int cnt = soinsubunhae.get(i);
			if (cnt / N > 0) {
				maximumValue *= (int) Math.pow(i, (cnt / N));
				soinsubunhaeNanungeo.put(i, soinsubunhaeNanungeo.getOrDefault(i, 0) + cnt / N);
			}
		}
		sb.append(maximumValue).append(" ");
		
		// 각 숫자마다 돌면서 부족한 숫자 채워넣기 
		int moves = 0;
		for (int i = 0; i < N; i++) {
			Map<Integer, Integer> res = new HashMap<>();
			disAssemble(numbers[i], res);
			for (Integer n : soinsubunhaeNanungeo.keySet()) {
				if (soinsubunhaeNanungeo.get(n) > res.getOrDefault(n, 0)) {
					moves += soinsubunhaeNanungeo.get(n) - res.getOrDefault(n, 0);
				}
			}
		}
		sb.append(moves);
		System.out.println(sb);

	}

	// 소인수분해
	public static void disAssemble(int N, Map<Integer, Integer> result) {
		if (N == 1) {
			return;
		}
		if (isPrime(N)) {
			if (result.containsKey(N)) {
				result.put(N, result.get(N) + 1);
			} else {
				result.put(N, 1);
			}
			return;
		}
		for (int i = (int) Math.sqrt(N) + 1; i > 1; i--) {
			if (N % i == 0) {
				disAssemble(N / i, result);
				disAssemble(i, result);
				break;
			}
		}

	}

	private static boolean isPrime(int N) {
		if (N < 2) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(N); i++) {
			if (N % i == 0) {
				return false;
			}
		}
		return true;
	}

}
