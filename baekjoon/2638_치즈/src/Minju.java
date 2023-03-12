import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Minju {

	/*
	 * 안쪽 첨에 2로 초기화 바깥은 0 치즈는 1
	 * 바깥 실내 부분 floodfill해서 체크하고 그럼 안쪽과 분리
	 * 1시간지날때마다 다시 체크해주고
	 * 바깥 2개이상 접하면 치즈 녹이기
	 */
	static int n,m,map[][], cheeseNum;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static boolean visited[][];
	static boolean lastvisited[][];
	static Queue<int[]> q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
	
		// init 2로 채워두기
		for(int i =0;i<n;i++) Arrays.fill(map[i], 2);
		
		for(int i =0;i<n-1;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<m-1;j++) {
				int n = Integer.parseInt(st.nextToken());
				if( n == 1) { // 일단 치즈만 1로 받아두기
					cheeseNum++;
					map[i][j] = 1;
				}
			}
		}
		// input 치즈는 가장자리에 못옴 = 가장자리 0으로 배치
		Arrays.fill(map[0], 0);
		Arrays.fill(map[n-1], 0);
		for(int i =0;i<n;i++) {
			map[i][0]= 0;
			map[i][m-1] = 0;
		}
		//0,0이 바깥이니까 일단 넣고
		q.add(new int[] {0,0});
		visited[0][0] =true;
		int hour = 0;
		while(cheeseNum>0) { // 치즈 있을 때까지만 루프
			melt();
			hour++;
			
		}
		
		System.out.println(hour);
	}

	private static void melt() {
	
		//바깥 만들기
		outerFill();

		//녹일 치즈리스트  한번에 녹여야함!
		List<int[]> cheese = new LinkedList<>();
		
		// 1시간 치즈 삭제
		for(int i =1;i<n-1;i++) {
			for(int j = 1;j<m-1;j++) {
				if(cheeseNum == 0) return;
				if(map[i][j] == 1) { 
					if(check(i,j)) {// 녹는 있는 치즈면
						cheeseNum--;
						cheese.add(new int[] {i,j});//녹일 치즈
						q.add(new int[] {i,j}); //치즈녹았으면 이제 바깥되니까 큐에 넣기
					}
				}
			}
		}
		
		for(int i =0;i<cheese.size();i++) {
			map[cheese.get(i)[0]][cheese.get(i)[1]] = 0;
		}
		
	}

	private static boolean check(int x, int y) {
		int meet = 0;
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(map[nx][ny] == 0) meet++;
		}
		if(meet >=2) return true;
		else return false;
	}

	private static void outerFill() {

		while(!q.isEmpty()) {
			// 바깥 실내 부분을 전부 floodfill
			int[] cur = q.poll();
			int curx = cur[0];
			int cury = cur[1];

			for(int i=0;i<4;i++) {
				
				int nx = curx + dx[i];
				int ny = cury + dy[i];
					
				if(nx<0 || nx >=n || ny<0 || ny >= m || visited[nx][ny] || map[nx][ny] == 1) continue;
				map[nx][ny] = 0; //바깥으로만들기
				visited[nx][ny] = true;
				q.add(new int[] {nx,ny});
			}
		}
			
	}

	
}
