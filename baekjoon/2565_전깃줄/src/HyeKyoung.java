import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class HyeKyoung {

	static class Elec implements Comparable<Elec> {
		int s, e, cnt;
		List<Integer> cross = new ArrayList<>();

		public Elec(int s, int e) {
			super();
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Elec o) {
			return this.s - o.s;
		}
	}

	static Elec[] elecs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), res = 0;
		elecs = new Elec[n];
		int[] dp = new int[n];
		int max = 0;

		Arrays.fill(dp, 1);
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			elecs[i] = new Elec(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(elecs);

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (elecs[i].e < elecs[j].e) {
					dp[j] = Math.max(dp[j], dp[i] + 1);
					max = Math.max(max, dp[j]);
				}
			}
		}
		System.out.println(n - max);
	}

}

//5
//1 3
//3 1
//2 5
//4 6
//6 4
//답:2

//10
//1 6
//2 8
//3 2
//4 9
//5 5
//6 10
//7 4
//8 1
//9 7
//10 3