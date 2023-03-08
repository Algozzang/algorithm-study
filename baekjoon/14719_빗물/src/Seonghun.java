import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Seonghun {

	static int H, W; // 세로 길이, 가로 길이
	static int[] block; // 블록 높이 정보
	static int answer; // 고이는 빗물의 총량

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		block = new int[W];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			block[i] = Integer.parseInt(st.nextToken());
		}

		// solution
		for (int h = H; h >= 1; h--) {
			// h이상의 높이로 쌓인 블록 위치 리스트
			List<Integer> idx = new ArrayList<>();
			for (int i = 0; i < W; i++) {
				if (block[i] >= h) {
					idx.add(i);
				}
			}
			// 빗물량 계산
			for (int i = 0; i < idx.size() - 1; i++) {
				answer += (idx.get(i + 1) - idx.get(i) - 1);
			}
		}

		// solution
		System.out.println(answer);
	}
}