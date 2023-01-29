import java.util.Scanner;

public class ���Ѿ�_9251 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] a = sc.next().toCharArray(); // ��
		char[] b = sc.next().toCharArray(); // ��
		
		int[][] LCS = new int[a.length+1][b.length+1];
		for(int i=1; i<a.length+1; i++) {
			for(int j=1; j<b.length+1; j++) {
				if(a[i-1]==b[j-1]) { //���ٸ�.
					LCS[i][j] = LCS[i-1][j-1]+1;
				} else {
					LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
				}
			}
		}
		System.out.println(LCS[a.length][b.length]);
	}

}
