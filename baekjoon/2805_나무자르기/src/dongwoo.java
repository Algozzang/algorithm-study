import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] heights = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(heights);	// 높이를 오름차순으로 정렬한 후 뒤에서부터 탐색  
		int sum = 0;			// 현재 얻은 나무 길이 합  
		int trees = 0;			// 현재 높이에서 베는 나무의 수 
		int nowHeight = heights[N];
		while (true) {
			trees++;
			while((N - trees)>0 && heights[N - trees+1]==heights[N - trees]) {	// 같은 높이의 나무 고려 
				trees++;
			}
			long diff = heights[N - trees + 1] - heights[N - trees];
			if (diff * trees >= M - sum) {		// diff * trees가 20억을 넘을 수도 있으므로 diff를 long형으로 선언 
				nowHeight -= Math.ceil((M - sum) / (float) trees);
				break;
			} else {
				sum += diff * trees;
				nowHeight = heights[N - trees];
			}
		}
		System.out.println(nowHeight);

	}

}