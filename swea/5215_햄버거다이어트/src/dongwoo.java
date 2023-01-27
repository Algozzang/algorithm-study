import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Math;

public class dongwoo {
	
	public static void main (String[] args) throws IOException {
		// 시작시간 : 11:06
		// N이 20이니까 조합으로 풀 수 있지 않을까? 근데 이제 DP를 좀 가미한..
		// 종료시간 : 12:01
        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        
        int T;
        T= Integer.parseInt(br.readLine());

        for (int test_case=1; test_case<=T; test_case++) {
        	String[] tmp = br.readLine().split(" ");
        	int N = Integer.parseInt(tmp[0]);
        	int L = Integer.parseInt(tmp[1]);
        	
        	int[] likeness = new int[N];
        	int[] calories = new int[N];
        	
        	for(int i=0; i<N; i++) {
        		tmp = br.readLine().split(" ");
        		likeness[i] = Integer.parseInt(tmp[0]);
        		calories[i] = Integer.parseInt(tmp[1]);
        	}
        	
        	int treeNodes = (int)Math.pow(2,(N+1))-1;
        	int[] calcedCalories = new int[treeNodes]; //누적 합 저장
        	int[] calcedLikeness = new int[treeNodes]; //누적 합 저장
        	
        			
        	for(int i=1; i<treeNodes; i++) {
        		if (i%2==0) {
            		calcedCalories[i] = calcedCalories[(i-1)/2] + calories[(int)log2(i+1)-1];  
            		if (calcedCalories[i]>L) {
            			calcedLikeness[i] = -1;
            		} else {
            			calcedLikeness[i] = calcedLikeness[(i-1)/2] + likeness[(int)log2(i+1)-1];            			
            		}
        		} else {
        			calcedCalories[i] = calcedCalories[(i-1)/2];
        			calcedLikeness[i] = calcedLikeness[(i-1)/2];
        		}
        	}
        	int max = 0;
        	for (int i=treeNodes/2; i<treeNodes;i++) {
        		if (calcedLikeness[i]> max) {
        			max = calcedLikeness[i];
        		}
        	}
        	System.out.println("#"+test_case+" "+max);
        }
	}
	
	static double log2(int x) {
		return Math.log10(x) / Math.log10(2);
	}
}
