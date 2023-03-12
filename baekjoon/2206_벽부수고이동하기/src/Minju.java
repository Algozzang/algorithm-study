import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Minju {

	/*
	 * 1. 시간초과 : dfs 백트래킹으로 풀었다가..
	 * 2. 메모리초과 : bfs 벽을 하나 부쉈는지 매개변수와 그때까지의 visited 배열을 계속 클론해서 넘겨줬음...
	 * 3. ~ 3차원배열이라는 힌트를 얻음 ~ 아하
	 */
	static int n, m, map[][],dp[][], result;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static Queue<Pos> q = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		result = 10000000; // 큰값으로 init

		for(int i =0;i<n;i++) {
			String[] token = br.readLine().split("");
			for(int j =0;j<m;j++) {
				map[i][j] = Integer.parseInt(token[j]);
			}
		}
		
		if(n==1 && m==1) result = 1; 
		else getMinDist();
		
		result = result==10000000?-1:result;
		System.out.println(result);

	}

	private static void getMinDist() {

		boolean[][][] visited = new boolean[n][m][2]; // [][][방문했으면1안했으면0]
		q.add(new Pos(0,0,1,false)); 
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			
			Pos cur = q.poll();
			
			for(int i= 0; i<4; i++) {
				
				int nx = cur.x + dx[i];
				int ny= cur.y + dy[i];
				
				if(nx<0 || nx >=n || ny<0 || ny >= m || (visited[nx][ny][0] && visited[nx][ny][1])) continue;
				
				if(nx == n-1 && ny == m-1) { // 끝까지 도달한 경우 리턴
					result = Math.min(result, cur.dist+1);
					return;
				}
			
		
				if(cur.wall) { // 지금까지 벽을 부순 적 있다면
					if(visited[nx][ny][1]) continue; // 이미 갔던길이면 안가고
					if(map[nx][ny] == 0) { // 0인 경우만 갈 수 있음
						visited[nx][ny][1] = true;
						q.add(new Pos(nx,ny,cur.dist+1, true));
					}
				}
				else { // 부순 적 없다면 벽이있거나 없거나 둘다 갈 수 있음
					
					if(map[nx][ny] == 0) {
						if(visited[nx][ny][0]) continue;
						visited[nx][ny][0] = true;
						q.add(new Pos(nx,ny, cur.dist+1,false));
					}else {
						if(visited[nx][ny][1]) continue;
						visited[nx][ny][1] = true;
						q.add(new Pos(nx,ny,cur.dist+1,true));
					}
				}

			}
		}
	}
	
	static class Pos{
		int x;
		int y;
		int dist;
		boolean wall;
		public Pos(int x, int y, int dist ,boolean wall) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.wall = wall;
	
		}
	}
/*
2 4
0111
0110
답 -1
4 4
0101
0101
0001
1110
답 7
 */
}