import java.io.*;
import java.util.*;

public class JIHANEOL {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st =new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		String[] si = br.readLine().split("");
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(Integer.valueOf(si[0]));
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(int i=1; i<n; i++) {
			int now = Integer.valueOf(si[i]);
			if(cnt==k) {
				stack.add(now);
				continue;
			}
			
			if(stack.peek() < now) {
				while(!stack.isEmpty() &&stack.peek() < now) {
					stack.pop();
					cnt++;
					if(cnt==k) break;
				}
				stack.add(now);
			}else {
				stack.add(now);
			}
		}
		while(stack.size()>n-k) {
			stack.pop();
		}
		for(int i : stack) {
			sb.append(i);
		}
		System.out.println(sb);
	}

}
