import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 지한얼두용액2470 {
		static int[] answer=new int[2];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int[] solution = new int[n];
		for(int i=0; i<n; i++) {
			solution[i] = Integer.valueOf(st.nextToken());
		}
		Arrays.sort(solution);
		int now = Integer.MAX_VALUE;
		int start=0;
		int end = n-1;
		while (start <end) {
			
			if(solution[start]+solution[end] ==0) {
				answer[0]=solution[start];
				answer[1]=solution[end];
				break;
			} else {
				int tmp = solution[start]+solution[end];
				if(Math.abs(tmp) <Math.abs(now)) {
					now=tmp;
					answer[0]=solution[start];
					answer[1]=solution[end];
				}
				if(tmp>0) end--;
				else start++;
				
			}
		}
		
		
		
		System.out.println(answer[0]+" "+answer[1]);
	}
	
	
}
		
