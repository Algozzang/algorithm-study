
import java.io.*;
import java.util.*;


public class 지한얼종이1780 {
		static String[][] arr; // 종이
		static int[] answer; //답
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.valueOf(br.readLine());
		arr = new String[n][];
		answer=new int[3];
		for(int i=0;i<n;i++) { // 담기
			arr[i] = br.readLine().split(" ");
		}
		papercut(0, 0, n);
		for(int i=0;i<3;i++)
		System.out.println(answer[i]);
	}
	static void papercut(int row,int col,int n) {
		String firs =  arr[row][col]; // 첫 자리
		boolean ispass=true;
		
		if(n==1) {
			check(firs);
			return;
		}
		// 전체 확인
		naga:for(int i=row; i<n+row;i++) {
			for(int j=col; j<n+col; j++) {
				if(!firs.equals(arr[i][j])) { 
					ispass=false;
					break naga;
				}
			}
		}
		if(!ispass) {
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					papercut(row+(n/3)*i, col+(n/3)*j, n/3);
				}
			}
		} else {
			check(firs);
		}
	}
	static void check(String s) {
		if(s.equals("-1")) {
			answer[0]++;
		}else if(s.equals("0")) {
			answer[1]++;
		}else {
			answer[2]++;
		}
	}
}


			
