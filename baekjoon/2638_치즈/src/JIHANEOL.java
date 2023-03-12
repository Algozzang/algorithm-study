import java.io.*;
import java.util.*;

public class JIHANEOL {
	static BufferedReader br;
	static StringTokenizer st;
	static int N,M,map[][];
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static boolean visited[][];
	static class Cheese{
		int x,y;

		public Cheese(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static Queue<Cheese> cheeses;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		cheeses = new LinkedList();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
				if(map[i][j]==1) {
					cheeses.add(new Cheese(i, j));
					
				}
			}
		}
		// 밖 과 안 구분
		Queue<int[]> queue = new LinkedList();
		queue.add(new int[] {0,0});
		map[0][0]=2;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i=0; i<4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if(isRange(nx, ny) && map[nx][ny]==0) {
					map[nx][ny]=2;
					queue.add(new int[] {nx,ny});
				}
			}
		}
		System.out.println(solution(map, 0));
		
	}
	public static int solution(int[][] map,int depth) {
		if(cheeses.isEmpty()) {
			return depth;
		}
		cheeses = isRemove(map);
		return solution(map, depth+1);
	}
	public static Queue<Cheese> isRemove(int[][] map) { // 부패한 치즈 제거
		Queue<Cheese> temp = new LinkedList(); // 제거 안할거
		Queue<Cheese> remove = new LinkedList(); // 제거 할거
		while(!cheeses.isEmpty()) {
			Cheese now = cheeses.poll();
			int cnt=0;
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(isRange(nx, ny) && map[nx][ny]==2) { //각 치즈 상태 보기
					cnt++;
				}
			}
			if(cnt>1) { // 2번이상 2면
				map[now.x][now.y]=0;
				remove.add(new Cheese(now.x, now.y));
				
			}else {
				temp.add(new Cheese(now.x, now.y));
			}
		}
		bfs(remove, map);
		return temp; // 제거 안하는 치즈
	}
	public static void bfs(Queue<Cheese> remove,int[][] map) { // 치즈 map 갱신
		while(!remove.isEmpty()) {
			Cheese now = remove.poll();
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(isRange(nx, ny) && map[nx][ny]==0) {
					map[nx][ny]=2;
					remove.add(new Cheese(nx, ny));
				}
			}
		}
	}
	public static boolean isRange(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}
}