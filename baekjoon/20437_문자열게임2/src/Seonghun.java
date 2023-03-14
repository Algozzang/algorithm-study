import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Seonghun {

	static int T;
	static String W;
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());
		for (int game = 1; game <= T; game++) {
			int answer1 = Integer.MAX_VALUE;
			int answer2 = 0;
			List<List<Integer>> alphabet = new ArrayList<>();
			for (int i = 0; i < 26; i++) {
				alphabet.add(new ArrayList<>());
			}

			// initialize
			W = br.readLine();
			K = Integer.parseInt(br.readLine());

			// 각 알파벳들의 인덱스를 저장
			for (int i = 0; i < W.length(); i++) {
				alphabet.get(W.charAt(i) - 'a').add(i);
			}

			// 인덱스 거리를 비교
			for (int i = 0; i < 26; i++) {
				if (alphabet.get(i).size() >= K) {
					for (int j = K - 1; j < alphabet.get(i).size(); j++) {
						int len = alphabet.get(i).get(j) - alphabet.get(i).get(j - K + 1) + 1;
						answer1 = Math.min(answer1, len);
						answer2 = Math.max(answer2, len);
					}
				}
			}

			// result
			if (answer1 != Integer.MAX_VALUE && answer2 != 0) {
				bw.write(answer1 + " " + answer2 + "\n");
			} else {
				bw.write("-1\n");
			}
		}
		bw.flush();
	}

}
