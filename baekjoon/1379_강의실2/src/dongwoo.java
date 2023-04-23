import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int K = 0;
		Lecture[] lectures = new Lecture[N + 1];
		PriorityQueue<Lecture> pq = new PriorityQueue<>();
		PriorityQueue<Rooms> roomPQ = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int lectureNum = Integer.parseInt(st.nextToken());
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			lectures[lectureNum] = new Lecture(lectureNum, startTime, endTime);
			pq.add(lectures[lectureNum]);
		}
		while(!pq.isEmpty()) {
			Lecture now = pq.poll();
			if (roomPQ.isEmpty() || roomPQ.peek().endTime>now.startTime) {
				K++;
				roomPQ.add(new Rooms(K, 0));
			}
			Rooms room = roomPQ.poll();
			lectures[now.lectureNum].lectureRoom = room.roomNum;
			room.endTime = now.endTime;
			roomPQ.add(room);
		}
		sb.append(K).append("\n");
		for (int i = 1; i <= N; i++) {
			sb.append(lectures[i].lectureRoom).append("\n");
		}
		System.out.println(sb);
	}
}

class Rooms implements Comparable<Rooms> {
	int roomNum;
	int endTime;
	
	public Rooms(int roomNum, int endTime) {
		this.roomNum = roomNum;
		this.endTime = endTime;
	}

	@Override
	public int compareTo(Rooms o) {
		if (endTime == o.endTime) {
			return roomNum - o.roomNum;
		}
		return endTime - o.endTime;
	}
}

class Lecture implements Comparable<Lecture> {
	int lectureNum;
	int startTime;
	int endTime;
	int lectureRoom;

	public Lecture(int lectureNum, int startTime, int endTime) {
		this.lectureNum = lectureNum;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public int compareTo(Lecture o) {
		return startTime - o.startTime;
	}
}