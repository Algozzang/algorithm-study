import java.io.*;
import java.util.*;

public class JIHANEOL {
	static int[] dx= {0,0,-1,1};
	static int[] dy= {1,-1,0,0};
	static int N;
	static BufferedReader br;
	static int[][] map;
	static List<Integer> list=new ArrayList<>();
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.valueOf(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			char[] c= br.readLine().toCharArray(); 
			for(int j=0; j<N; j++) {
				map[i][j]=c[j]-'0';
			}
		}
		int answer=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==1) {
					int num=bfs(i, j);
					list.add(num);
					answer++;
				}
			}
		}
		System.out.println(answer);
		list.sort((o1,o2)-> o1-o2);
		for(int a: list) {
			System.out.println(a);
		}
	}
	public static int bfs(int now_x,int now_y) {
		int num=1;
		Queue<int[]> myQueue = new LinkedList<>();
		map[now_x][now_y]=2;
		myQueue.add(new int[] {now_x,now_y});
		while(!myQueue.isEmpty()) {
			int[] t = myQueue.poll();
			int x = t[0];
			int y = t[1];
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(nx<0 || ny<0 || nx>=N || ny>=N || map[nx][ny]==0) continue;
				if(map[nx][ny]==1) {
					myQueue.add(new int[] {nx,ny});
					map[nx][ny]=2;
					num++;				
				}
			}
		}
		return num;
	}

}