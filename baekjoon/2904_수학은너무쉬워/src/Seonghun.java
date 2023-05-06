import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Seonghun {

	static int N;
	static int[] numbers;
	static final int MAX = 1000000;
	static int[] factor = new int[MAX + 1]; // 소인수분해 배열 (해당 수의 가장 작은 소인수 저장)
	static Map<Integer, Integer> totalCount = new HashMap<Integer, Integer>(); // 소인수 들의 전체 개수
	static Map<Integer, Integer>[] count; // 각 숫자들에 대한 소인수 개수
	static int score = 1; // 얻을 수 있는 최대 점수
	static int move = 0; // 최대 점수를 위한 최소 이동 횟수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// initialize
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		count = new HashMap[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			count[i] = new HashMap<>();
		}

		// eratos 소인수 분해
		for (int i = 2; i <= MAX; i++) {
			factor[i] = i;
		}
		for (int i = 2; i * i <= MAX; i++) {
			if (factor[i] == i) {
				for (int j = i * i; j <= MAX; j += i) {
					if (factor[j] == j) {
						factor[j] = i;
					}
				}
			}
		}

		// 소인수 고려
		for (int i = 0; i < N; i++) {
			int num = numbers[i];
			while (num > 1) {
				int fac = factor[num];
				totalCount.put(fac, totalCount.getOrDefault(fac, 0) + 1);
				count[i].put(fac, count[i].getOrDefault(fac, 0) + 1);
				num /= fac;
			}
		}

		// 최대 공약수 구하기
		for (Entry<Integer, Integer> e : totalCount.entrySet()) {
			totalCount.put(e.getKey(), e.getValue() / N);
			if (totalCount.get(e.getKey()) > 0) {
				score *= Math.pow(e.getKey(), totalCount.get(e.getKey()));
			}
		}

		// 이동 횟수 계산하기
		for (int i = 0; i < N; i++) {
			for (Entry<Integer, Integer> e : totalCount.entrySet()) {
				int gap = e.getValue() - count[i].getOrDefault(e.getKey(), 0);
				move += Math.max(gap, 0);
			}
		}

		// result
		System.out.println(score + " " + move);
	}

}
