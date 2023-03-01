import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class HyeKyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			TreeMap<Integer, Integer> treeMap = new TreeMap<>();
			int k = Integer.parseInt(br.readLine());
			for (int j = 0; j < k; j++) {
				st = new StringTokenizer(br.readLine());
				char ch = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());
				if (ch == 'I') {
					if (!treeMap.containsKey(num)) treeMap.put(num, 1);
					else treeMap.put(num, treeMap.get(num) + 1);
				} else if (ch == 'D') {
					if (treeMap.isEmpty()) continue;

					int key = num > 0 ? treeMap.lastKey() : treeMap.firstKey();

					if(treeMap.get(key) == 1) treeMap.remove(key);
					else treeMap.put(key, treeMap.get(key) - 1);
				}
			}
			if (treeMap.isEmpty()) sb.append("EMPTY\n");
			else sb.append(treeMap.lastKey() + " " + treeMap.firstKey() + "\n");
		}
		System.out.println(sb.toString());
	}
}