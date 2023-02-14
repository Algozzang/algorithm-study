import java.util.Arrays;
import java.util.Scanner;

public class Seonghun {

	static int N;
	static final int MAX = 1003001;
	static boolean[] prime = new boolean[MAX + 1];
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// initialize
		N = input.nextInt();
		input.close();
		eratos();
		
		// solution
		for (int n = N; n <= MAX; n++) {
			if (prime[n] && isPalindrome(n)) {
				System.out.println(n);
				break;
			}
		}
	}
	
	static void eratos() {
		Arrays.fill(prime, true);
		prime[1] = false;
		for (int i = 2; i * i <= MAX; i++) {
			if (prime[i]) {
				for (int j = 2 * i; j <= MAX; j += i) {
					prime[j] = false;
				}
			}
		}
	}
	
	static boolean isPalindrome(int n) {
		String s = Integer.toString(n);
		int len = s.length();
		for (int i = 0; i < len / 2; i++) {
			if (s.charAt(i) != s.charAt(len - i - 1)) {
				return false;
			}
		}
		return true;
	}
	
}
