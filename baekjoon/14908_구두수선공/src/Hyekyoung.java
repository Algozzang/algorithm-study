import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Hyekyoung {

	public static void main(String[] args) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Work[] works = new Work[N];
		double a, b;
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Double.parseDouble(st.nextToken());
			b = Double.parseDouble(st.nextToken());
			works[i] = new Work(i+1, a/b);
		}
		Arrays.sort(works);
		for (Work work : works) {
			sb.append(work.index+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static class Work implements Comparable<Work>{
		int index;
		double per;

		public Work(int index, double compensation) {
			super();
			this.index = index;
			this.per = compensation;
		}

		@Override
		public int compareTo(Work o) {
			return Double.compare(this.per, o.per);
		}
	}
}