import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Minju { // 강의실 PQ + 유니온파인드
	static int N;
	
	static PriorityQueue<Lecture> pq = new PriorityQueue<>((a,b)->(a.e - b.e));
	static Integer room[]; //배정할 강의실
	static Integer room2[]; //배정할 강의실
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb =new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// 시작시간 빠른 애들부터 넣고 우선순위큐는 끝나는 시간 빠른 애로 정렬, 들어갈수있는 강의실있으면 그 강의 배고 새로 넣기
		N = Integer.parseInt(br.readLine());
		Lecture[] lecture = new Lecture[N];
		room = new Integer[N+1];
		room2 = new Integer[N+1];
		for(int i =0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			lecture[i] = new Lecture(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(lecture,(a,b)-> (a.s==b.s)? a.e-b.e:a.s-b.s ); // 시작시간 정렬, 같으면 종료시간 정렬
		pq.add(lecture[0]);
		for(int i =1;i<N;i++) {
			if(pq.peek().e <= lecture[i].s) {
				room[pq.peek().idx] = lecture[i].idx;
				pq.poll();
				pq.add(lecture[i]);
			}else {
				pq.add(lecture[i]);
			}
		}
		sb.append(pq.size()+"\n"); //강의실 최소 개수
		
		int roomIdx = 1;
		while(!pq.isEmpty()) {
			Lecture poll = pq.poll();
			room2[poll.idx] = roomIdx++; // room2는 강의실 배정받은애들
		}

		for(int i =1;i<=N;i++) {
			room2[i] = find(i); // 강의실 찾아주기
		}
		for(int i =1;i<=N;i++) {
			sb.append(room2[i]+"\n");
		}
		System.out.println(sb);
	}
	
	static int find(int a){
		if(room2[a] == null) return find(room[a]);
		return room2[a];
	}

}

class Lecture {
	int idx;
	int s;
	int e;

	public Lecture(int idx, int s, int e) {
		super();
		this.idx = idx;
		this.s = s;
		this.e = e;
	}

}


