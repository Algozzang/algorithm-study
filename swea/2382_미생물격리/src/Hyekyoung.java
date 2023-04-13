import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Hyekyoung {
	static int N, M, K, map[][], sum = 0;
	static Queue<Microbe> moveQueue = new ArrayDeque<>();
	static List<Microbe>[] dieList;
	static Set<Integer> set = new HashSet<>();
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			sum = 0;
			dieList = new ArrayList[N * N];
			for (int i = 0; i < N * N; i++) dieList[i] = new ArrayList<>();

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				moveQueue.add(new Microbe(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1));
			}
			move(0);
			sb.append("#" + tc + " " + sum + "\n");
		}
		System.out.println(sb.toString());
	}

	static void move(int time) {
		// 미생물 이동
		Microbe mic;
		int num, nr, nc, cnt, dir;

		while (!moveQueue.isEmpty()) {
			mic = moveQueue.poll();
			cnt = mic.cnt;
			dir = mic.dir;

			nr = mic.r + dr[dir];
			nc = mic.c + dc[dir];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			num = N * nr + nc;
			set.add(num);
			dieList[num].add(new Microbe(nr, nc, cnt, dir));
		}
		die(time + 1);
	}

	static void die(int time) {
		int r = 0, c = 0, cnt, dir, d = -1;
		for (int num : set) {
			int mSum = 0, max = 0, size = dieList[num].size();
			for (Microbe mic : dieList[num]) {
				r = mic.r;
				c = mic.c;
				cnt = mic.cnt;
				dir = mic.dir;

				if (size > 1) {
					// 같은 자리에서 모인 경우
					mSum += cnt;
					if (max < cnt) {
						// 제일 많은 애 메모메모
						max = cnt;
						d = dir;
					}
				} else {
					if (r == N - 1 || r == 0 || c == N - 1 || c == 0) {
						// 끄트머리에서 반띵
						cnt /= 2;
						dir = (dir < 2) ? 1 - dir : 5 - dir;
					}
					if (cnt > 0) moveQueue.offer(new Microbe(r, c, cnt, dir));
				}
			}
			if (size > 1) {
				moveQueue.offer(new Microbe(r, c, mSum, d));
			}
			dieList[num].clear();
		}

		set.clear();

		if (time == M) {
			// 시간이 되면 합 구하기
			Microbe mic;
			while (!moveQueue.isEmpty()) {
				mic = moveQueue.poll();
				sum += mic.cnt;
			}
			return;
		}

		move(time);
	}

	static class Microbe {
		int r, c, cnt, dir;

		public Microbe(int r, int c, int cnt, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = dir;
		}
	}
}