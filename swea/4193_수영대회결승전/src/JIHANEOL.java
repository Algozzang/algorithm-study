import java.io.*;
import java.util.*;


public class JIHANEOL {
	static BufferedReader br;
	static StringTokenizer st;
	static boolean visited[][];
	static int N, map[][],end[];
	
	public static void main(String[] args) throws IOException {
		br  = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.valueOf(br.readLine());
		for(int t=1; t<=test; t++) {
			N = Integer.valueOf(br.readLine());
			
			map = new int[N][];
			for(int i=0; i<N; i++) {
				map[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			st = new StringTokenizer(br.readLine());
			int[] start = {Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken())};
			st = new StringTokenizer(br.readLine());
			end = new int[] {Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken())};
			visited = new boolean[N][N];
			Queue<int[]> q = new LinkedList<int[]>();
			q.add(start);
			visited[start[0]][start[1]] = true;
			boolean flag = false;
			int cnt =-1;
			int[] dx = {0,0,-1,1};
			int[] dy = {1,-1,0,0};
			while(!q.isEmpty()) {
				cnt++;
				int size = q.size();
				while(size-->0) {
					int[] now = q.poll();
					if(now[0]==end[0] && now[1]==end[1]) {
						q.clear();
						flag = true;
						break;
					}
					for(int i=0; i<4; i++) {
						int nx = dx[i] + now[0];
						int ny = dy[i] + now[1];
						try {
							if(map[nx][ny]==1 || visited[nx][ny]) continue;
							if(map[nx][ny]==0) {
								visited[nx][ny] = true;
								q.add(new int[] {nx,ny});
							}else {
								if(cnt%3==2) { // 소용돌이 주기 판단 ...
									visited[nx][ny]=true;
									q.add(new int[] {nx,ny});
								}else {
									q.add(new int[] {now[0],now[1]});
								}
							}
						} catch (Exception e) {
							continue;
						}
					}
				}
			}
			if(flag) {
				System.out.println("#"+t+" "+cnt);
			}else {
				System.out.println("#"+t+" "+-1);
			}
			
		}
	}
}