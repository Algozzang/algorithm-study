import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Minju { // 1132 합

	static int n;

	/*
	 * 길이에 따라 가중치를 주되 자리수도 생각해야 함 
	 * 각 알파벳마다 가중치가 높은 순으로 9부터 배정 
	 * 0 예외처리 말고는 동작..
	 * 만약 알파벳이 맨앞에 오는 경우가 있다면 0은 안됨 (HOW? 모르겠어...)
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		String[] s = new String[n];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			s[i] = str;

		}

		HashMap<Character, Integer> alpha = new HashMap<>(); // 알파벳, 가중치
		boolean[] isFirst = new boolean[10]; // 맨첫자리 체크
		boolean isf = false;
		int[] alphabet = new int[10]; // 0a 1b 2c ...9j
		Arrays.fill(alphabet, 0);
		for (int i = 0; i < n; i++) { // 받은 문자열 개수만큼
			for (int j = 0; j < s[i].length(); j++) {

				char cur = s[i].charAt(j);
				if (j == 0) {
					isFirst[cur - 'A'] = true;
					isf = true;
				}

				int temp = 0;
				if (alpha.containsKey(cur))
					temp = alpha.get(cur);
				alpha.put(cur, temp + (int) Math.pow(10, s[i].length() - j - 1));
				alphabet[cur - 'A'] = temp + (int) Math.pow(10, s[i].length() - j); // 현재 알파벳의 가중치값 늘려주기
			}
		}

		// map 값 정렬 내림차순
		List<Entry<Character, Integer>> entry = new ArrayList<Entry<Character, Integer>>(alpha.entrySet());

		Collections.sort(entry, new Comparator<Entry<Character, Integer>>() {

			@Override
			public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}

		});

		List<Integer> nums = new ArrayList<>(); // 숫자 리스트 썼으면 remove
		int[] alphabet2 = new int[10]; // 0a 1b 2c ...9j //치환할 숫자가 값이 됨

		for (int i = 0; i < 10; i++) {
			nums.add(i); // 기본 0~9세팅
		}
		Collections.sort(nums, Collections.reverseOrder());

		/*
		 * for (int i = 0; i < entry.size(); i++) { //map 값 내림차순 정렬한 리스트 char c =
		 * entry.get(i).getKey(); if (isFirst[c - 'A']) { alphabet2[c - 'A'] =
		 * nums.get(0); nums.remove(0); } else { alphabet2[c - 'A'] = nums.get(0);
		 * nums.remove(0); } }
		 */

		for (int i = 0; i < entry.size(); i++) { // map 값 내림차순 정렬한 리스트
			char c = entry.get(i).getKey();
			if (isFirst[c - 'A']) {
				/*
				 * 모든 문자를 사용했고
				 * 첫자리인거있으면 첫자리 0인 애들 어떻게 풀지
				 */
				alphabet2[c - 'A'] = nums.get(0);
				nums.remove(0);
			} else {
				alphabet2[c - 'A'] = nums.get(0);
				nums.remove(0);
			}
		}

		for (int i = 0; i < n; i++) { // 문자열 해당 숫자로 치환
			for (int j = 0; j < s[i].length(); j++) {
				int ch = s[i].charAt(j) - 'A';
				String cur = Integer.toString(alphabet2[ch]);
				s[i] = s[i].substring(0, j) + cur + s[i].substring(j + 1);
			}
		}
		long result = 0;
		for (int i = 0; i < n; i++) { // 합 구하기
			result += Long.parseLong(s[i]);
		}

		System.out.println(result);
	}

}
