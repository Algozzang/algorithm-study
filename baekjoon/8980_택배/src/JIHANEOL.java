import java.io.*;
import java.util.*;

// 머리가 좋아야한다. 초딩들 무야 
public class JIHANEOL {
	static BufferedReader br;
	static StringTokenizer st;
	static class Cupang implements Comparable<Cupang>{
		int from,to,cost;

		public Cupang(int from,int to, int cost) {
			super();
			
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Cupang o) {
			return this.to == o.to ? Integer.compare(this.from, o.from) : Integer.compare(this.to, o.to);
		}
		
	}
	public static int[] cp;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int C = Integer.valueOf(st.nextToken());
		cp = new int[N];
		int total = 0;
		int M = Integer.valueOf(br.readLine());
		List<Cupang> list = new ArrayList();
		Arrays.fill(cp,C);
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int cost = Integer.valueOf(st.nextToken());
			list.add(new Cupang(from, to , cost));
		}
		Collections.sort(list);
		
		for(int i=0; i<M; i++) {
			Cupang now = list.get(i);
			int min = find(now.from,now.to);
			// 양수면
			if(min-now.cost>=0) {
				total+=now.cost;
				update(now.from, now.to, now.cost);
			}else {
				total+=min;
				update(now.from, now.to, min);
			}
		}
		System.out.println(total);
		
	}
	// 최소 찾기
	static int find(int s, int e) {
		int min = 10005;
		for(int i=s; i<e; i++) {
			if (cp[i]<min) {
				min=cp[i];
			}
		}
		return min;
	}
	// 갱신하기
	static void update(int s, int e,int num) {
		for(int i=s; i<e; i++) {
			cp[i]-=num;
		}
	}
}