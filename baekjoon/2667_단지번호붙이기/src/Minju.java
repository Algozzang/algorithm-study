import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;


public class Minju {

	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer> cnt = new ArrayList<>();
	static int num, n;
	static boolean[][] arr;
	static boolean[][] visited;
	// 상하좌우 방향전환
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		num = 0;
		n = Integer.parseInt(br.readLine());
		arr = new boolean[n][n];
		visited = new boolean[n][n];
		/* array input */
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < n; j++) {
				arr[i][j] = str[j].equals("0") ? false : true; // 집 있는지 없는지 bool값으로 입력받기
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				num=0;
				if (visited[i][j]) continue; // 방문한 배열이면 이미 단지 수 세기함
				if (arr[i][j]) { // 집이 있으면 탐색
					recur(i, j);
					cnt.add(num); // 탐색끝난 집수 리스트에 추가
				}
				
			}
		}


		/* asc sort */
		cnt.sort(Comparator.naturalOrder());
		/* output */
		sb.append(cnt.size() + "\n"); // 총 단지수 출력
		for (int x : cnt) {
			sb.append(x+ "\n"); // 각 단지내 집의 수
		}
		System.out.println(sb);
	}

	private static void recur(int i, int j) {
		
		visited[i][j] = true; // 현재 배열 방문체크
		num++; // 집 수 증가
		for (int s = 0; s < 4; s++) {// 상하좌우 있으면 재귀
			int newI = i + dx[s];
			int newJ = j + dy[s];
			if (newI >= 0 && newJ >= 0 && newI < n && newJ < n) { // 배열 범위 내
				if (!visited[newI][newJ]) { // 아직 방문안했고
					if(arr[newI][newJ]) recur(newI, newJ); // 집이 있는 배열원소라면 계속 탐색
					else visited[newI][newJ] = true; //집 없으면 방문체크만 해주기
				}
			}
			
		}
	}

}
