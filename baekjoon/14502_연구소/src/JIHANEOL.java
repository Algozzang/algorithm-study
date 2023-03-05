import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class JIHANEOL{
	static List<int[]> virus;
	static int map[][], N, M,answer;
	static BufferedReader br;
	static StringTokenizer st;
	static int[] dx = new int[] {0,0,-1,1};
	static int[] dy = new int[] {1,-1,0,0};
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		map = new int[N][M];
		virus = new ArrayList<>();
		answer=0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int num= Integer.valueOf(st.nextToken());
				map[i][j] = num;
				if(num==2) {
					virus.add(new int[] {i,j});
				}
			}
		}
		wall(0);
		System.out.println(answer);
		
		
		
		

	}
	public static void bfs(int x, int y,int[][] laboratory) {
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = dx[i]+now[0];
				int ny = dy[i]+now[1];
				
				if(nx>=N || nx<0 || ny<0 || ny>=M || laboratory[nx][ny]==1 || laboratory[nx][ny]==2) continue;
				laboratory[nx][ny]=2;
				q.add(new int[] {nx,ny});
			}
		}
		
		
	}
	public static void wall(int depth){ //벽설치
		if(depth==3) {
			int[][] temp = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					temp[i][j] = map[i][j];
				}
			}
			for(int[] a : virus) {
				bfs(a[0],a[1],temp);
			}
			
			answer = Math.max(sum(temp), answer);
			return;
		}
		for(int i=0; i<M*N-2; i++) {
			if(map[i/M][i%M]!=0) continue;
			for(int j=i+1; j<M*N-1; j++) {
				if(map[j/M][j%M]!=0) continue;
				for(int k=j+1; k<M*N; k++) {
					if(map[k/M][k%M]!=0) continue;
					map[i / M][i % M] = 1;
					map[j / M][j % M] = 1;
					map[k / M][k % M] = 1;
					wall(3);
					map[i / M][i % M] = 0;
					map[j / M][j % M] = 0;
					map[k / M][k % M] = 0;
					
				}
			}
		}
	}
	public static int sum(int[][] arr) {
		int num=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==0) {
					num++;
				}
			}
		}
		return num;
	}

}








