import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class Seonghun {

	static int N;
	static int[][] map;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static List<Integer> answer = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// initialize
		N = Integer.parseInt(br.readLine());
		map = new int[N][];
		for (int i = 0; i < N; i++) {
			map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		// solution
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					int count = 0;
					Queue<int[]> queue = new LinkedList<>();
					queue.offer(new int[] { i, j });
					map[i][j] = 0;
					while (!queue.isEmpty()) {
						int[] p = queue.poll();
						int x = p[0], y = p[1];
						for (int d = 0; d < 4; d++) {
							int dx = x + direct[d][0];
							int dy = y + direct[d][1];
							if (inRange(dx, dy) && map[dx][dy] == 1) {
								queue.offer(new int[] { dx, dy });
								map[dx][dy] = 0;
							}
						}
						count++;
					}
					answer.add(count);
				}
			}
		}

		// result
		answer.sort(null);
		bw.write(answer.size() + "\n");
		for (int a : answer) {
			bw.write(a + "\n");
		}
		bw.flush();
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

}
