import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Minju {

	/*
	 * 이차원배열로 생각했을 때 배열 맨앞줄에 0넣을 공간을 생성[alen+1][blen+1]
	 * 값 0행 1행 .. 행 탐색 먼저 해야 함
	 * 마지막 배열의 값이 LCS 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String a = br.readLine();
		String b = br.readLine();

		int aLen = a.length(); // col
		int bLen = b.length(); // row
		int[][] arr = new int[bLen + 1][aLen + 1];

		// 초기값 세팅
		for (int i = 0; i <= aLen; i++) {
			for (int j = 0; j <= bLen; j++) {
				if (i == 0 || j == 0)
					arr[j][i] = 0;
			}
		}

		for (int i = 1; i <= aLen; i++) {
			for (int j = 1; j <= bLen; j++) {
				
				if (a.charAt(i-1) == b.charAt(j-1)) { // 현재값이 같은 문자열이면
					arr[j][i] = arr[j - 1][i-1] + 1; //왼쪽 대각선위 값 +1
				} else {
					arr[j][i] = Math.max(arr[j][i-1] , arr[j-1][i]); //왼쪽 값과 위쪽 값 중 max
				}
			}
		}

		System.out.println(arr[bLen][aLen]);

	}

}

/*출력문
for (int i = 0; i <= bLen; i++) {
	for (int j = 0; j <= aLen; j++) {
		System.out.print(arr[i][j] + " ");
	}
	System.out.println();
}
*/
