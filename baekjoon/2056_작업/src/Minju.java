import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Minju { // 위상정렬
	static int N;
	static int[] comeIn;
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		comeIn = new int[N+1];
		int[] workTime = new int[N+1];
		int[] sumTime = new int[N+1];
		List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		
		for(int i =0;i<=N;i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i = 1;i<= N;i++) {
			st = new StringTokenizer(br.readLine());
			workTime[i] = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			while(num-->0) {
				int cur = Integer.parseInt(st.nextToken());
				list.get(i).add(cur);
				comeIn[cur]++;
			}
		}
		for(int i =1;i<=N;i++) {
			if(comeIn[i] == 0) q.add(i);
			sumTime[i] = workTime[i]; 
		}
	
		while(!q.isEmpty()) {
			Integer poll = q.poll();
			for(int i =0;i<list.get(poll).size(); i++) {
				int next = list.get(poll).get(i);
				comeIn[next]--;
				
				sumTime[next] = Math.max(sumTime[next], sumTime[poll]+workTime[next]);
				
				if(comeIn[next] == 0) q.add(next);
			}
		}
		Arrays.sort(sumTime);
		System.out.println(sumTime[N]);
		
		
	}

}
