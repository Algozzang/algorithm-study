import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Seonghun {

	static int N; // 회의 수
	static int[][] meeting; // 회의 정보
	static int[] personnel; // 해당 인덱스까지의 회의를 선택했을 때의 최대 인원수 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// initialize
		N = Integer.parseInt(br.readLine());
		meeting = new int[N + 1][3];
		personnel = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				meeting[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 끝나는 시간 오름차순, 시작 시간 오름차순 정렬
		Arrays.sort(meeting, (o1, o2) -> o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0]);

		// 선택한 회의의 시작 시간과 가장 가깝게 끝나는 회의를 찾기
		for (int i = 1; i <= N; i++) {
			int idx = 0; // 가장 가깝게 끝나는 회의의 인덱스
			int begin = 1;
			int end = N;
			// 이분 탐색
			while (begin <= end) {
				int mid = (begin + end) / 2;
				// 찾은 회의가 선택한 회의보다 이전에 실행될 수 있는 경우
				if (meeting[mid][1] <= meeting[i][0]) {
					begin = mid + 1;
					idx = mid;
				} else {
					end = mid - 1;
				}
			}
			// personnel[i - 1] : 현재 회의를 선택하지 않으며, 이전 회의까지의 최대 인원수로 고려
			// meeting[i][2] + personnel[idx] : 현재 회의를 선택하며, 추가로 가장 가깝게 끝나는 회의의 최대 인원값을 합함
			personnel[i] = Math.max(personnel[i - 1], meeting[i][2] + personnel[idx]);
		}

		// result
		System.out.println(personnel[N]);
	}

}