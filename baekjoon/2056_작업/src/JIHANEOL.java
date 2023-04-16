	import java.io.*;
	import java.util.*;
	
	public class JIHANEOL {
		static int N;
		static StringTokenizer st = null;
		static int[] duty;
		public static void main(String[] args) throws Exception{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.valueOf(br.readLine());
			int max = 0;
			duty = new int[N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int t = Integer.valueOf(st.nextToken());
				int n = Integer.valueOf(st.nextToken());
				if(n==0) {
					duty[i] = t;
				}else {
					int check = 0;
					for(int j=0; j<n; j++) {
						int c = Integer.valueOf(st.nextToken());
						if(duty[c-1] > check) {
							check = duty[c-1];
						}
					}
					duty[i] = check +t;
				}
				
				if(max < duty[i]) {
					max = duty[i];
				}
			}
			System.out.println(max);
		}
	
	}