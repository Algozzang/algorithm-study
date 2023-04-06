import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Seonghun {

	static int N; // 격자 크기
	static int M; // 파이어볼 개수
	static int K; // 이동 명령 횟수
	static List<FireBall>[][] fireBalls; // 파이어볼 정보
	static int[][] direct = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

	static class FireBall {
		int m; // 질량
		int s; // 속력
		int d; // 방향

		FireBall(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		fireBalls = getGrid();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireBalls[r - 1][c - 1].add(new FireBall(m, s, d));
		}

		// solution
		while (K-- > 0) {
			List<FireBall>[][] grid = getGrid();
			// 파이어볼 이동
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					for (FireBall fireBall : fireBalls[r][c]) {
						int m = fireBall.m;
						int s = fireBall.s;
						int d = fireBall.d;
						int dr = Math.floorMod(r + (s * direct[d][0]), N);
						int dc = Math.floorMod(c + (s * direct[d][1]), N);
						grid[dr][dc].add(new FireBall(m, s, d));
					}
				}
			}

			// 파이어볼 충돌 처리
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int cnt = grid[r][c].size();
					if (cnt >= 2) {
						int sumM = 0; // 질량의 합
						int sumS = 0; // 속력의 합
						int cntOddD = 0; // 방향이 홀수인 방향 수
						for (FireBall fireBall : grid[r][c]) {
							sumM += fireBall.m;
							sumS += fireBall.s;
							if (fireBall.d % 2 == 1) {
								cntOddD++;
							}
						}
						grid[r][c].clear();
						int begin = (cntOddD == 0 || cntOddD == cnt) ? 0 : 1;
						for (int d = begin; d < 8; d += 2) {
							if (sumM / 5 > 0) {
								grid[r][c].add(new FireBall(sumM / 5, sumS / cnt, d));
							}
						}
					}
				}
			}
			// 파이어볼 정보 업데이트
			fireBalls = copyGrid(grid);
		}

		// result
		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (FireBall fireBall : fireBalls[r][c]) {
					answer += fireBall.m;
				}
			}
		}
		System.out.println(answer);
	}

	// 새로운 그리드를 만들어서 반환
	static List<FireBall>[][] getGrid() {
		List<FireBall>[][] grid = new List[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				grid[r][c] = new ArrayList<>();
			}
		}
		return grid;
	}

	// 그리드를 복사하여 반환
	static List<FireBall>[][] copyGrid(List<FireBall>[][] grid) {
		List<FireBall>[][] copyGrid = new List[N][N];
		for (int i = 0; i < N; i++) {
			copyGrid[i] = grid[i].clone();
		}
		return copyGrid;
	}

}
