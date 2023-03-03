import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Seonghun {

	static int G; // 게이트의 수
	static int P; // 비행기의 수
	static int[] docking; // 게이트 도킹 위치 저장 (greedy)
	static int answer; // 도킹시킬 수 있는 최대 비행기 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// initialize
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		docking = new int[G + 1];
		for (int i = 1; i <= G; i++) {
			docking[i] = i; // 도킹 가능 위치 세팅
		}

		// solution
		for (int i = 0; i < P; i++) {
			int g = Integer.parseInt(br.readLine());
			int gate = findGate(g);
			if (gate != 0) { // 도킹 가능한 위치가 있는 경우
				docking[gate] = docking[gate - 1];
				answer++;
			} else { // 도킹 가능한 위치가 없는 경우
				break;
			}
		}

		// result
		System.out.println(answer);
	}

	// 도킹하기 가장 적합한 위치 반환 (반환하면서 도킹 위치 업데이트)
	static int findGate(int g) {
		return g == docking[g] ? g : (docking[g] = findGate(docking[g]));
	}

}
