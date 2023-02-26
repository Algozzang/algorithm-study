import java.util.Scanner;

public class JIHANEOL {
	static int N;
	static long M;
	static int[][] matrix, E;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextLong(); 
		matrix = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				matrix[i][j]=sc.nextInt();
			}
		}
		E = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) {
					E[i][j]=1;
				}
			}
		}
		int[][] answer=divide(M);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(answer[i][j]+" ");
				
			}System.out.println();
		}
	}
	public static int[][] divide(long n){
		
		if(n==1) {
			return multipli(matrix, E);
		}
		
		if(n%2==0) { // 짝수면
			int[][] t= divide(n/2);
			return multipli(t,t);
		}else { // 홀수면
			int[][] t = divide(n-1);
			return multipli(matrix, t);
		}
		
		
	}
	public static int[][] multipli(int[][] a, int[][] b){
		int[][] temp = new int[N][N];
		int sum = 0;
		for(int idx=0; idx<N; idx++) { // idx
			for(int idy=0; idy<N; idy++) { // idy
				
				for(int i=0; i<N; i++) {
					sum += a[idx][i]*b[i][idy];
				}
				temp[idx][idy] = sum%1000;
				sum=0;
			}
			
		}
		return temp;
	}
}
