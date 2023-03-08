import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class JIHANEOL {
	static int map[][], N, M,answer,high[];
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		map = new int[N][M];
		answer=0;
		high = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int j=0; j<M; j++) {
			int num = Integer.valueOf(st.nextToken());
			high[j] =num;
			for(int i=0; i<num; i++) {
				map[i][j]=1;
			}
		}
		for(int i=0; i<M-1; i++) {
			answer+=fill(i);
		}
			
		System.out.println(answer);
		
	}
	public static int fill(int idx) {
		int sum=0;
		for(int i=0; i<high[idx]; i++) {
			int temp=0;
			boolean check=false;
			int j=idx;
			while(++j<M) {
				if(map[i][j]==1) {
					check=true;
					break;
				}
				temp+=1;
			}
			if(check) {
				sum+=temp;
			}
		}
		return sum;
	}
}









