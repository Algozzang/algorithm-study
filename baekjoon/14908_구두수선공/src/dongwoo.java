import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		Work[] works = new Work[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			works[i] = new Work(i + 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(works);

		for (int i = 0; i < N; i++) {
			sb.append(works[i].number).append(" ");
		}
		System.out.println(sb);
	}

}

class Work implements Comparable<Work> {
	int number;
	int time;
	int S;

	public Work(int number, int time, int S) {
		this.number = number;
		this.time = time;
		this.S = S;
	}

	@Override
	public int compareTo(Work w) {
//		double here = Math.round((double) S * 100000 / time);
//		double other = Math.round((double) w.S * 100000 / w.time);
		double here = (double) S * 100000 / time;
		double other = (double) w.S * 100000 / w.time;
		// 작업 효율 비교 (보상금 대비 걸리는 시간  )
		if (here != other) {
			if (here - other > 0) {
				return -1;
			}
			if (here - other < 0) {
				return 1;
			}
			// 효율이 같으면 시간이 적게 걸리는 순서 
			return this.time - w.time;
		}
		// 시간도 같다면 오름차순 
		return this.number - w.number;
	}

}