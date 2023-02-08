import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dongwoo {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());

		int diff[] = new int[N];

		int changeN = 0;
		for (int i = 0; i < N; i++) {
			diff[i] = Integer.parseInt(st2.nextToken()) - Integer.parseInt(st.nextToken());
			changeN += Math.abs(diff[i]);
		}

		for (int i = 0; i < N - 1; i++) {
			if (diff[i] < 0 && diff[i + 1] < 0) {
				changeN += Math.max(diff[i], diff[i + 1]);
			}
			if (diff[i] > 0 && diff[i + 1] > 0) {
				changeN -= Math.min(diff[i], diff[i + 1]);
			}
		}

		System.out.println(changeN);

	}

}
