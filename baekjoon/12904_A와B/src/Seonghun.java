import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Seonghun {

	static String S, T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// initialize
		S = br.readLine();
		T = br.readLine();

		// solution
		while (S.length() < T.length()) {
			char c = T.charAt(T.length() - 1);
			T = T.substring(0, T.length() - 1);
			if (c == 'B') {
				T = new StringBuilder(T).reverse().toString();
			}
		}

		// result
		System.out.println(S.equals(T) ? 1 : 0);
	}

}
