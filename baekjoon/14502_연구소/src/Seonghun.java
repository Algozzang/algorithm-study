import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Seonghun {

	private static int N, M; // 지도 크기
	private static int[][] map; // 초기 입력 지도
	private static int[][] lab; // 벽이 설치되어 안전 영역을 확인하기 위한 지도
	private static int safe; // 초기 입력의 안전 영역 크기
	private static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int answer; // 안전 영역의 최대 크기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		lab = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					safe++;
				}
			}
		}

		// 벽 3개를 모든 경우로 설치하고 simulation 실행
		for (int i = 0; i < N * M - 2; i++) {
			if (map[i / M][i % M] != 0)
				continue;
			for (int j = i + 1; j < N * M - 1; j++) {
				if (map[j / M][j % M] != 0)
					continue;
				for (int k = j + 1; k < N * M; k++) {
					if (map[k / M][k % M] != 0)
						continue;
					// 지도 복사
					for (int n = 0; n < N; n++) {
						lab[n] = map[n].clone();
					}
					// 벽 설치
					lab[i / M][i % M] = 1;
					lab[j / M][j % M] = 1;
					lab[k / M][k % M] = 1;

					// 바이러스 실험
					simulation(safe - 3);
				}
			}
		}

		// result
		System.out.println(answer);
	}

	private static void simulation(int safe) {
		// 바이러스 좌표 저장
		Queue<int[]> virus = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (lab[i][j] == 2) {
					virus.add(new int[] { i, j });
				}
			}
		}
		// 바이러스가 퍼져
		while (!virus.isEmpty()) {
			int[] v = virus.poll();
			int x = v[0];
			int y = v[1];
			for (int d = 0; d < 4; d++) {
				int dx = x + direct[d][0];
				int dy = y + direct[d][1];
				if (inRange(dx, dy) && lab[dx][dy] == 0) {
					virus.add(new int[] { dx, dy });
					lab[dx][dy] = 2;
					safe--;
					// 이미 지금까지 나온 최대 안전 영역보다 작은 경우 중단
					if (safe < answer) {
						return;
					}
				}
			}
		}

		answer = Math.max(answer, safe);
	}

	private static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

}
