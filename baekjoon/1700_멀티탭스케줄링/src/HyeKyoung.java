import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class HyeKyoung {
	static int[] num, plug;
	static int minCnt = 0, n, k;
	static Map<Integer, List<Integer>> numCnt = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		num = new int[k];
		plug = new int[n];
		for (int i = 0; i < k; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			if (!numCnt.containsKey(num[i]))
				numCnt.put(num[i], new ArrayList<>());
			numCnt.get(num[i]).add(i);
		}
		scheduling(0, 0);
		System.out.println(minCnt);
	}

	static void scheduling(int size, int cnt) {
		if (cnt == k) return;
		if (size < n) { 
			boolean exist = false;
			for (int x : plug) {
				if (x == num[cnt]) {
					exist = true;
					break;
				}
			}
			if(!exist) {
				plug[size] = num[cnt];
				size++;
			}
			numCnt.get(num[cnt]).remove(0);
		} else {
			boolean exist = false;
			for (int x : plug) {
				if (x == num[cnt]) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				int max = 0, maxIdx = 0;
				for (int i = 0; i < n; i++) {
					if (numCnt.get(plug[i]).size() == 0) {
						maxIdx = i;
						break;
					} else {
						int m = numCnt.get(plug[i]).get(0);
						if (m > max) {
							max = m;
							maxIdx = i;
						}
					}
				}
				plug[maxIdx] = num[cnt];
				minCnt++;
			}
			numCnt.get(num[cnt]).remove(0);
		}
		scheduling(size, cnt + 1);
	}
}
