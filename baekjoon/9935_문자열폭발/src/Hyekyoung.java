import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Hyekyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine(), explosion = br.readLine();
		int size = explosion.length();
		
		for (int i = 0; i < str.length(); i++) {
			sb.append(str.charAt(i));
			if (sb.length() >= size) {
				if (sb.substring(sb.length() - size, sb.length()).equals(explosion)) {
					sb.delete(sb.length() - size, sb.length());
				}
			}
		}
		System.out.println(sb.length() > 0 ? sb : "FRULA");
	}

}