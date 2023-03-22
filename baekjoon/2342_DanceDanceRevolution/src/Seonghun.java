import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Seonghun {

	static List<Integer> commands = new ArrayList<>();; // 입력한 명령어
	static int[][][] minPower; // 현재 밝고 있는 발판과 수행한 명령어에 대한 최소의 힘을 저장
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// initialize
		int input;
		while ((input = Integer.parseInt(st.nextToken())) != 0) {
			commands.add(input);
		}
		minPower = new int[5][5][commands.size()];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Arrays.fill(minPower[i][j], Integer.MAX_VALUE);
			}
		}

		// solution
		minPower[0][commands.get(0)][0] = 2; // 첫번째 명령어 수행
		for (int n = 1; n < commands.size(); n++) {
			int cmd = commands.get(n);
			for (int left = 0; left < 4; left++) {
				for (int right = left + 1; right < 5; right++) { // 오른발 발판이 왼발 발판보다 항상 크도록 설정
					int power = minPower[left][right][n - 1];
					if (power != Integer.MAX_VALUE) { // 계산된 값이 있으면
						// 왼발을 옮기자
						if (cmd < right) {
							minPower[cmd][right][n] = Math.min(minPower[cmd][right][n], power + calPower(left, cmd));
						} else {
							minPower[right][cmd][n] = Math.min(minPower[right][cmd][n], power + calPower(left, cmd));
						}
						// 오른발을 옮기자
						if (cmd < left) {
							minPower[cmd][left][n] = Math.min(minPower[cmd][left][n], power + calPower(right, cmd));
						} else {
							minPower[left][cmd][n] = Math.min(minPower[left][cmd][n], power + calPower(right, cmd));
						}
					}
				}
			}
		}

		// result
		for (int left = 0; left < 4; left++) {
			for (int right = left + 1; right < 5; right++) {
				answer = Math.min(answer, minPower[left][right][commands.size() - 1]);
			}
		}
		System.out.println(answer);
	}

	static int calPower(int before, int after) {
		return before == after ? 1 : before == 0 ? 2 : Math.abs(before - after) % 2 == 1 ? 3 : 4;
	}
}
