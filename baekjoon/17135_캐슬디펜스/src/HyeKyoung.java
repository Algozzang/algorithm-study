import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HyeKyoung {
	static int[][] enemy;
	static int n, m, d, tmpCnt, enemyCnt=0, line=-1, maxCnt = 0;
	static int[] selected = new int[3];
	static int[] dr = {0, -1, 1};
	static int[] dc = {-1, 1, 1};
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		enemy = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				enemy[i][j] = Integer.parseInt(st.nextToken());
				if(enemy[i][j] == 1) {
					if(line==-1) line = i;
					enemyCnt++;
				}
			}
		}
		comb(0, 0);
		System.out.println(maxCnt);
	}
	
	static void comb(int start, int cnt) {
		if(cnt==3) {
			tmpCnt = enemyCnt;
			boolean[][] check = new boolean[n][m];
			int[][] enemyCopy = new int[n][m];	//복사배열 만들기
			for(int i=0; i<enemy.length; i++) enemyCopy[i] = enemy[i].clone();
			game(check, enemyCopy, n-1);
			return;
		}
		
		for (int i = start; i < m; i++) {
			selected[cnt] = i;	//궁수의 열 위치 조합
			comb(i + 1, cnt + 1);
		}
	}
	
	static void game(boolean[][] check, int[][] enemyCopy, int sr) {
		if(sr<line || sr == -1 || tmpCnt==0) {
			if(maxCnt<enemyCnt-tmpCnt) maxCnt = enemyCnt-tmpCnt;
			return;	//더이상 윗 라인에 적이 없거나 남은 적이 없으면 끝
		}
		int dis, r, c, nr, nc;
		
		for(int i=0; i<3; i++) {
			dis=0;
			r=sr; c=selected[i];
			OUTER:while(dis<d) {
				nr=r+dr[0]*dis;	//왼쪽
				nc=c+dc[0]*dis;
				if(checkBound(nr, nc) && enemyCopy[nr][nc]==1) {
					checkEnemy(check, nr, nc);
					break OUTER;
				}
				for(int j=1; j<=2; j++) {
					for(int k=0; k<dis; k++) {
						nr += dr[j];
						nc += dc[j];
						if(checkBound(nr, nc) && enemyCopy[nr][nc]==1) {
							checkEnemy(check, nr, nc);
							break OUTER;
						}
					}
				}
				dis++;
			}
		}
		for (int i=0; i<queue.size();) {
			enemyCopy[queue.poll()][queue.poll()] = 0;
		}
		game(check, enemyCopy, sr-1);
	}
	
	static void checkEnemy(boolean[][] check, int nr, int nc) {
		if(!check[nr][nc]) {
			check[nr][nc]=true;
			queue.offer(nr);
			queue.offer(nc);
			tmpCnt--;
		}
	}
	
	static boolean checkBound(int r, int c) {
		if(r<0 || c<0 || c >= m) return false;
		return true;
	}
}
