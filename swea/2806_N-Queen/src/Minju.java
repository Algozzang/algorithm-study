import java.io.*;

public class Minju { // 2806 nqueen

	static int N;
	static boolean[][] arr;
	static boolean row[];
	static boolean col[];
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			row = new boolean[N];
			col = new boolean[N];
			arr = new boolean[N][N];
			result = 0;
			setQueen(0);

			System.out.println("#"+tc+" "+result);
		}
	}

	
	private static void setQueen(int r) { //dfs
		
		if(r == N) { //행 다 채웠으면 종료
			result++; //경우의수++
			return;
		}
		
		for(int j =0;j<N;j++) { 

			if(isPossible(r,j)) { // 놓을 수 있는 자리면
				row[r] = true;
				col[j] = true;
				arr[r][j] = true;
				setQueen(r+1);
				row[r] = false;
				col[j] = false;
				arr[r][j] =false;
			}
		}
		
		

	}
	
	private static boolean isPossible(int r, int c) {

		// 행 체크
		if(row[r]) return false;

		// 열 체크
		if(col[c]) return false;
	
		// 왼쪽 위로 올라가는 대각선 위쪽 체크
		for(int i=1;i<N;i++) {
			if(r-i >= 0 && c-i >= 0 ) { //범위 check
				if(arr[r-i][c-i]) return false;
			}
		}		
		
		//오른쪽 위로 올라가는 대각선 위쪽 체크
		for(int i=1;i<N;i++) {
			if(r-i>=0 && c+i < N) { //범위 check
				if(arr[r-i][c+i]) return false;
			}
		}
		
		return true;
	}
}
