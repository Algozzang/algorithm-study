import java.util.Scanner;
import java.io.FileInputStream;

class 김혜경
{
    public static int[] a;
	public static int cnt;
	public static int n;
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			n = sc.nextInt();
			cnt = 0;

			for (int i = 0; i < n; i++) {
				a = new int[n];
				a[0] = i;
				nQueen(1);	
			}
			System.out.printf("#%d %d\n", test_case, cnt);
		}
	}

	public static void nQueen(int c) {
		if(c==n) {
			cnt++;
			return;
		}
		
		for(int i=0; i<n; i++){
			a[c]=i;
			if(check(c)) nQueen(c+1); 
		}
	}

	public static boolean check(int c) {
		boolean exist = true;
		for (int i = 0; i < c; i++) {
			if (a[i] == a[c])
				exist = false; // 같은 행에 퀸이 존재
			else if (Math.abs(i - c) == Math.abs(a[i] - a[c]))
				exist = false; // 대각선 방향에 퀸
		}

		return exist;
	}
}