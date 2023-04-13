import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class JIHANEOL {
	static BufferedReader br;
	static StringTokenizer st;
	static ArrayList<Mic>[][] board;
	static class Mic{
		int total,direc;

		public Mic( int total, int direc) {
			super();
			
			this.total = total;
			this.direc = direc;
		}
		
	}
	static int N,M,K;
	public static void main(String[] args) throws IOException {
		br  = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.valueOf(br.readLine());
		for(int t=1; t<test+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.valueOf(st.nextToken());
			M = Integer.valueOf(st.nextToken());
			K = Integer.valueOf(st.nextToken());
			board = new ArrayList[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					board[i][j] = new ArrayList();
				}
			}
			
			while(K-->0) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.valueOf(st.nextToken());
				int y = Integer.valueOf(st.nextToken());
				int total = Integer.valueOf(st.nextToken());
				int dir = Integer.valueOf(st.nextToken());
				
				board[x][y].add(new Mic(total, dir));
			}
			
			while(M-->0) {
				move();
				sum();
			}
			int sum=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if (board[i][j].size()>0) {
						for(Mic a:board[i][j]) {
							sum+=a.total;
						}
					}
				}
			}
			System.out.println("#"+t+" "+sum);
			
		}
		
	}
	public static void move() {
		int dx[] = {0,-1,1,0,0};
		int dy[] = {0,0,0,-1,1};
		ArrayList<Mic>[][] newA = new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				newA[i][j] = new ArrayList<>();
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if (board[i][j].size()!=0) {
					
					for(Mic now : board[i][j]) {
						
						int nx = dx[now.direc] + i;
						int ny = dy[now.direc] + j;
						
						if(isRange(nx, ny)) {
							if(now.direc==1|| now.direc==3) {
								now.direc = now.direc+1;
							}else {
								now.direc = now.direc-1;
							}
							now.total = now.total/2;
							if(now.total==0) continue;
						}
						newA[nx][ny].add(new Mic(now.total, now.direc));
					}
				}
			}
		}
		board = newA;
	}
	public static void sum() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if (board[i][j].size()>1) {
					int d=0,sum=0,max=0;
					for(Mic now : board[i][j]) {
						if(max<now.total) {
							max=now.total;
							d=now.direc;
						}
						sum +=now.total;
					}
					board[i][j].clear();
					board[i][j].add(new Mic(sum, d));
				}
			}
		}
	}
	public static boolean isRange(int x,int y) {
		
		return x==0 || y==0 || x==N-1 || y==N-1;
	}
}







