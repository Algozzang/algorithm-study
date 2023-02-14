import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HyeKyoung {
	static int[] wrongTab, rightTab;
	static int cnt = 0, n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		wrongTab = new int[n];
		rightTab = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) wrongTab[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) rightTab[i] = Integer.parseInt(st.nextToken()); // input

		edit(0);
		System.out.println(cnt);
	}

	// idx 범위 안넘어가게 조절 신경쓰기
	static void edit(int idx) {
		if (idx >= n) return;
		
		int dif = wrongTab[idx] - rightTab[idx];
		int size = grouping(idx);

		if (dif == 0) edit(idx + 1);
		else {
			int min = 80;
			for (int i = idx; i < idx + size; i++)
				if (Math.abs(wrongTab[i] - rightTab[i]) < min)
					min = Math.abs(wrongTab[i] - rightTab[i]); // 묶인것들 중 가장 차가 작은것 찾기

			for (int i = idx; i < idx + size; i++) {
				if (dif > 0) wrongTab[i] -= min;
				else wrongTab[i] += min;
			}
			cnt += min;
			if (wrongTab[idx] - rightTab[idx] != 0) edit(idx);
			else edit(idx + 1);
		}
	}

	static int grouping(int idx) { // 차의 부호가 같은 라인끼리 그룹핑(묶어서 탭)
		int size = 0;
		int nextIdx = idx + size;
		while (true) {
			if (nextIdx == n - 1) break;

			if (!sameSign(nextIdx)) break;
			nextIdx++;
			size++;
		}
		return size + 1;
	}

	static boolean sameSign(int idx) {
		int dif = wrongTab[idx] - rightTab[idx];
		int nextdif = wrongTab[idx + 1] - rightTab[idx + 1];
		if ((dif > 0 && nextdif > 0) || (dif < 0 && nextdif < 0)) return true;
		return false;
	}
}
