import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {
	
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class seonghun {

	static int time;
	static int N, K, L;
	static int[] X;
	static char[] C;
	static int[][] map; // 0(공백), 1(뱀), 2(사과)
	static int[][] direct = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // 방향

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// initialize
		time = 0;
		N = input.nextInt();
		map = new int[N][N];
		map[0][0] = 1;

		K = input.nextInt();
		for (int i = 0; i < K; i++) {
			map[input.nextInt() - 1][input.nextInt() - 1] = 2; // 사과 표시
		}

		L = input.nextInt();
		X = new int[L + 1];
		C = new char[L];
		for (int i = 0; i < L; i++) {
			X[i] = input.nextInt();
			C[i] = input.next().charAt(0);
		}
		X[L] = 10000;

		// solution
		int hx = 0, hy = 0; // 머리의 위치
		int dir = 0; // 머리의 방향
		boolean crash = false; // 충돌 여부
		Queue<Point> snake = new LinkedList<>(); // 뱀이 위치한 좌표들을 저장
		snake.offer(new Point(0, 0));
		
		for (int i = 0; i < L + 1; i++) {
			// 앞으로 이동
			while (time < X[i]) {
				// 시간 증가
				time++;
				
				int dx = hx + direct[dir][0];
				int dy = hy + direct[dir][1];
				
				// 벽 또는 자기 몸에 충돌하는 경우
				if (!inRange(dx, dy) || map[dx][dy] == 1) {
					crash = true;
					break;
				}
				
				// 꼬리 이동
				if (map[dx][dy] != 2) {
					map[snake.peek().x][snake.peek().y] = 0;
					snake.remove();
				}
				
				// 머리 이동
				hx = dx;
				hy = dy;
				map[hx][hy] = 1;
				snake.add(new Point(hx, hy));
			}

			// 방향 전환
			if (i < L) {
				if (C[i] == 'L') {
					dir = (dir + 3) % 4;
				} else if (C[i] == 'D') {
					dir = (dir + 1) % 4;
				}
			}

			// 충돌시 게임 종료
			if (crash) {
				break;
			}
		}

		// result
		System.out.println(time);
	}
	

	private static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

}

