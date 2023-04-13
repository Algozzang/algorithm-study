import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] distance = new long[N];
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[0] = 0;
		int[][] buses = new int[M][3];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			buses[i][0] = Integer.parseInt(st.nextToken()) - 1;
			buses[i][1] = Integer.parseInt(st.nextToken()) - 1;
			buses[i][2] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int[] bus = buses[j];
				int start = bus[0];
				int end = bus[1];
				int cost = bus[2];
				if (distance[start]!=Long.MAX_VALUE && distance[end] > distance[start] + cost) {
					distance[end] = distance[start] + cost;
					if (i==N-1) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		for (int i = 1; i < N; i++) {
			if (distance[i]==Long.MAX_VALUE) {
				sb.append(-1).append("\n");
			} else {
				sb.append(distance[i]).append("\n");				
			}
		}
		System.out.println(sb);
	}
}
