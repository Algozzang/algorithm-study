
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


public class 지한얼관악산 {
		static int[] dp;
		static List<Integer>[] graph;
		static int[] highcheck;
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		st = new StringTokenizer(bf.readLine());
		Map<Integer, Integer> high =new HashMap<>(); 
		dp = new int[n+1]; // 값저장
		
		for(int i=1;i<n+1;i++) {
			high.put(i,Integer.valueOf(st.nextToken())) ; //노드별 높이
		}
		
		// 높이 내림차순 ㄱㄱ
		List<Map.Entry<Integer, Integer>> highList = high.entrySet().stream() // 인덱스, 높이
													.sorted(Map.Entry.comparingByValue((o1,o2) -> //높이 오름차순
															o2.compareTo(o1))).collect(Collectors.toList());
		graph = new ArrayList[n+1];
		
		for(int i=0; i<n+1;i++) {
			graph[i]=new ArrayList<>();
		}
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(bf.readLine());
			int a =Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());
			graph[a].add(b);		
			graph[b].add(a);		
		}
		highcheck = new int[n+1];
		
		for(Map.Entry<Integer, Integer> high1 : highList) {
			highcheck[high1.getKey()]=high1.getValue();
			
		}
		
		for(Map.Entry<Integer, Integer> high1 : highList) {
				
			hiking(high1.getKey(), 0, high1.getKey());
		}
		
		for(int i=1; i<n+1;i++) {
			System.out.println(dp[i]);
		}
	
	}
	static void hiking(int now,int depth,int start) {
		if(dp[start]==0) { // 안간곳이라면
			dp[start]=1;
			}
	
		for(int next_node:graph[now]) {
			if(highcheck[next_node]>highcheck[now]) { //낮으면 안가.
				continue;
			}else { // 높으면 가
				if(dp[next_node]<dp[now]+1) { // dp가 낮다면 나보다 바꿔주고 간다.
					dp[next_node]=dp[now]+1;
					hiking(next_node, depth+1, start);
				}
			}
		}
		
		// 갈곳이 없다...
		
	
	}
	
	
}
