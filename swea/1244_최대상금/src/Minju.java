import java.io.*;

public class Minju { // 1244 최대상금

	static int max; // 최대 금액
	static int depth; // 교환횟수
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			max = 0;
			String[] sArr = br.readLine().split(" "); // 숫자판
			String s = sArr[0];
			depth = Integer.parseInt(sArr[1]);

			// 시간초과: 횟수 최대는 길이로
			if (depth > s.length()) {
				depth = s.length();
			}
			int[] arr = new int[s.length()];
			for (int i = 0; i < s.length(); i++) { // 배열 저장
				arr[i] = s.charAt(i) - '0';
			}
		
			getMax(arr, 0, 0);
			sb.append("#").append(tc).append(" ").append(max).append("\n");
			
		}
		System.out.println(sb);
	}

	private static void getMax(int[] arr, int cur, int idx) {
		if (cur == depth) { // dfs 종료조건 : 교환횟수 종료
			int price = getSum(arr);
			max = Math.max(max, price);
			return;
		}
		// dfs
		for (int i = idx; i < arr.length - 1;i++) {
			for (int j = i + 1; j < arr.length; j++) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				getMax(arr, cur+1, idx);
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;

			}

		}

	}

	private static int getSum(int[] arr) { 
		int sum = 0;
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			sum += arr[i] * Math.pow(10, len - i - 1);
		}
		return sum;
	}

}
