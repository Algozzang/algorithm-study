import java.io.*;
import java.util.*;
public class JIHANEOL {
    static int N,M,map[][],answer;
    static boolean visited[][];
    static class Node{
    	int x,y,c;

		public Node(int x, int y, int c) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
		}
    	
    }
    public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    M = Integer.valueOf(st.nextToken());
	    N = Integer.valueOf(st.nextToken());
	    map = new int[N][M];
	    visited = new  boolean[N][M];
	    answer = Integer.MAX_VALUE;
	    for (int i = 0; i < N; i++) {
	    	char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp[j]-'0';
			}
		}
	    bfs();
	    System.out.println(answer);
    }
    public static void bfs() {
    	int[] dx = {0,0,1,-1};	
    	int[] dy = {1,-1,0,0};	
    	LinkedList<Node> queue = new LinkedList<>(); // 0 1 BFS를 만듬
    	
    	map[0][0]=-1; // 방문 처리
    	
    	queue.add(new Node(0, 0, 0));
    	
    	while(!queue.isEmpty()) {
    		Node now= queue.poll();
    		if(now.c>answer) return;
    		if(now.x==N-1 && now.y==M-1) {
    			answer = Math.min(answer, now.c);
    			return;
    		}
    		for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(isRange(nx, ny) && map[nx][ny]!=-1) {
					if(map[nx][ny]==1) { // 벽이라면
						map[nx][ny]=-1; // 방문 처리
						queue.addLast(new Node(nx, ny, now.c+1)); // 가중치 증가
					}else { // 빈방 
						map[nx][ny]=-1; // 방문 처리
						queue.addFirst(new Node(nx, ny, now.c)); 
					}
				}
    		}
    	}
    }
    public static boolean isRange(int x, int y) {
    	return x>=0 && y>=0 && x<N && y<M;
    }
}