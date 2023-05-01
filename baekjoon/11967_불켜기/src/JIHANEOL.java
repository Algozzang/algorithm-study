import java.io.*;
import java.util.*;

public class JIHANEOL {
	static BufferedReader br;
	static StringTokenizer st;
	static boolean map[][],On[][];
	static boolean visited[][][];
	
	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		List<Point>[][] list = new List[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				list[i][j] = new ArrayList();
			}
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			int x1 = Integer.valueOf(st.nextToken());
			int y1 = Integer.valueOf(st.nextToken());
			list[x-1][y-1].add(new Point(x1-1, y1-1));
		}
		int turnOn = 1;
		int dx[] = {0,0,1,-1};
		int dy[] = {1,-1,0,0};
		map = new boolean[N][N];
		
		//달이 차오른다  1억 크기 까지 만들수 있다. 메모리 몇십 매거 차지
		visited = new boolean[N][N][N*N];
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {0,0,1});
		map[0][0] = true;
		visited[0][0][1] =true;
		On = new boolean[N][N];
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int key = now[2];
			
			// KEY 와 현재 turnOn과 비교
			if(turnOn!=key) continue;
			// 불 킬수 있으면 키자..
			if(!On[x][y] && list[x][y].size()!=0) {
				On[x][y] = true;
				for(Point p : list[x][y]) {
					if(map[p.x][p.y]==false) {
						map[p.x][p.y] = true;
						turnOn++;
					}
				}
			}
			for(int i=0; i<4; i++) {
				int nx = x+ dx[i];
				int ny = y+ dy[i];
				
				try {
					if(map[nx][ny] && !visited[nx][ny][turnOn]) {
						visited[nx][ny][turnOn]=true;
						if(key<turnOn) {
							visited[x][y][turnOn] = true;
						}
						q.add(new int[] {nx,ny,turnOn});
					}
				} catch (Exception e) {
					continue;
				}
			}
		}
		System.out.println(turnOn);
	}
}