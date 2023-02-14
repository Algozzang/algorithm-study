import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 시간 자바11기준 148
// 시간 자바8기준 96
public class JIHANEOL {
	static int N,answer;
	static int[][] RGB,temp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		RGB = new int[N][3];// 원본
		temp = new int[N][3]; // 값 갱신 테이블
		answer=1000000;
		for(int i=0; i<N; i++) {
			st =  new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				RGB[i][j]=Integer.valueOf(st.nextToken());
				if(i==0 )
				temp[i][j] =RGB[i][j];
			}
		}
		Solution(0,0,0);
		System.out.println(answer);	
	}
	static void Solution(int depth, int check,int check1) {
		if(depth==0) {
			int n=0;
			int m=0;
			for(int i=0; i<3; i++) {
				// 값 바꿔 주기요
				n=temp[0][(i+1)%3];
				m=temp[0][(i+2)%3];
				temp[0][(i+2)%3]=10001;
				temp[0][(i+1)%3]=10001;
				Solution(depth+1, i,0);
				temp[0][(i+1)%3]=n;
				temp[0][(i+2)%3]=m;
			}
		} else if(depth==N) {
			 for(int i = 0 ; i < 3; i++) {
				 if(i != check) answer = Math.min(answer, temp[N-1][i]);
	         }
		} else  {
			temp[depth][1] = Math.min(temp[depth-1][0], temp[depth-1][2]) + RGB[depth][1];
			temp[depth][0] = Math.min(temp[depth-1][1], temp[depth-1][2]) + RGB[depth][0];
			temp[depth][2] = Math.min(temp[depth-1][0], temp[depth-1][1]) + RGB[depth][2];
			Solution(depth+1, check, check1);	
		}	
	}
}
