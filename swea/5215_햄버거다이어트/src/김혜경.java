import java.util.Scanner;

//햄버거 다이어트
public class 김혜경 {
	public static int[] t_scr;
	public static int[] cal;
	public static int result=0, n, l;
	
	static void sumScore(int i, int score, int cals) {
		if(cals>=l) return;
		
		if (i == n) {
			if (result < score) {
				result = score;
			}
			return;
		}
		
		sumScore(i + 1, score + t_scr[i], cals + cal[i]);
		sumScore(i + 1, score, cals);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			n=sc.nextInt(); l=sc.nextInt();
			result=0;
			t_scr=new int[n]; cal=new int[n];
			for(int i=0; i<n; i++) {
				t_scr[i]=sc.nextInt();
				cal[i]=sc.nextInt();
			}
			sumScore(0, 0, 0);
			System.out.printf("#%d %d\n", test_case, result);
		}
	}

}
