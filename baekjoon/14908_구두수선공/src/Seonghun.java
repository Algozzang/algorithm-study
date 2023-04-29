import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Work {
	int num; // 번호
	int time; // 걸리는 시간
	int cost; // 보상금

	Work(int num, int time, int cost) {
		this.num = num;
		this.time = time;
		this.cost = cost;
	}
}

public class Main {

	static int N; // 작업의 수
	static Work[] works; // 작업들의 정보
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// initialize
		N = Integer.parseInt(br.readLine());
		works = new Work[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			works[i] = new Work(i + 1, time, cost);
		}

		// solution
		Arrays.sort(works, (o1, o2) -> o1.time * o2.cost - o2.time * o1.cost);

		// result
		for (Work work : works) {
			answer.append(work.num).append(" ");
		}
		System.out.println(answer);
	}

}