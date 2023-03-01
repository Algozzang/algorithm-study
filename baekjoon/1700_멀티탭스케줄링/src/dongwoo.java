import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dongwoo {
	static int[] schedule;
	static int minChanged = Integer.MAX_VALUE;
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		schedule = new int[K];
		for (int i = 0; i < K; i++) {
			schedule[i] = Integer.parseInt(st.nextToken());
		}
		search(0, new visited(new boolean[K + 1]), 0);
		System.out.println(minChanged);
	}

	private static void search(int i, visited v, int changed) {
		if (i == K) { // 멀티탭 빼는 횟수의 최소값 업데이트
			minChanged = Math.min(changed, minChanged);
			return;
		}

		if (v.status[schedule[i]]) {
			search(i + 1, v, changed);
			return;
		}

		if (v.size < N) {
			v.status[schedule[i]] = true;
			v.size++;
			search(i + 1, v, changed);
			return;
		}

		for (int j = 1; j < K + 1; j++) { // 빼야하는 경우
			boolean old = v.status[j];
			if (old) {
				v.status[schedule[i]] = true;
				v.status[j] = false;
				if (minChanged >= changed + 1) {
					search(i + 1, v, changed + 1);
				}
				v.status[j] = true;
				v.status[schedule[i]] = false;
			}
		}
	}
}
class visited {
	boolean[] status;
	int size = 0;
	visited(boolean[] status){
		this.status = status;
	}
}