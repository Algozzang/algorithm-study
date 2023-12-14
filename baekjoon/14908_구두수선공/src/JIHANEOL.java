import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class JIHANEOL {
	static BufferedReader br;
	static StringTokenizer st;
	static class Work implements Comparable<Work>{
		int no,t,s;

		public Work(int no,int t, int s){
			super();
			this.no = no;
			this.t = t;
			this.s = s;
		}
		// 작업 , 보상금 으로 그리디 하게 접근 똑똑하다.  
		@Override
		public int compareTo(Work o) {
			double a = this.t/(double)this.s;
			double b = (double)o.t/o.s;
			
			if(a==b) {
				return this.no - o.no;
			}
			return Double.compare(a, b);
		}
		
	}
	static List<Work> list = new ArrayList();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.valueOf(st.nextToken());
			int s = Integer.valueOf(st.nextToken());
			list.add(new Work(i,t, s));
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		
		for(Work w : list) {
			sb.append(w.no + " ");
		}
		System.out.println(sb);
		
	}

}