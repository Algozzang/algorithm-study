import java.util.Scanner;

public class 지한얼_9251 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] a = sc.next().toCharArray(); // 행
		char[] b = sc.next().toCharArray(); // 열
		
		int[][] LCS = new int[a.length+1][b.length+1];
		for(int i=1; i<a.length+1; i++) {
			for(int j=1; j<b.length+1; j++) {
				if(a[i-1]==b[j-1]) { //같다면.
					LCS[i][j] = LCS[i-1][j-1]+1;
				} else {
					LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
				}
			}
		}
		System.out.println(LCS[a.length][b.length]);
	}

}
