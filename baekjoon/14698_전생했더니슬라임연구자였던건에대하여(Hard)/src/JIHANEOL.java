import java.io.*;
import java.util.*;

public class JIHANEOL {
	// 왜 틀리지>?
	static final int MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(t-->0) {
			long answer = 1;
			int n = Integer.valueOf(br.readLine());
			st =new StringTokenizer(br.readLine());
			PriorityQueue<Long> pq = new PriorityQueue();
			for(int i=0; i<n; i++) {
				pq.add(Long.valueOf(st.nextToken()));
			}
			
			if(n==1) {
				sb.append(answer+'\n');
				continue;
			}
			while(pq.size()>1) {
				long a = pq.poll();
				long b = pq.poll();
				answer = (answer*((a*b)%MOD))%MOD;
				pq.add(a*b);
				
			}
			sb.append(String.valueOf(answer) + '\n');
		}
		
		
		System.out.println(sb);
		
		
	}

}
