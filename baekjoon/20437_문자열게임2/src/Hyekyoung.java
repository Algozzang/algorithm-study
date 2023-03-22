import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Hyekyoung {
	static int max, min, length;
	static List<Integer>[] alphabet = new ArrayList[26];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()), k;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			max = 0;
			min = 10001;
			for (int j = 0; j < 26; j++) alphabet[j] = new ArrayList<>();
			String w = br.readLine();
			k = Integer.parseInt(br.readLine());
			for (int j = 0; j < w.length(); j++) {
				alphabet[w.charAt(j) - 'a'].add(j); // 알파벳이 등장한 자리 저장
			}
			getLength(k);
			sb.append(max == 0 ? "-1" : min + " " + max).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void getLength(int k) {
		for (int i = 0; i < 26; i++) {
			int size = alphabet[i].size();
			if (size < k) continue;

			for (int j = 0; j < size; j++) {
				if (j + k - 1 >= size) break;
				length = alphabet[i].get(j + k - 1) - alphabet[i].get(j) + 1;
				max = Math.max(max, length);
				min = Math.min(min, length);
			}
		}

	}
}
