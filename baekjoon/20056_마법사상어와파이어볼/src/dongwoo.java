import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class dongwoo {
	static int N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		List<List<List<FireBall>>> map = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			map.add(new ArrayList<>(N));
			for (int j = 0; j < N; j++) {
				map.get(i).add(new ArrayList<>());
			}
		}

		Queue<int[]> fireBallPosition = new ArrayDeque<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireBallPosition.add(new int[] { r - 1, c - 1 });
			map.get(r - 1).get(c - 1).add(new FireBall(d, s, m));
		}

		for (int i = 0; i < K; i++) {
			List<int[]> indexes = new ArrayList<>();
			while (!fireBallPosition.isEmpty()) {
				int[] pos = fireBallPosition.poll();
				FireBall fireBall = map.get(pos[0]).get(pos[1]).get(0);
				// move 하고 (맵 밖으로 나가면? 안하면 되지;;)
				// indexes에다가 옮겨진애 인덱스 저장.
				int[] newPos = getNewPoint(pos[0], pos[1], fireBall.direction, fireBall.speed);
				if (0 <= newPos[0] && newPos[0] < N && 0 <= newPos[1] && newPos[1] < N) {
					indexes.add(newPos);
					map.get(newPos[0]).get(newPos[1]).add(fireBall);
				}
				// 원래자리에 있던 0번 인덱스 지움
				map.get(pos[0]).get(pos[1]).remove(0);
			}
			// temp값이 겹치는곳 4등분 실행.
			boolean visited[][] = new boolean[N][N];
			for (int j = 0; j < indexes.size(); j++) {
				int[] pos = indexes.get(j);
				if (visited[pos[0]][pos[1]]) {
					continue;
				}
				if (map.get(pos[0]).get(pos[1]).size() > 1) {
					int sumMass = 0;
					int sumSpeed = 0;
					int sumDirection = 0;
					List<FireBall> fireBalls = map.get(pos[0]).get(pos[1]);
					for (FireBall fireBall : fireBalls) {
						sumMass += fireBall.mass;
						sumSpeed += fireBall.speed;
						sumDirection += fireBall.direction % 2;
					}
					int size = fireBalls.size();
					int newMass = sumMass / 5;
					int newSpeed = sumSpeed / size;
					int newDirection = sumDirection;
					map.get(pos[0]).get(pos[1]).clear();
					if (newMass > 0) {
						if (newDirection == 0 || newDirection == size) {
							map.get(pos[0]).get(pos[1]).add(new FireBall(0, newSpeed, newMass));
							map.get(pos[0]).get(pos[1]).add(new FireBall(2, newSpeed, newMass));
							map.get(pos[0]).get(pos[1]).add(new FireBall(4, newSpeed, newMass));
							map.get(pos[0]).get(pos[1]).add(new FireBall(6, newSpeed, newMass));
						} else {
							map.get(pos[0]).get(pos[1]).add(new FireBall(1, newSpeed, newMass));
							map.get(pos[0]).get(pos[1]).add(new FireBall(3, newSpeed, newMass));
							map.get(pos[0]).get(pos[1]).add(new FireBall(5, newSpeed, newMass));
							map.get(pos[0]).get(pos[1]).add(new FireBall(7, newSpeed, newMass));
						}
						fireBallPosition.add(pos);
						fireBallPosition.add(pos);
						fireBallPosition.add(pos);
						fireBallPosition.add(pos);
					}
					visited[pos[0]][pos[1]] = true;
				} else {
					fireBallPosition.add(pos);
					visited[pos[0]][pos[1]] = true;
				}
			}
		}
		int res = 0;
		while (!fireBallPosition.isEmpty()) {
			int[] pos = fireBallPosition.poll();
			FireBall fireBall = map.get(pos[0]).get(pos[1]).get(0);
			res += fireBall.mass;
			map.get(pos[0]).get(pos[1]).remove(0);
		}
		System.out.println(res);
	}

	private static int[] getNewPoint(int r, int c, int d, int s) {
		int[] point = new int[] { r, c };
		int[] dr = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dc = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };

		point[0] += s * dr[d] + s * N;
		point[1] += s * dc[d] + s * N;
		if (point[0] < 0) {
			point[0] %= N;
		}
		if (point[0] >= N) {
			point[0] %= N;
		}
		if (point[1] < 0) {
			point[1] %= N;
		}
		if (point[1] >= N) {
			point[1] %= N;
		}

		return point;
	}
}

class FireBall {
	int direction;
	int speed;
	int mass;

	public FireBall(int d, int s, int m) {
		direction = d;
		speed = s;
		mass = m;
	}

}
