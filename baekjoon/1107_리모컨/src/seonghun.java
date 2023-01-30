import java.util.Scanner;

public class seonghun {

	static int N; // 목표 채널
	static int M; // 고장난 버튼의 개수
	static boolean[] broken; // 고장난 버튼
	static int answer;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// initialize
		N = input.nextInt();
		M = input.nextInt();
		broken = new boolean[10];
		answer = Math.abs(100 - N);
		for (int i = 0; i < M; i++) {
			broken[input.nextInt()] = true;
		}
		
		input.close();

		// solution
		clickButton(0, new String());

		// result
		System.out.println(answer);
	}

	private static void clickButton(int cnt, String channel) {
		// 현재까지 누른 채널에 대한 비교
		if (channel.length() > 0) {
			answer = Math.min(answer, cnt + Math.abs(Integer.parseInt(channel) - N));
		}
		// 고장나지 않은 버튼 하나씩 누르기
		for (int i = 0; i < 10; i++) {
			if (!broken[i]) {
				String nextChannel = channel + i;
				if (nextChannel.length() <= 6) {
					clickButton(cnt + 1, nextChannel);
				}
			}
		}
	}

}
