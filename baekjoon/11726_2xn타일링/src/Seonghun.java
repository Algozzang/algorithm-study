import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// initialize
		int n = Integer.parseInt((new BufferedReader(new InputStreamReader(System.in))).readLine());
		int[] answer = new int[n + 1];

		// solution
		answer[0] = 1; answer[1] = 1;
		for (int i = 2; i <= n; i++) answer[i] = (answer[i - 2] + answer[i - 1]) % 10007;

		// result
		System.out.println(answer[n]);
	}
}
