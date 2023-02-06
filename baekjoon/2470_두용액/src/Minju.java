import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Minju {

	public static void main(String[] args) throws IOException {
		// 그냥 풀었다가 시간초과나서 투포인터로
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] num = new int[n];
		String[] stoken = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(stoken[i]);
		}
		// pointer 초기위치 init
		int start = 0;
		int end = n - 1;

		int min = Integer.MAX_VALUE; // 0에 가장 가까운 값
		int a = 0, b = 0;

		// sort
		Arrays.sort(num);

		while (start < end) {
			if (num[start] + num[end] == 0) { // 합이 0인 최적의경우
				a = num[start];
				b = num[end];
				break;
			} else {
				if (min > Math.abs(num[start] + num[end])) { // 0과의 차이가 가장 적은 값 저장 & 두 수 저장
					min = Math.abs(num[start] + num[end]);
					a = num[start];
					b = num[end];
				}
				// 포인터 이동 
				if (num[start] + num[end] > 0) end--; // end 왼쪽으로
				else start++; // start 포인터 오른쪽으로
			}
		}

		System.out.println(a + " " + b);

	}
}