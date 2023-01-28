import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class dongwoo {
	public static int subStringSizeMax = 0;
	// str1을 기준으로 str2와 겹치는 문자를 저장 gyeopChiNeunMunja
	// 겹치는 문자가 str2에서 어디에 있는지 index값을 저장 gyeopChiNeunWhich
	public static List<Character> gyeopChiNeunMunja = new ArrayList<Character>();
	public static HashMap<Character, ArrayList<Integer>> gyeopChiNeunWhich = new HashMap<>();
	public static HashMap<String, Integer> cache = new HashMap<String, Integer>();

	public static void main(String[] args) throws IOException {
		// 시작 02:30
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Scanner sc = new Scanner(System.in);
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		int SIZE1 = str1.length;
		int SIZE2 = str2.length;

		for (int i = 0; i < SIZE1; i++) {
			for (int j = 0; j < SIZE2; j++) {
				if (str1[i] == str2[j]) {
					gyeopChiNeunMunja.add(str1[i]);
					if (!(gyeopChiNeunWhich.containsKey(str1[i]))) {
						gyeopChiNeunWhich.put(str1[i], new ArrayList<Integer>());
					}
					gyeopChiNeunWhich.get(str1[i]).add(j);
				}
			}
		}

		dfs(0, 0, -1, gyeopChiNeunMunja.size());

		System.out.println(subStringSizeMax);
	}

	public static void dfs(int index, int subStringSize, int index2, int maxLength) {
		// index 겹치는 문자에 접근하기 위한 인덱스값
		// subStringSize 현재까지 선택한 문자의 크기
		// index2 str2에서의 현재까지 선택한 문자의 최대 인덱스 값.
		if (index >= maxLength) {
			return;
		}
		if (cache.getOrDefault(index + "_" + index2, -1) >= subStringSize) {
			return;
		}
		cache.put(index + "_" + index2, subStringSize);

		if (subStringSize > subStringSizeMax) {
			subStringSizeMax = subStringSize;
		}
		for (int whici : gyeopChiNeunWhich.get(gyeopChiNeunMunja.get(index))) {
			// 현재 index에 있는 문자를 선택하는경우
			if (whici > index2) {
				dfs(index + 1, subStringSize + 1, whici, maxLength);
			}
			// 현재 index에 있는 문자를 선택 안 하는경우
			dfs(index + 1, subStringSize, index2, maxLength);
		}
	}
}
