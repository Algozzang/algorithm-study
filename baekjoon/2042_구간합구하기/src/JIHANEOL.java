import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class JIHANEOL {
	static long[] tree;
	static int N,M,K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken()); // 수변경 횟수
        K = Integer.valueOf(st.nextToken()); // 구간합 구하는 회수
        int cnt=1;
        while(Math.pow(2, cnt)<N) cnt++;
        int start = (int)(Math.pow(2, cnt));
        tree = new long[start*2];
        for(int i=start; i<start+N; i++) {
        	tree[i] = Long.parseLong(br.readLine());
        }
        setTree(start*2-1);
        for(int i=0; i<M+K; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.valueOf(st.nextToken());
        	int b = Integer.valueOf(st.nextToken());
        	long c = Long.valueOf(st.nextToken());
        	if(a==1) { // update
        		long diff = tree[start-1+b]-c;
        		tree[start-1+b]=c;
        		update((start-1+b)/2,diff);
        	}else { // 구간합
        		System.out.println(getSum(start-1+b, start-1+(int)c));
        	}
        }
        
        
    }
    public static void setTree(int end) {
    	while(end!=1) {
    		tree[end/2]+=tree[end];
    		end--;
    	}
    }
    public static void update(int idx,long diff) { // 바꿀 인덱스, 변경값
    	while(idx!=1) {
    		tree[idx]-=diff;
    		idx/=2;
    	}
    }
    public static long getSum(int start, int end) {
    	long sum=0;
    	while(start<=end) {
    		if(start%2==1)  sum+=tree[start];
    		if(end%2==0) sum+=tree[end];
    		start=(start+1)/2;
    		end = (end-1)/2;
    	}
    	return sum;
    }
}



