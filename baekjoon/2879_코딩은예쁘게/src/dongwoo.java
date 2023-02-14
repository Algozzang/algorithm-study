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
		
		// 각 자리별로 변해야 하는 횟수를 저장할 diff 배열
		int diff[] = new int[N];

		// changeN에 일단 절댓값 다 더해 놓기
		int changeN = 0;
		for (int i = 0; i < N; i++) {
			diff[i] = Integer.parseInt(st2.nextToken()) - Integer.parseInt(st.nextToken());
			changeN += Math.abs(diff[i]);
		}
		
		// 바로 다음녀석과 부호가 같으면 절약할 수 있는 횟수만큼 changeN 에서 빼주기
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
