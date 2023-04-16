import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Hyekyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()), roomNum[] = new int[N + 1];
		int last = 1, n;
		Lecture cur;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		List<Lecture> list = new ArrayList<>();
		PriorityQueue<Lecture> room = new PriorityQueue<>((o1, o2) -> o1.e - o2.e);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list, (o1, o2) -> o1.s - o2.s);
		cur = list.get(0);
		room.add(new Lecture(cur.num, cur.s, cur.e, last));
		roomNum[cur.num] = last;
		list.remove(0);
		
		for (Lecture l : list) {
			cur = room.peek();
			if (cur.e > l.s) {
				// 끝나는 시간이 제일 빠른 강의보다 먼저 시작하면 새로운 강의실 주기
				n = l.num;
				room.add(new Lecture(n, l.s, l.e, ++last));
				roomNum[n] = last;
			} else {
				// 끝나는 시간이 제일 빠른 강의보다 늦게 시작하면 그 강의실 주면 됨
				n = l.num;
				room.poll();
				room.add(new Lecture(n, l.s, l.e, cur.room));
				roomNum[n] = cur.room;
			}
		}
		sb.append(last + "\n");
		for (int i = 1; i <= N; i++) sb.append(roomNum[i] + "\n");
		System.out.println(sb.toString());
	}

	static class Lecture {
		int num, s, e, room;

		public Lecture(int num, int s, int e) {
			super();
			this.num = num;
			this.s = s;
			this.e = e;
		}

		public Lecture(int num, int s, int e, int room) {
			super();
			this.num = num;
			this.s = s;
			this.e = e;
			this.room = room;
		}

	}
}
