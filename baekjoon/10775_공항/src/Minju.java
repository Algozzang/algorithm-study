import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Minju {
	// 그냥 완탐시 시간초과!! 문제를 보고 유니온파인드라는 생각을 어떻게 하지 ?!?!??
	/*
	 * p번째 비행기는 입력받은 그대로에 두는 것이 최선책
	 * 입력받은 cur가능하지 않으면 cur-1, cur-2,...에 확인 0번게이트는 없으므로 0까지 가면 불가능
	 * 자기자신으로 parents 초기화 해두고
	 * 자기자신k 도킹가능 하면 바로 도킹하고 k-1으로 부모 변경 그래야 만약 4들어와도 3으로 보낼 수 있음
	 */
	static int parents[];
	static int g,p;
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		g = Integer.parseInt(br.readLine()); // 게이트 수
		p = Integer.parseInt(br.readLine()); // 뱅기 수
		//int gate[] = new int[g+1];
		//ArrayList[] plane = new ArrayList[p];
		boolean[] dock = new boolean[g+1];
		// 자기자신으로 초기화
		parents = new int[g+1];
		for(int i =1;i<=g;i++) {
			parents[i] = i;
		}
		int result = 0;
		// 뱅기리스트랑 게이트배열 입력받기
		for(int i =0;i<p;i++) {
			int cur = Integer.parseInt(br.readLine());
			
			int p = find(cur);
			if(p == 0) { // 0까지 갔으면 0부터cur까지 쓰고 있는 칸이라 못 둠
				break; // 비행기가 어느 게이트에 도킹할 수 없다면 공항 폐쇄
			}
			result++;
			if(p>0)union(p-1,p);

		}

		System.out.println(result);
		
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
}
