import java.io.*;
import java.util.*;


public class JIHANEOL {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.valueOf(st.nextToken()); 
		int k = Integer.valueOf(st.nextToken());
		List<int[]> list = new ArrayList<>();
		int[][] map = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
				if(map[i][j]!=0) {
					list.add(new int[] {map[i][j],i,j});
				}
			}
		}
		Queue<int[]> q = new LinkedList<>();
		list.sort((o1,o2)->{
			return o1[0]-o2[0];
		});
		for(int[] a : list) {
			q.add(new int[] {a[1],a[2],a[0]});
		}
		
		st = new StringTokenizer(in.readLine());
		int s = Integer.valueOf(st.nextToken()); 
		int x1 = Integer.valueOf(st.nextToken()); 
		int y1 = Integer.valueOf(st.nextToken()); 
		int dx[] = {0,0,-1,1};
		int dy[] = {-1,1,0,0};
		while(s-->0) {
			int size = q.size();
			while(size-->0) {
				int[] now = q.poll();
				int c = now[2];
				for(int i=0; i<4; i++) {
					int nx = dx[i]+now[0];
					int ny = dy[i]+now[1];
					
					try {
						if(map[nx][ny]==0) {
							map[nx][ny]=c;	
							q.add(new int[] {nx,ny,c});
						}
						
					} catch (Exception e) {
						continue;
					}
				}
			}
			
		}
		System.out.println(map[x1-1][y1-1]);
	}
}

