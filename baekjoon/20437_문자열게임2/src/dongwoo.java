import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			Map<Character, List<Integer>> string = new HashMap<>();
			char[] original = br.readLine().toCharArray();
			int K = Integer.parseInt(br.readLine());
			for (int i = 0; i < original.length; i++) {
				if (!string.containsKey(original[i])) {
					string.put(original[i], new ArrayList<>());
				}
				string.get(original[i]).add(i);
			}
			String result = "-1";
			int min = Integer.MAX_VALUE;
			int max = 0;
			for (char c : string.keySet()) {
				List<Integer> appear = string.get(c);
				if (appear.size() < K) {
					continue;
				}
				for (int i = 0; i < appear.size() - K + 1; i++) {
					int diff = appear.get(i + K - 1) - appear.get(i) + 1;
					min = Math.min(min, diff);
					max = Math.max(max, diff);
				}
			}
			if (min == Integer.MAX_VALUE) {
				System.out.println(result);
			} else {
				System.out.println(min + " " + max);
			}
		}
	}
}
