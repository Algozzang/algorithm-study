import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Minju {
	// 간좌표 분리해서 bfs돌리면서 탈출시켰는데 근데.. 바이러스 큐에 넣어서 걔네만 bfs 돌리면 되는군
	static int n ,m, map[][], max;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static boolean[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		for(int i =0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0,0,0);
		System.out.println(max);
	}
	private static void comb(int x , int y,int cnt) {
		if(cnt == 3) { //벽 3개 다 세웠다면
			getSecurity();
			return;
		}
		
		for(int i =x;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j] != 0) continue;
				// 빈칸이면 벽세우기
				map[i][j] = 1;
				comb(i,j,cnt+1);
				map[i][j] = 0; // 되돌리기

			}
		}
		
		
	}
	private static void getSecurity() {
		//맵 클론하고
		int[][] mapClone = new int[n][m];
		for(int i =0;i<n;i++) {
			mapClone[i] = map[i].clone();
		}
		//바이러스 전파
		for(int i =0;i<n;i++) Arrays.fill(visited[i], false);
		for(int i =0;i<n;i++) { // 지점하나씩 탐색하면서 2면 바이러스 퍼뜨리기
			for(int j=0;j<m;j++) {
				if(mapClone[i][j] != 2) continue;
				if(visited[i][j]) continue; // 전 타임에 이미 방문한 배열이면 호출안함
				virus(i,j,mapClone);
			}
		}
		// 바이러스 개수 세고 맥스 업데이트
		int safeNum = 0;
		for(int i =0;i<n;i++) { // 지점하나씩 탐색하면서 2면 바이러스 퍼뜨리기
			for(int j=0;j<m;j++) {
				if(mapClone[i][j] == 0) safeNum++;
			}
		}

		max = Math.max(max, safeNum);
		
	}
	private static void virus(int x, int y, int[][] mapClone) {

		// 2이면 바이러스 퍼뜨리기
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x,y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
			
			Node cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if(nx < 0 || ny <0 || nx>=n || ny >= m) continue;
				if(map[nx][ny] == 1 || visited[nx][ny]) continue; //벽이면 전파 못시킴
				mapClone[nx][ny] = 2;
				visited[nx][ny] = true;
				q.offer(new Node(nx,ny));
			}
		}
		
	}
}
class Node{
	int x;
	int y;
	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}