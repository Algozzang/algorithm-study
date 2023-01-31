import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dongwoo {
	public static List<Integer> alive = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)); // 살이있는 버튼

	public static void main(String[] args) {
		// 시작 : 09:47 		끝 : 다음날 아침 10:36
		Scanner sc = new Scanner(System.in);
		int target = sc.nextInt();
		int brokenNo = sc.nextInt();
		int[] brokens = new int[brokenNo];
		for (int i = 0; i < brokenNo; i++) {
			brokens[i] = sc.nextInt();
		}

		// 채널 버튼 만 누른 경우
		int onlyChannel = Math.max(target - 100, 100 - target);
		int notOnlyChannel = onlyChannel; // 숫자버튼을 누를경우 (두 숫자중 min값이 결과이므로 onlyChannel로 초기화)

		if (brokenNo == 0) {
			notOnlyChannel = String.valueOf(target).length(); // 고장난 버튼이 없는경우, 그냥 숫자패드 누르고 끝
		} else if (brokenNo != 10) {// 고장난 버튼이 있는 경우, 다 고장난 경우는 의미 없으므로 제외

			for (int j = 0; j < brokenNo; j++) {
				for (int i = 0; i < 10; i++) {
					if (brokens[j] == i) {
						alive.remove(Integer.valueOf(i)); // alive안에 있는 고장난 숫자 버튼을 제거
					}
				}
			}
			// 자릿수가 다른경우 ex) 999 인데 버튼이 1, 0 만 살아있으면 자릿수가 올라감
			int digits = String.valueOf(target).length();
			int[] longerSelection = new int[digits + 2];
			if (alive.get(0) == 0 && alive.size() > 1) {
				longerSelection[1] += 1; // 0 버튼이 살아있으면 0000으로 초기화 되는거 방지
			}
			int longerDiff = Math.max(selectionToNumber(longerSelection) - target,
					target - selectionToNumber(longerSelection));
			notOnlyChannel = Math.min(notOnlyChannel, longerDiff + longerSelection.length - 1);
			if (digits > 1) {
				int[] shorterSelection = new int[digits];
				for (int i = 1; i < digits; i++) {
					shorterSelection[i] = alive.size() - 1;
				}
				int shorterDiff = Math.max(selectionToNumber(shorterSelection) - target,
						target - selectionToNumber(shorterSelection));
				notOnlyChannel = Math.min(notOnlyChannel, shorterDiff + shorterSelection.length - 1);

			}
			// 누른 숫자 버튼을 (alive에 저장된 index기준 )순서대로 담고있는 배열 []
			int[] selection = new int[digits + 1]; // 맨 앞자리를 탐색 완료 여부 판단용으로 쓰기 위해 길이를 길게 선언
			while (true) {
				int hubo = selectionToNumber(selection);
				int diff = Math.max(target - hubo, hubo - target);
				notOnlyChannel = Math.min(notOnlyChannel, diff + selection.length - 1);
				if (!getNextSelection(alive.size(), selection)) {
					break;
				}
			}

		}

		System.out.println(Math.min(notOnlyChannel, onlyChannel)); // 채널 증감만 한경우와, 숫자를 누른 경우 중 적은것

	}

	public static int selectionToNumber(int[] selection) {
		int SIZE = selection.length;
		int result = 0;
		for (int i = SIZE - 1; i > 0; i--) {
			result += alive.get(selection[i]) * (int) Math.pow(10, SIZE - 1 - i);
		}
		return result;
	}

	public static boolean getNextSelection(int n, int[] selection) { // alive의 길이 기준으로 n진법 계산.
		int SIZE = selection.length;
		selection[SIZE - 1] += 1;
		for (int i = SIZE - 1; i > 0; i--) {
			if (selection[i] >= n) {
				selection[i] = 0;
				selection[i - 1] += 1;
			}
		}

		if (selection[0] == 1)
			return false;
		return true;
	}

}