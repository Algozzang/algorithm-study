import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int a = 0;
		int b = 1;
		// 피보나치 
		for (int i = 0; i<N; i++) {
			int temp = (a+b)%10007;
			a = b;
			b = temp;
		}
		System.out.println(b);
	}
}
