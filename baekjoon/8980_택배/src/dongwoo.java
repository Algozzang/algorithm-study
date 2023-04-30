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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		// pq 시작점기준 정렬 
		PriorityQueue<Parcel> pq = new PriorityQueue<>(
				(o1, o2) -> o1.start == o2.start ? o1.end - o2.end : o1.start - o2.start);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Parcel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		// 마지막 택배도 계산하기 위해 시작지가 N인 짐 추가 
		pq.add(new Parcel(N, N, 0));

		Truck truck = new Truck(C);
		while (!pq.isEmpty()) {
			Parcel parcel = pq.poll();
			truck.load(parcel);
		}
		System.out.println(truck.completed);
	}
}

class Truck {
	List<Parcel> payload = new ArrayList<>();
	int capacity = 0;	// 현재 적재 용량 
	int completed = 0;	// 배달 완료 박스 
	int C;		// 총 적재 용량 

	public Truck(int C) {
		this.C = C;
	}

	public void load(Parcel parcel) {
		payload.sort((o1, o2) -> o1.end - o2.end);
		// 기존에 있던 짐 중에 내릴 수 있는거 내리고
		for (int i = payload.size() - 1; i >= 0; i--) {
			if (payload.get(i).end <= parcel.start) {
				completed += payload.get(i).boxes;
				capacity -= payload.get(i).boxes;
				payload.remove(i);
			}
		}
		
		int leftCapa = C - capacity;
		if (parcel.boxes <= leftCapa) {
			// 용량이 되면 그냥 적재 
			payload.add(parcel);
			capacity += parcel.boxes;
		} else {
			// 용량이 안되는 경우
			int size = payload.size();
			int nowBoxes = parcel.boxes;
			if (capacity != C) {
				// 일단 꽉꽉 눌러 담고 
				nowBoxes -= C-capacity;
				payload.add(new Parcel(parcel.start, parcel.end, C-capacity));
				capacity = C;
			}
			for (int i = size-1; i >= 0; i--) {
				// 아직 남아있는 짐 중에 바꾸면 이득인거 바꿔가면서 적재함
				if (payload.get(i).end <= parcel.end || nowBoxes == 0) {
					break;
				} else {
					// 기존 짐이 너무 늦게 내리는경우 새치기함 
					if (nowBoxes>=payload.get(i).boxes) {
						payload.get(i).end = parcel.end;
						nowBoxes -= payload.get(i).boxes;
					} else {
						payload.get(i).boxes -= nowBoxes;
						payload.add(new Parcel(parcel.start, parcel.end, nowBoxes));
						break;
					}
				}
			}
		}
		
	}
}

class Parcel {
	int start;
	int end;
	int boxes;

	public Parcel(int start, int end, int boxes) {
		this.start = start;
		this.end = end;
		this.boxes = boxes;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "start : "+start+", end : "+end+", boxes : "+boxes;
	}
}
