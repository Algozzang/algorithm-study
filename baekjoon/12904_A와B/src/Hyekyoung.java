import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hyekyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		StringBuilder sb = new StringBuilder();
		sb.append(T);

		int len = T.length();
		for (int i = 1; i <= len - S.length(); i++) {
			if (sb.toString().charAt(len - i) == 'A') sb.deleteCharAt(len - i);
			else {
				sb.deleteCharAt(len - i);
				sb.reverse();
			}
		}
		System.out.println(sb.toString().equals(S) ? 1 : 0);
	}

}
