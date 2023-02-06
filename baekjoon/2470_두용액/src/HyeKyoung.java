import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//메모리: 31696kb, 시간: 276ms
//http://boj.kr/2be65d0e09e84e9383351eaae63832dc
public class HyeKyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] sol = new int[n];
		int s = 0, e = n - 1, a = 0, b = 0;
		int sum, min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++)
			sol[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(sol);

		while (s < e) {
			sum = sol[s] + sol[e];

			if (Math.abs(sum) < min) {
				min = Math.abs(sum);
				a = s;
				b = e;
			}

			if (sum < 0)
				s++;
			else if (sum > 0)
				e--;
			else
				break;
		}
		System.out.println(sol[a] + " " + sol[b]);
	}
}
