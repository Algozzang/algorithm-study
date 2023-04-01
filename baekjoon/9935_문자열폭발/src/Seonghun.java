import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Seonghun {

	static String str;
	static String bomb;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// initialize
		str = br.readLine();
		bomb = br.readLine();

		// solution
		for (char c : str.toCharArray()) {
			answer.append(c);
			int len = answer.length();
			if (len >= bomb.length() && answer.substring(len - bomb.length()).equals(bomb)) {
				answer.delete(len - bomb.length(), len);
			}
		}

		// result
		System.out.println(answer.toString().isEmpty() ? "FRULA" : answer);
	}

}
