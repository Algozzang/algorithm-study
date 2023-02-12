import java.io.*;
import java.util.*;

public class JIHANEOL {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		int end=0; // 끝
		int[] number = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			number[i]= Integer.valueOf(st.nextToken());
			if(end<number[i]) {
				end=number[i];
			}
		}
		int start=0; //시작
		
		while(start<end) {
			long sum=0;
			int center=(start+end)/2;
			for(int i=0; i<N; i++) {
				if(number[i]-center>0) {
					sum+=number[i]-center;
				}
			}
			
			if(sum>=M) {
				start=center+1;
			} else if(sum<M) {
				end=center;  // -1 하고 안하고 ...
			} 
			
		}
		System.out.println(start-1);
	}

}

/*
2 3
1 2
 */
