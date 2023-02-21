import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class HyeKyoung {
	static boolean[] possible;
	static int[][] party;
	static Map<Integer, Integer> truth = new HashMap<>(); // 진실을 아는 사람 저장
	static int m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()), res = 0;
		m = Integer.parseInt(st.nextToken());
		party = new int[m][];
		possible = new boolean[m];
		st = new StringTokenizer(br.readLine());
		int cnt = Integer.parseInt(st.nextToken()), num; // 아는 사람 수
		for (int i = 0; i < cnt; i++) truth.put(Integer.parseInt(st.nextToken()), 1);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			party[i] = new int[num];
			for (int j = 0; j < num; j++) party[i][j] = Integer.parseInt(st.nextToken());
		}
		findParty();
		for (int i = 0; i < m; i++)
			if (!possible[i]) res++;
		
		System.out.println(res);
	}

	static void findParty() {
		for (int i = 0; i < m; i++) {
			if (possible[i]) continue;

			for (int j = 0; j < party[i].length; j++) {
				if (truth.containsKey(party[i][j])) {	//파티에 아는 사람이 껴있으면
					possible[i] = true; //거짓말 못치는 파티,,
					for (int k = 0; k < party[i].length; k++) {
						if (!truth.containsKey(party[i][k])) { //그 파티에 껴있던 모르던 사람들도 알게됨
							truth.put(party[i][k], 1);
							findParty(); //모르던 사람들도 껴있던 파티 탐색
						}
					}
					break;
				}
			}
		}
	}
}
