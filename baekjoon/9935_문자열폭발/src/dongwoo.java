import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String original = br.readLine();
		String bomb = br.readLine();
		int bombLen = bomb.length();
		Deque<Character> deq = new ArrayDeque<>();
		for (int i = 0; i < original.length(); i++) {
			deq.add(original.charAt(i));
			if (deq.size() >= bombLen && original.charAt(i) == bomb.charAt(bombLen - 1)) {
				// 끝글자가 폭발 끝 글자랑 같다면 꺼내서 확인해봄
				char[] temp = new char[bombLen];
				for (int j = 0; j < bombLen; j++) {
					temp[bombLen - 1 - j] = deq.pollLast();
				}
				for (int j = 0; j < bombLen; j++) {
					if (temp[j] != bomb.charAt(j)) {
						for (int k = 0; k < bombLen; k++) {
							deq.add(temp[k]);
						}
						break;
					}
				}
			}
		}
		if (deq.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			int size = deq.size();
			for (int i = 0; i < size; i++) {
				sb.append(deq.pollFirst());
			}
			System.out.println(sb);
		}
	}
}
