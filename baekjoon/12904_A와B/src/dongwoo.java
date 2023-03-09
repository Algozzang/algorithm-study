import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] S = br.readLine().toCharArray();
		char[] T = br.readLine().toCharArray();

		List<Character> result = new ArrayList<>();

		for (int i = 0; i < T.length; i++) {
			if (T[i] == 'A') {
				result.add('A');
			} else {
				result.add('B');
			}
		}
		for (int i = T.length-1; i >= S.length; i--) {
			if (result.get(i)=='A') {
				result.remove(result.size()-1);
			} else if (result.get(i)=='B') {
				result.remove(result.size()-1);
				result = reverse(result);
			}
		}
		for (int i = 0; i < S.length; i++) {
			if (S[i]!=result.get(i)) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(1);
	}

	private static List<Character> reverse(List<Character> result) {
		List<Character> res = new ArrayList<>();
		int N = result.size();
		for (int i=0; i<N; i++) {
			res.add(result.get(N-i-1));
		}
		return res;
	}
}