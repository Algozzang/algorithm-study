import java.util.Scanner;

public class HyeKyoung {
	// http://boj.kr/d39d288068454423bc3d97d4abb432e7
	// http://boj.kr/ae9134cdff914c4393a49f1e6538fd34

	static boolean[] primeNum = new boolean[2000000];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		primeNum[1] = true;

		setPrime(1003001);

		while (true) {
			if (!primeNum[n] && palindrome(n)) break;

			n++;
		}
		System.out.println(n);
	}

	static boolean palindrome(int n) {
		String str = Integer.toString(n);

		if (str.equals(new StringBuffer(str).reverse().toString())) return true;
		else return false;
	}

	public static void setPrime(int n) {
		for (int i = 2; i <= Math.pow(n, 0.5); i++) {
			if (primeNum[i]) continue;
			for (int j = i + i; j <= n; j += i)
				if (!primeNum[j]) primeNum[j] = true;
		}
	}

}
