import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Hyekyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()), k, max, input, result = 0;
		int[] time = new int[N + 1];
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			max = 0;
			st = new StringTokenizer(br.readLine());
			input = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			if (i == 1) {
				time[i] = input;
				continue;
			}
			// 먼저 해야되는 애들 중에 제일 늦게끝난거 + 내 작업시간
			for (int j = 0; j < k; j++) max = Math.max(max, time[Integer.parseInt(st.nextToken())]);
			time[i] = max + input;
			result = Math.max(result, time[i]);
		}
		System.out.println(time[N]);
	}

}
