import java.io.*;
import java.util.*;

public class JIHANEOL {
	static BufferedReader br;
	static StringTokenizer st;
	static int N,M,D,archer[],map[][],archerrow,temp[][],answer,result;
	static Set<int[]> kill;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.valueOf(s[0]);
		M = Integer.valueOf(s[1]);
		D = Integer.valueOf(s[2]);
		archer = new int[M];
		answer=0;
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.valueOf(st.nextToken());
			}
		}
		createArcher(0, 0);
		System.out.println(answer);
	}
	static void createArcher(int idx,int depth) { // 궁수 뽑기
		if(depth==3) {
			result=0;
			archerrow=N;
			game();
			return;
		}
		for(int i=idx; i<M; i++) {
			archer[i]=1;
			createArcher(i+1, depth+1);
			archer[i]=0;
			if(depth==0 && M-3==i) return;
		}
	}
	static void game() { // 게임 시작
		temp=new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				temp[i][j]= map[i][j];
			}
		}
		for(int t=archerrow; t>=1; t--) {	
			kill = new HashSet<>();
			for(int i=0; i<M; i++) {
				if(archer[i]==1) {
					fight(t, i);
//					System.out.println(t+" "+i);
				}
			}
			Kill(); // 한번에 죽여 g하...
		} 
		score();
		if(answer<result) {
			answer=result;
		}
		
	}
	static void fight(int r,int c) { //궁수 위치;
		int min =1000000;
		int y=-1,x=-1;
		for(int i=0; i<=r-1;i++) { // 적행
			if(i<0) continue;
			for(int j=0; j<M; j++) { // 적열
				if(temp[i][j]==1) {
					if((r-i+Math.abs(j-c))<=D && min>=(r-i+Math.abs(j-c))) {
						if(min==(r-i+Math.abs(j-c))) {
							if(y<j) { //오른쪽이면
								continue;
							}
						}
						min=(r-i+Math.abs(j-c)); // 아나 1로 설정 해서....
						x=i;
						y=j;
					}
				}
			}			
		}
		if(x!=-1 && y!=-1)
		kill.add(new int[] {x,y});
	}
	static void score() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++){
				if(temp[i][j]!=map[i][j]) {
					result++;
				}	
			}
		}
	}
	static void Kill() { // 죽여..
		for(int[] c: kill) {
			temp[c[0]][c[1]]=0;
		}
	}
}