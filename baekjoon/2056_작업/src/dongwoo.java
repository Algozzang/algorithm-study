import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Work[] works = new Work[N + 1];
		PriorityQueue<Work> pq = new PriorityQueue<>((o1, o2) -> o1.endTime - o2.endTime);
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int duration = Integer.parseInt(st.nextToken());
			int before = Integer.parseInt(st.nextToken());
			works[i] = new Work(i, duration, before);
			if (before == 0) {
				works[i].startTime = 0;
				works[i].endTime = duration;
				pq.add(works[i]);
			}
			for (int j = 0; j < before; j++) {
				int first = Integer.parseInt(st.nextToken());
				works[first].afterWork.add(i);
			}
		}
		int endTime = Integer.MAX_VALUE;
		while (!pq.isEmpty()) {
			Work now = pq.poll();
			endTime = now.endTime;
			for (int ind : now.afterWork) {
				works[ind].beforeWork--;
				if (works[ind].beforeWork == 0) {
					works[ind].startTime = now.endTime;
					works[ind].endTime = works[ind].startTime + works[ind].duration;
					pq.add(works[ind]);
				}
			}
		}
		System.out.println(endTime);
	}
}

class Work {
	int number;
	List<Integer> afterWork = new ArrayList<>();
	int startTime;
	int endTime;
	int beforeWork;
	int duration;

	public Work(int number, int duration, int beforeWork) {
		this.number = number;
		this.duration = duration;
		this.beforeWork = beforeWork;
	}

}