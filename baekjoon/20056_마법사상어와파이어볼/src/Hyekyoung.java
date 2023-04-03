import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Hyekyoung {
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }, dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static List<Fireball>[] fireballs;
	static Queue<Fireball> queue = new ArrayDeque<>();
	static Set<Integer> numSet = new HashSet<>();
	static int N, M, K, nr, nc, num, m, s, d;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r, c;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		fireballs = new ArrayList[N * N + 1];
		for (int i = 0; i <= N * N; i++) {
			fireballs[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			queue.add(new Fireball(r, c, m, s, d));
		}
		move(0);
	}

	static void move(int cnt) {
		Fireball ball;
		if (cnt == K) {
			int mSum=0;
			while(!queue.isEmpty()) {
				ball = queue.poll();
				mSum+=ball.m;
			}
			System.out.println(mSum);
			return;
		}
		while (!queue.isEmpty()) {
			ball = queue.poll();
			m = ball.m;
			s = ball.s;
			d = ball.d;
			nr = ball.r + dr[d] * (s % N);
			nc = ball.c + dc[d] * (s % N);

			if (nr > N) nr = (nr-1) % N + 1;
			else if (nr <= 0) nr = N - Math.abs(nr);
			if (nc > N) nc = (nc-1) % N + 1;
			else if (nc <= 0) nc = N - Math.abs(nc);
			num = N * (nr - 1) + nc;
			numSet.add(num);
			
			fireballs[num].add(new Fireball(nr, nc, m, s, d));
		}
		mergeBalls();
		move(cnt+1);
	}
	
/*
4 4 2
1 2 13 4 3
1 4 12 3 7
4 1 2 5 7
4 2 6 3 0
ans:25

4 6 4
1 1 5 1 1
3 3 5 1 5
1 3 5 1 3
3 1 5 1 7
2 2 5 1 3
3 2 5 1 2
ans:4

4 4 2
1 2 13 4 3
1 4 12 3 7
4 1 2 5 7
4 2 6 3 0
ans:33
 * */

	static void mergeBalls() {
		int n;
		Iterator<Integer> it = numSet.iterator();
		while (it.hasNext()) {
			n = it.next();
			Fireball ball = fireballs[n].get(0);
			if (fireballs[n].size() > 1) {
				boolean same = true;
				int mSum = 0, sSum = 0;
				int dir = fireballs[n].get(0).d % 2;
				for (Fireball f : fireballs[n]) {
					if (dir != f.d % 2) {
						same = false;
					}
					mSum+=f.m;
					sSum+=f.s;
				}
				if(mSum/5>0) {
					if(same) {
						for(int i=0; i<4; i++) {
							queue.add(new Fireball((n-1)/N+1, (n-1)%N+1, mSum/5, sSum/fireballs[n].size(), 2*i));
						}
					}
					else {
						for(int i=0; i<4; i++) {
							queue.add(new Fireball((n-1)/N+1, (n-1)%N+1, mSum/5, sSum/fireballs[n].size(), 2*i+1));
						}
					}
				}
			}else {
				queue.add(new Fireball((n-1)/N+1, (n-1)%N+1, ball.m, ball.s, ball.d));
			}
			fireballs[n].clear();
		}
		numSet.clear();

	}

	static class Fireball {
		int r, c, m, s, d;

		public Fireball(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

	}
}
