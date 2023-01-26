
import java.util.ArrayList;
import java.util.Scanner;


public class 지한얼 {
	static int N,max;
	static char[] NUM;
	static ArrayList[] visited;
	
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int t= sc.nextInt();
		for(int test=1; test<=t;test++) {
			NUM = sc.next().toCharArray();
			N = sc.nextInt();
			visited=new ArrayList[N+1];
			for(int i=0;i<N+1;i++) {
				visited[i]=new ArrayList<String>();
			}
			
			dfs(0);
			System.out.println("#"+test+" "+max);
			max=0;
		}
		
	}
	public static void dfs(int depth) {
		if(depth==N) {
			String st = new String(NUM);
			int num=Integer.valueOf(st);
			if(max<num) {
				max=num;
			}
			return;
		}
		
		for(int i=0; i<NUM.length;i++) {
			for(int j=0; j<NUM.length;j++) {
				if(i!=j) {
					swap(i, j);
					if(!visited[depth+1].contains(new String(NUM))) { //없다면 추가
						visited[depth+1].add(new String(NUM));
						dfs(depth+1);
					}
					swap(j, i);
				}
			}
		}
	}
	public static void swap(int s, int e) {
		char temp = NUM[s];
		NUM[s] = NUM[e];
		NUM[e] = temp;
	}

}

