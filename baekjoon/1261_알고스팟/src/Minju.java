import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Minju {
	
	// dp인가했는데 상하좌우 왔다갔다하는 경우면 무한루프 => 큐가 아니라 덱을 써야함!!
	// 0인 길은 앞쪽에 넣고 1인 길은 뒤쪽에 넣어서 bfs 
	// 0인애들만 먼저 보니까 visited 해도 노상관
	
	static int n,m,map[][],dp[][], min;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		
		m = Integer.parseInt(nm[0]);
		n = Integer.parseInt(nm[1]);
		map = new int[n][m];
		dp = new int[n][m];
		visited = new boolean[n][m];

		// input
		for(int i =0;i<n;i++) {
			String[] line = br.readLine().split("");
			for(int j = 0;j<m;j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		dp[0][0] = 0;
		bfs(0,0);
		
		for(int i =0;i<n;i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		System.out.println(dp[n-1][m-1]);
	}
	
	
	private static void bfs(int x, int y) {
		
		Pos start = new Pos(x,y);
		Deque<Pos> q = new ArrayDeque<>();
		q.addLast(start);
		visited[0][0] = true;
	
		while(!q.isEmpty()) {
		
			Pos cur = q.pollLast();
			
			for(int i=0;i<4;i++) {
				
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if(nx<0 || nx >=n || ny<0 || ny >= m || visited[nx][ny]) continue;
				visited[nx][ny] = true;
				if(map[nx][ny] == 0) {
	
					q.addLast(new Pos(nx,ny));
					dp[nx][ny] = dp[cur.x][cur.y];
				}else { //1이면 우선순위를 뒤로 보냄

					dp[nx][ny] = dp[cur.x][cur.y]+1;
					q.addFirst(new Pos(nx,ny));
					
				}
			}
		}
	}
}
                                                         
class Pos{
	int x;
	int y;
	public Pos(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
