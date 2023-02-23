import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Seonghun {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 값들의 수를 저장하고 삭제할 tree map 선언
			TreeMap<Integer, Integer> tmap = new TreeMap<>();
			
			// 입력값에 따라 처리
			int k = Integer.parseInt(br.readLine());
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				String cmd = st.nextToken();
				int value = Integer.parseInt(st.nextToken());
				// 삽입
				if (cmd.equals("I")) {
					if (tmap.containsKey(value)) {
						tmap.put(value, tmap.get(value) + 1);
					} else {
						tmap.put(value, 1);
					}
				}
				// 삭제
				if (cmd.equals("D") && !tmap.isEmpty()) {
					// 1이면 가장 큰 키값을, -1이면 가장 작은 키 값을 가져오기
					int target = (value == 1) ? tmap.lastKey() : tmap.firstKey();
					if (tmap.get(target) > 1) {
						tmap.put(target, tmap.get(target) - 1);
					} else {
						tmap.remove(target);
					}
				}
			}
			
			// result
			if (tmap.isEmpty()) {
				System.out.println("EMPTY");
			} else {
				System.out.println(tmap.lastKey() + " " + tmap.firstKey());
			}
		}
	}

}
