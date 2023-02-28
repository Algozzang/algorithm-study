import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Seonghun {

	private static int N; // 멀티탭 구멍의 개수
	private static int K; // 전기 용품의 총 사용횟수
	private static List<Integer> multitap = new ArrayList<>(); // 멀티탭을 사용중인 전기 용품 번호
	private static List<Integer> device = new ArrayList<>(); // 사용하는 전기 용품 번호 순서
	private static int answer; // 플러그 빼는 횟수의 최솟값

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int v = Integer.parseInt(st.nextToken());
			device.add(v);
		}

		// solution
		for (int i = 0; i < K; i++) {
			// 전기 용품이 멀티탭에 꽂혀있지 않은 경우
			int v = device.remove(0);
			if (!multitap.contains(v)) {
				if (multitap.size() < N) { // 비어있는 멀티탭이 있는 경우
					multitap.add(v);
				} else {
					// 어떤 전기 용품 플러그를 뽑을까?
					int[] find = new int[N];
					for (int j = 0; j < N; j++) {
						int dist = device.indexOf(multitap.get(j));
						find[j] = dist != -1 ? dist : Integer.MAX_VALUE;
					}
					int idx = 0; // 플러그 뽑을 위치
					for (int j = 1; j < N; j++) {
						if (find[j] > find[idx]) {
							idx = j;
						}
					}
					multitap.set(idx, v);
					answer++;
				}
			}
		}

		// result
		System.out.println(answer);
	}

}
