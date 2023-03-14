import java.io.*;
import java.util.*;

public class JIHANEOL {
    static int N,M,map[][],answer;
    static class Node implements Comparable<Node>{
    	int x,y,c;
    	boolean visit;

		public Node(int x, int y, int c,boolean visit) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.visit = visit;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.c, o.c);
		}
    }
    public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.valueOf(st.nextToken());
	    M = Integer.valueOf(st.nextToken());
	    map = new int[N][M];
	    answer = -1;
	    for (int i = 0; i < N; i++) {
	    	char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp[j]-'0';
			}
		}
	    bfs();
    }
    public static void bfs() {
    	boolean visited[][][] = new boolean[N][M][2]; // 0은 안부수고 간아이 1은 부수고 간 아이
    	int[] dx = {0,0,1,-1};	
    	int[] dy = {1,-1,0,0};	
    	Queue<Node> queue = new LinkedList<>();
    	queue.add(new Node(0, 0, 1, false));
    	
    	while(!queue.isEmpty()) {
    		Node now = queue.poll();
    		
    		if(now.x==N-1 && now.y==M-1) {
    			System.out.println(now.c);
    			return;
    		}
    		
    		for(int i=0; i<4; i++) {
    			int nx = now.x + dx[i];
    			int ny = now.y + dy[i];
    			
    			if(!isRange(nx, ny)) continue;
    			
    			if(map[nx][ny]==1) { // 벽이면
    				if(!visited[nx][ny][0] && !now.visit ) { // 한번이라도 부순벽이 없고 가본적도 없다면
    					queue.add(new Node(nx, ny, now.c+1, true));
    				}
    			}else { // 벽이 아니면
    				if(!now.visit && !visited[nx][ny][0]) { // 한번도 안부순 놈이면
    					visited[nx][ny][0]=true;
    					queue.add(new Node(nx, ny, now.c+1, false));
    				}else if(now.visit && !visited[nx][ny][1]) { // 부순 놈이면
    					visited[nx][ny][1]=true;
    					queue.add(new Node(nx, ny, now.c+1, true));
    					
    				}
    			}
    		}
    	}
    	System.out.println(-1);
    }
    public static boolean isRange(int x, int y) {
    	return x>=0 && y>=0 && x<N && y<M;
    }
}