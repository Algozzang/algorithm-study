import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class JIHANEOL {
    static BufferedReader br;
    static StringTokenizer st;
    static int r,c;
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};
    static List<ArrayList<fireBall>> list;
    static int N,M,K;
    static class fireBall{
        int m,s,d;

    public fireBall(int m, int s, int d) {
        this.m = m;
        this.s = s;
        this.d = d;
    }
}
public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    st =new StringTokenizer(br.readLine());
    N = Integer.valueOf(st.nextToken());
    M = Integer.valueOf(st.nextToken());
    K = Integer.valueOf(st.nextToken());
    list = new ArrayList<>(); // 너무 더럽잖어...
    for(int i=0; i<N*N; i++) {
        list.add(new ArrayList<>());  // 각 map 칸마다 리스트 만들어서 파이어볼 담기ㄷㄷ
    }
    for(int i=0; i<M; i++) {
        st = new StringTokenizer(br.readLine());
        int r = Integer.valueOf(st.nextToken());
        int c = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());
        int s = Integer.valueOf(st.nextToken());
        int d = Integer.valueOf(st.nextToken());
        
        list.get((r-1)*N+(c-1)%N).add(new fireBall(m, s, d));
        
    }
    
    /*
     1. 방향으로 속력만큼 이동
     2. 이동 끝나고 2개 이상 파이어볼있다면
         2.1 모두합치고 4개의 파이어볼생성
         2.2 질량은 질량합/5, 
             속력은 속력합/파볼갯수
             방향은 모두 홀이거나 짝이면 0246  아니면 1357
         3 질량이 0이면 소멸쓰 
     */
    
    while(K-->0) {
        moving();
        sumFb();
    }
    int sum = 0;
    for(int i=0; i<list.size(); i++)
        for(fireBall f: list.get(i)) 
            sum += f.m;
    System.out.println(sum);
}
// 움직여주기
	static void moving() {
	    List<ArrayList<fireBall>> newList = new ArrayList();
	    int n = list.size();
	    for(int i=0; i<n; i++)
	    newList.add(new ArrayList());
	    for(int i=0; i<n; i++) {
	        if(list.get(i).isEmpty()) continue;
	        for(fireBall f: list.get(i)) {
	            int r = i/(int)Math.sqrt(n), c = i%(int)Math.sqrt(n); // 현재 위치
	            int nx = range(r+dx[f.d]*f.s%N); 
	            int ny = range(c+dy[f.d]*f.s%N);
	            
	            newList.get(nx*N+ny%N).add(new fireBall(f.m, f.s, f.d));
	        }
	    }
	    list = newList;
	}
// 값 더해주기
	static void sumFb() {
	    int n = list.size();
		    for(int i=0; i<n; i++) {
		        if(list.get(i).size()>1) {
	//                2.2 질량은 질량합/5, 
	//                 속력은 속력합/파볼갯수
	//                 방향은 모두 홀이거나 짝이면 0246  아니면 1357
	                int m=0,s=0,d=0; // 질량, 속도, 방향
	                for(fireBall f: list.get(i)) {
	                    m+=f.m;
	                    s+=f.s;
	                    d+=f.d%2;
	                }
	                m /=5;
	                s/=list.get(i).size();
	//                d = (d==0 || (d==list.get(i).size()))?0:1; // 0이면 홀 짝 1이면 잡다
	                d = (d>0 && d<list.get(i).size())?1:0;
	                list.get(i).clear();
	                if(m==0) continue;
	                for(int q=0; q<4; q++) {
	                    list.get(i).add(new fireBall(m, s, d));
	                    d +=2;
	                }
	
	            }
	        }
    }
    static int range(int point) {
        if(point<0) return point+N;
        if(point>=N) return point-N;
        return point;
    }

}