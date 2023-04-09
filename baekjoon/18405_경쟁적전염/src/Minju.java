import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Minju {
	static int x, y, n, k, s;
	static long B;
	static int[][] map;

	static boolean[][] visited;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static PriorityQueue<Virus> q = new PriorityQueue<>();
	static PriorityQueue<Virus> q2 = new PriorityQueue<>();
	static int result;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		/* input */
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) q2.add(new Virus(i,j,map[i][j]));
			}
		}
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken())-1;
		y = Integer.parseInt(st.nextToken())-1;
		
		result = map[x][y];
	
		for(int i =0;i<s;i++) {
			
			for(Virus v: q2) {
				q.add(v);
			}
			q2.clear();
			result = bfs();

			if(result != 0) break;
		}
		
		System.out.println(result);

	}
	private static int bfs() {

		// pq 두개 써서 옮김
		while(!q.isEmpty()) {
			Virus virus = q.poll();
		
			for(int i =0;i<4; i++) {
				int nx = virus.x + dx[i];
				int ny = virus.y + dy[i];
				int num = virus.num;
			
				if(nx<0 || ny <0 || nx>=n || ny>=n || visited[nx][ny]) continue;
				if(map[nx][ny] != 0) continue;
				
				if(nx == x && ny == y) return num;
				map[nx][ny] = num;
				visited[nx][ny] = true;
				q2.add(new Virus(nx,ny,num));
		
			}
		}
		
		return result;

	}
}

class Virus implements Comparable<Virus>{
	int x;
	int y;
	int num;
	public Virus(int x, int y, int num) {
		super();
		this.x = x;
		this.y = y;
		this.num = num;
	}
	@Override
	public int compareTo(Virus o) {
		return this.num - o.num;
	}

}