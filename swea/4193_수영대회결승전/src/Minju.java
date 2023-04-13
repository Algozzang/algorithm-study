import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Minju {
	// 소용돌이 위에서 이동가능함.
	// user problem이라그런지 지문읽기가 좀..
	static int n, map[][], endX, endY;
	static Queue<Pos> q;
	static List<Pos> list=  new ArrayList<>();
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static boolean[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int t = Integer.parseInt(br.readLine());
		for(int tc =1;tc<=t;tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			visited = new boolean[n][n];
			
			q = new LinkedList<>();
			list.clear();
			for(int i =0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}	
			st = new StringTokenizer(br.readLine());
			// 그냥 계산하기 편하게 1초부터 시작해서 3나눠질때 소용돌이 안보이는거로
			q.add(new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),1));
			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
	
			bfs();
			if(list.size() == 0) {
				sb.append("#"+tc+" -1\n");
			}else {
				Collections.sort(list);
				sb.append("#"+tc+" "+ list.get(0).time+"\n");
			}
		}	
		System.out.println(sb);
	}
	private static void bfs() {
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
		
			visited[cur.x][cur.y] = true;
			for(int i =0;i<4;i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx<0 || ny<0|| nx>=n||ny>=n || map[nx][ny] == 1|| visited[nx][ny]) continue; // 섬 못감
				if(nx == endX && ny == endY) {
					list.add(new Pos(nx,ny,cur.time));
				}
				// 안가고 그자리에서 기다리기
				if(map[nx][ny] == 2) {
					if(cur.time % 3 ==0) {
						q.add(new Pos(nx,ny,cur.time+1)); 
					}else {
						q.add(new Pos(cur.x,cur.y,cur.time+1)); 
					}
					continue;
				}
				// 0 인길 가기 
				q.add(new Pos(nx,ny,cur.time+1));
				visited[nx][ny] = true;
			}
		}
		
	}
}

class Pos implements Comparable<Pos>{
	int x;
	int y;
	int time;
	
	public Pos(int x, int y, int time) {
		super();
		this.x = x;
		this.y = y;
		this.time = time;
	}

	@Override
	public int compareTo(Pos o) {
		return this.time-o.time;
	}
}