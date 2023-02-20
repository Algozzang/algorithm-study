import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Seonghun {

	static int N; // 행의 수
	static int M; // 열의 수
	static int D; // 궁수 공격 거리
	static int[][] map; // 게임이 진행되는 격자판
	static int answer; // 제거할 수 있는 적의 최대 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// solution
		for (int i = 0; i < M - 2; i++) {
			for (int j = i + 1; j < M - 1; j++) {
				for (int k = j + 1; k < M; k++) {
					// 궁수 위치 3곳을 선택하고 게임 시작
					game(new int[] { i, j, k });
				}
			}
		}

		// result
		System.out.println(answer);
	}

	static void game(int[] archer) {
		int count = 0; // 제거한 적의 수
		boolean[][] removed = new boolean[N][M]; // 병사 제거 여부
		for (int ax = N; ax > 0; ax--) {
			List<int[]> shoot = new ArrayList<>(); // 활을 쏘는 위치
			for (int ay : archer) {
				// 가장 가까운 병사 찾기
				int[] pos = findSoilder(ax, ay, removed);
				if (pos != null) { // 쏠 수 있어!
					shoot.add(pos);
				}
			}
			// 활쏘기
			for (int[] pos : shoot) {
				int r = pos[0], c = pos[1];
				if (!removed[r][c]) {
					removed[r][c] = true;
					count++;
				}
			}
		}
		answer = Math.max(answer, count);
	}

	static int[] findSoilder(int ax, int ay, boolean[][] removed) {
		int[] pos = null;
		int minDist = Integer.MAX_VALUE;
		// 왼쪽부터 탐색
		for (int c = 0; c < M; c++) {
			// 거리 D안에 있는 범위만 고려
			for (int r = ax - 1, d; (d = Math.abs(ax - r) + Math.abs(ay - c)) <= D && r >= 0; r--) {
				// 제거되지 않은 병사가 존재하고, 거리도 최소인 경우
				if (map[r][c] == 1 && !removed[r][c] && d < minDist) {
					pos = new int[] { r, c };
					minDist = d;
				}
			}
		}
		return pos;
	}

}