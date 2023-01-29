import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 지한얼_촌수 {
	static int answer;
	static boolean[] visited;
	static int[] find;
	static List<Integer>[] graph;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		find = new int[]{sc.nextInt(),sc.nextInt()};
		int e = sc.nextInt();
		graph = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		answer=-1;
		for(int i=0;i<e;i++) {
			int a=sc.nextInt(), b=sc.nextInt();
			graph[a].add(b);
			graph[b].add(a);
		}
//		Queue<Integer> myQueue = new LinkedList<Integer>();
		visited = new boolean[n+1];
		visited[find[0]] = true;
		dfs(find[0], 0);
		System.out.println(answer);
		
	}
	
	public static void dfs(int now,int depth) {
		if(find[1]==now) { //찾으면 끝
			answer=depth;
			return;
		}
		for(int node:graph[now]) {
			if(!visited[node]) {
				visited[node]=true;
				dfs(node, depth+1);
			}
		}
		
	}

}
