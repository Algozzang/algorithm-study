import java.util.Scanner;

public class dongwoo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if (n == 1) {
			System.out.println(2);
			return;
		}
		while (true) {
			if (isPalindrome(n) && isPrime(n)) {
				break;
			}
			n = n + 1 + n % 2; // 짝수면 1 더하고 홀수면 2씩 더함.
		}
		System.out.println(n);
	}

	private static boolean isPrime(int num) {
		if (num <= 1) {
			return false;
		}
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	private static boolean isPalindrome(int n) {
		String number = String.valueOf(n);
		int len = number.length();
		for (int i = 0; i < len; i++) {
			if (2 * i >= len - 1)
				break;
			if (number.charAt(i) != number.charAt(len - i - 1)) {
				return false;
			}
		}
		return true;
	}

}
