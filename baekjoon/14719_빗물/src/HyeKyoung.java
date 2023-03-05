import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//빗물
public class HyeKyoung {
	static class Height implements Comparable<Height>{
		int h, idx;

		public Height(int h, int idx) {
			super();
			this.h = h;
			this.idx = idx;
		}
		@Override
		public int compareTo(Height o) {
			return o.h - this.h;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[W];
		int sum = 0;
		int[] blocks = new int[W];
		Height[] block = new Height[W];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++) {
			int h = Integer.parseInt(st.nextToken());
			block[i] = new Height(h, i);
			blocks[i] = h;
		}
		Arrays.sort(block);

		for(int i=1; i<W; i++) {
			int left, right;
			int height = block[i].h;
			if(block[i-1].idx < block[i].idx) {
				left = block[i-1].idx;
				right = block[i].idx;
			}
			else {
				right = block[i-1].idx;
				left = block[i].idx;
			}
			
			visited[left] = true;
			visited[right] = true;
			
			for(int j = left + 1; j<right; j++) {
				if(visited[j]) continue;
				sum+=height-blocks[j];
				visited[j] = true;
			}
		}
		System.out.println(sum);
	}
}