import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 지한얼트리 { 
	static boolean[] visited;
	static List<Integer>[] graph;
	static int answer=0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.valueOf(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		visited = new boolean[n];
		graph = new List[n];
		for(int i=0; i<n;i++) {
			graph[i] = new ArrayList();
		}
		
		int start=0;
		for(int i=0;i<n;i++) {
			int a=Integer.valueOf(st.nextToken()); 
			if(a==-1) {
				start=i;
			}
			else {
				graph[i].add(a);
				graph[a].add(i);
			}	
		}
		int delete = Integer.valueOf(br.readLine());
		visited[delete]=true;
		
		dfs(start);
		System.out.println(answer);
	}
	public static void dfs(int node) {
		int n=0;
		if(visited[node]) { //방문 했다면
			return;
		} 
		//안했다면
		visited[node]=true;
		for(int nextnode: graph[node] ) {
			if(!visited[nextnode]){//방문안했다면
				n++;
				dfs(nextnode);
			}
		}
		if(n==0)//확인 
			answer++;
		
	}
	
	}

















