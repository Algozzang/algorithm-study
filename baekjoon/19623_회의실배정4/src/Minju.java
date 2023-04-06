import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Minju {
	// 모르겠어서 지한얼님 코드 참고하였음
	static int n, dp[], endTime[];
	static Room room[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		room = new Room[n]; 
		dp = new int[n]; 
	
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			room[i] = new Room(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(room); // 끝나는 시간 빠른 순으로 정렬
		dp[0] = room[0].pNum; // 일단 넣고 이따 다시
		for(int i =1;i<n;i++) {
			int mid = binarySearch(0,i,room[i].start);
			if(mid == -1) dp[i] = Math.max(room[i].pNum, dp[i-1]); // 시작지점
			else dp[i] = Math.max(dp[mid]+room[i].pNum, dp[i-1]); // 현재 인원 넣는것과 안 넣는 것
		}

		System.out.println(dp[n-1]);
	}
	private static int binarySearch(int s, int e, int target) {
		while(s<e) {
			int mid = (s+e)/2;
			if(target<room[mid].end) e = mid;
			else s = mid+1;
		}
		return s-1;
	}

}

class Room implements Comparable<Room>{

	int start;
	int end;
	int pNum;
	
	public Room(int start, int end, int pNum) {
		super();
		this.start = start;
		this.end = end;
		this.pNum = pNum;
	}

	@Override
	public int compareTo(Room o) {
		
		return this.end - o.end;
	}
	
}


