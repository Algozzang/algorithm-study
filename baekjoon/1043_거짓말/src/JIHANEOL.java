import java.io.*;
import java.util.*;

public class JIHANEOL {
    static BufferedReader br;
    static StringTokenizer st;
    static List<Integer>[] graph;
    static int truep[], parent[], result;
    public static void main(String[] arg) throws IOException {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	st=new StringTokenizer(br.readLine());
    	int N = Integer.valueOf(st.nextToken());
    	int M = Integer.valueOf(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	int No = Integer.valueOf(st.nextToken()); // 진실아는 사람 수
    	result=0;
    	truep = new int[No];
    	for(int i=0; i<No; i++) { // 진실을 아는 사람 저장
    		int c = Integer.valueOf(st.nextToken());
    		truep[i] =c; 
    	}
    	graph = new List[M];
    	for(int i=0; i<M; i++) { // 그래프값 저장하기
    		st = new StringTokenizer(br.readLine());
    		int c = Integer.valueOf(st.nextToken());
    		graph[i] = new ArrayList<>();
    		for(int j=0; j<c; j++ ) {
    			graph[i].add(Integer.valueOf(st.nextToken()));
    		}
    	}
    	parent = new int[N+1];
    	for(int i=1; i<N+1; i++) { // 대표노드 내자신 초기화
    		parent[i]=i; 
    	}
    	for(int i=0; i<M; i++) { // 그룹 만들기
    		int firstPeople = graph[i].get(0);
    		for(int j=1; j<graph[i].size(); j++) {
    			union(firstPeople,graph[i].get(j));
    		}
    	}
    	for(int i=0; i<M; i++) {
    		boolean isPossible = true;
    		int cur = graph[i].get(0);
    		for(int j=0; j<truep.length; j++) { // 진실을 아는 사람들
    			if(find(truep[j])==cur) { // 파티에 속해있다면 거짓말 못함
    				isPossible= false;
    				break;
    			}
    		}
    		if(isPossible) result++;
    	}
    	System.out.println(result);
    	
    }
    public static void union(int a, int b) {
    	a=find(a); 
    	b=find(b);
    	if(a !=b) {
    		parent[b]=a;
    	}
    }
    public static int find(int a) { //대장찾기
    	if(a==parent[a]) return a;
    	return parent[a] =find(parent[a]);
    }
 
    
}










