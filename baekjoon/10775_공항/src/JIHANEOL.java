	import java.io.*;
	import java.util.*;
	
	public class JIHANEOL{
		static BufferedReader br;
		static StringTokenizer st;
		static int G,P,parents[];
		static boolean[] docking;
		public static void main(String[] args) throws Exception{
			br = new BufferedReader(new InputStreamReader(System.in));
			G = Integer.parseInt(br.readLine());
			P = Integer.parseInt(br.readLine());
			docking = new boolean[G+1];
			int answer =0;
			parents= new int[G+1];
			for(int i=1; i<=G; i++) {
				parents[i]=i;
			}
			for(int i=0; i<P; i++) {
				int num = Integer.valueOf(br.readLine());

                if(!docking[find(num)]) { // false 면 아직 안갔으니 
                    docking[find(num)] = true;
                    if(find(num)>1) {
                        union(find(find(num)-1), find(num));						
                    }
                    answer++;
                }else {
                    break;
                }
				// num이 0보다 크고 도킹이 false를 만날때까지 찾는다.
			}
			System.out.println(answer);
			
		}
		public static int find(int x) {
			if(x==parents[x]) {
				return x;
			}
			return parents[x]= find(parents[x]);
		}
		public static void union(int a, int b) {
			a = find(a);
			b= find(b);
			if(a!=b) {
				parents[b]=a;
			}
		}
	}
	
	
	
	
	
	
	
	
