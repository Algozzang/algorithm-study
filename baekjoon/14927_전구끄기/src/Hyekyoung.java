import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Hyekyoung {

	static int N, minCnt = 325, push;
	static int light[], selected, copy[], l, pos;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		light = new int[N];
		int input;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				input = Integer.parseInt(st.nextToken());
				if (input == 1) light[i] |= (1 << j);
				// 불 켜진 곳 표시
			}
		}
		selectTop(0, 0);
		System.out.println(minCnt < 325 ? minCnt : -1);
	}

	// 가장 윗줄 모든 경우 선택
	static void selectTop(int cnt, int selected) {
		if (cnt == N) {
			lightOff(selected);
			return;
		}
		selectTop(cnt + 1, selected);
		selected |= 1 << cnt;
		selectTop(cnt + 1, selected);
	}

	static void lightOff(int selected) {
		copy = light.clone();
		push = 0;
		boolean flag = true;
		l = selected & (-selected);
		// 현재 열 바로 윗줄에서 가장 오른쪽에 위치한 1 비트 위치 찾기
		// 내 윗 전구가 1이면 꺼야함(윗 줄을 끌 수 있는 것은 아랫줄 뿐이니까)
		while (l > 0) {
			pos = log2(l);
			changeLight(0, pos);
			selected &= ~(1 << pos);
			l = selected & (-selected);
		}
		for (int i = 1; i < N; i++) {
			l = copy[i - 1] & (-copy[i - 1]);
			while (l > 0) {
				changeLight(i, log2(l));
				l = copy[i - 1] & (-copy[i - 1]);
			}
		}
		for (int i = 0; i < N; i++) {
			if (copy[i] > 0) {
				flag = false;
				break;
			}
		}
		if (flag) minCnt = Math.min(minCnt, push);
	}

	static void changeLight(int r, int c) {
		push++;
		copy[r] ^= 1 << c;
		if (r > 0) copy[r - 1] ^= 1 << c;
		if (r < N - 1) copy[r + 1] ^= 1 << c;
		if (c > 0) copy[r] ^= 1 << c - 1;
		if (c < N - 1) copy[r] ^= 1 << c + 1;
	}

	static int log2(int x) {
		return (int) (Math.log(x) / Math.log(2));
	}
}