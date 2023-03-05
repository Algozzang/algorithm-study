import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HyeKyoung {
	static int[] parents;
	
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
//		if(aRoot == bRoot) return;
		
		parents[bRoot] = aRoot;
	}
	static int findSet(int a) {
		if(a == parents[a]) return a;
		
		return parents[a] = findSet(parents[a]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine()), P = Integer.parseInt(br.readLine());
		int Gi, cnt = 0;
		boolean possible = true;
		parents = new int[G+1];
		for(int i=1; i<=G; i++) parents[i] = i;
		
		for(int i=0; i<P; i++) {
			Gi = Integer.parseInt(br.readLine());
			if(!possible) continue;
			
			int groot = findSet(Gi);
			if(groot == 0) {
				possible = false;
				continue;
			}
			union(groot - 1, Gi);
			cnt++;
		}
		System.out.println(cnt);
	}

}
