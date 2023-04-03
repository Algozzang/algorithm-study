import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Hyekyoung {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] LIS = new int[N+1];
		int input, lastIdx = 0, idx = 0;
		
		LIS[0] = Integer.parseInt(st.nextToken());
		for(int i=1; i<N; i++) {
			input = Integer.parseInt(st.nextToken());
			if(input > LIS[lastIdx]) {
				lastIdx++;
				LIS[lastIdx] = input;
				continue;
			}
			idx = Math.abs(Arrays.binarySearch(LIS, 0, lastIdx, input))-1;
			if(input == LIS[idx+1]) continue;
			LIS[idx] = input;
		}
		System.out.println(lastIdx + 1);
	}
}