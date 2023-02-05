import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HyeKyoung {
	static Map<Integer, List<Integer>> map = new HashMap<>();
	static Map<Integer, Integer> restMap = new HashMap<>();
	static List<Integer> rest = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str=br.readLine().split(" ");

		int a, b;
		int n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);

		str = br.readLine().split(" ");
		for (int i = 0; i < n; i++)
			rest.add(Integer.parseInt(str[i]));

		for (int i = 0; i < m; i++) {
			str = br.readLine().split(" ");
			a = Integer.parseInt(str[0]) - 1;
			b = Integer.parseInt(str[1]) - 1;

			if (rest.get(a) < rest.get(b)) {
				if (map.containsKey(a))
					map.get(a).add(b);
				else {
					List<Integer> list = new ArrayList<>();
					list.add(b);
					map.put(a, list);
				}
			} else {
				if (map.containsKey(b))
					map.get(b).add(a);
				else {
					List<Integer> list = new ArrayList<>();
					list.add(a);
					map.put(b, list);
				}
			}
		}
		for (int i = 0; i < n; i++) {
			int maxIdx = rest.indexOf(Collections.max(rest));
			setRest(maxIdx);
			rest.set(maxIdx, 0);
		}
		for (int i = 0; i < n; i++)
			System.out.println(restMap.get(i));
	}

	static void setRest(int i) {
		int max = 0;
		if (!map.containsKey(i)) // 키가 없다면 연결된 것 중 가장 위쪽 휴게소
			restMap.put(i, 1);
		else {
			for (int j : map.get(i))
				if (restMap.get(j) > max)
					max = restMap.get(j);
			restMap.put(i, max + 1);
		}
		return;
	}
}
