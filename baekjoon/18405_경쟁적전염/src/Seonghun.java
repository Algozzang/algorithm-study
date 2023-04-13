import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Seonghun {

	static int N; // 시험관 크기
	static int K; // 바이러스 종류
	static int[][] virus; // 시험관 정보
	static int S, X, Y; // 시간, 위치
	static int virusCnt; // 바이러스
	static PriorityQueue<Virus> heap = new PriorityQueue<>((o1, o2) -> o1.type - o2.type);
	static int direct[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Virus {
		int x, y; // 바이러스 위치
		int type; // 바이러스 타입

		protected Virus(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		virus = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				virus[i][j] = Integer.parseInt(st.nextToken());
				if (virus[i][j] > 0) {
					virusCnt++;
					heap.add(new Virus(i, j, virus[i][j]));
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		// solution
		while (S-- > 0 && virusCnt < N * N) {
			PriorityQueue<Virus> tmp = new PriorityQueue<>(heap);
			while (!tmp.isEmpty()) {
				Virus v = tmp.poll();
				for (int d = 0; d < 4; d++) {
					int dx = v.x + direct[d][0];
					int dy = v.y + direct[d][1];
					if (inRange(dx, dy) && virus[dx][dy] == 0) {
						virusCnt++;
						virus[dx][dy] = v.type;
						heap.add(new Virus(dx, dy, v.type));
					}
				}
			}
		}

		// result
		System.out.println(virus[X][Y]);
	}

	static boolean inRange(int x, int y) {
		return 1 <= x && x <= N && 1 <= y && y <= N;
	}

}
