import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class dongwoo {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			TreeMap<Integer, Integer> dq = new TreeMap<>();
			int calc = Integer.parseInt(br.readLine());
			for (int c = 0; c < calc; c++) {
				st = new StringTokenizer(br.readLine());
				String command = st.nextToken();
				if (command.equals("I")) {
					int input = Integer.parseInt(st.nextToken());
					if (dq.containsKey(input)) {
						dq.put(input, dq.get(input) + 1);
					} else {
						dq.put(input, 1);
					}
				}
				if (command.equals("D")) {
					if (dq.size() == 0) {
						continue;
					}
					String order = st.nextToken();
					if (order.equals("1")) {
						int val = dq.lastKey();
						if (dq.get(val) > 1) {
							dq.put(val, dq.get(val) - 1);
						} else {
							dq.pollLastEntry();
						}
						System.out.println(val);
					}
					if (order.equals("-1")) {
						int val = dq.firstKey();
						if (dq.get(val) > 1) {
							dq.put(val, dq.get(val) - 1);
						} else {
							dq.pollFirstEntry();
						}
						System.out.println(val);
					}
				}
			}
			if (dq.size() == 0) {
				System.out.println("EMPTY");
			} else {
				System.out.print(dq.lastKey() + " ");
				System.out.println(dq.firstKey());
			}
		}
	}
}