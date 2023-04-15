import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lecture {
	int num;
	int begin;
	int end;

	Lecture(int num, int begin, int end) {
		this.num = num;
		this.begin = begin;
		this.end = end;
	}
}

public class Seonghun {

	static int N; // 강의 개수
	static Lecture[] lectures; // 강의 정보
	static int[] room; // 각 강의에 배정된 강의실 번호 저장
	static PriorityQueue<Lecture> inClass = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// initialize
		N = Integer.parseInt(br.readLine());
		lectures = new Lecture[N];
		room = new int[N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int begin = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			lectures[i] = new Lecture(num, begin, end);
		}

		// 가장 빠른 강의부터 배정
		Arrays.sort(lectures, (o1, o2) -> o1.begin - o2.begin);
		inClass.add(lectures[0]);
		room[lectures[0].num] = 1;

		// 강의실 배정
		for (int i = 1; i < N; i++) {
			// 강의하려는데 남는 강의실이 없네?
			if (lectures[i].begin < inClass.peek().end) {
				inClass.add(lectures[i]);
				room[lectures[i].num] = inClass.size(); // 강의실 늘려줭
			}
			// 그 강의실 다썼지?
			else {
				inClass.add(lectures[i]);
				room[lectures[i].num] = room[inClass.poll().num]; // 강의할거니까 나와줄래?
			}

		}

		// result
		System.out.println(inClass.size());
		for (int i = 1; i <= N; i++) {
			System.out.println(room[i]);
		}
	}

}
