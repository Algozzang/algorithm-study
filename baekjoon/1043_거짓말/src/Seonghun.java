import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Seonghun {

	static int N; // 사람의 수
	static int M; // 파티의 수
	static long know; // 진실을 아는 사람들 (비트)
	static long[] party; // 파티들 (비트)
	static boolean[] exist; // 각 파티에 진실을 아는 사람 존재 여부

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// initialize
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		party = new long[M];
		exist = new boolean[M];
		
		st = new StringTokenizer(br.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		for (int i = 0; i < cnt; i++) {
			int n = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 번호
			know |= (long) 1 << n;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int n = Integer.parseInt(st.nextToken());
				party[i] |= (long) 1 << n;
			}
		}
		
		// solution
		for (int i = 0; i < M; i++) {
			if (!exist[i]) {
				// 현재 파티에 진실을 아는 사람이 한명이라도 있는 경우
				if ((know & party[i]) != 0) {
					know |= party[i];
					exist[i] = true;
					i = -1;
				}
			}
		}
		
		
		// result
		int answer = 0;
		for (int i = 0; i < M; i++) {
			if (!exist[i]) {
				answer++;
			}
		}
		System.out.println(answer);
	}

}