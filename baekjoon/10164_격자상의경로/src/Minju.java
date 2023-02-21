import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Minju {

	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer> cnt = new ArrayList<>();
	static int num, n, m, k, circleX, circleY;
	static boolean circle, originCircle;
	static boolean[][] arr;
	// 오른쪽, 아래 방향전환
	static int[] dx = { 0, 1 };
	static int[] dy = { 1, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		num = 0; // 출력값
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		 // 원 마킹한 배열 체크 숫자는 1부터시작하니까 k-1
		circleX = (k-1) / m;
		circleY = (k-1) % m;
	
		arr = new boolean[n][m];
		if (k != 0) {
			arr[circleX][circleY] = true; // 원 배열 마킹
			originCircle = true; //원이 있으면 저장
		}
		recur(0, 0); // 0,0에서 출발
		System.out.println(num);
	}

	private static void recur(int i, int j) {

		// 원보다 왼쪽 아래에 있거나 오른쪽 위에 있으면 가지못하는 경로임
		if( originCircle && (i>circleX && j <circleY)|| (j >circleY && i<circleX)) return;
		
		for (int s = 0; s < 2; s++) {// 상하좌우 있으면 재귀
		
			int newI = i + dx[s];
			int newJ = j + dy[s];
			if (newI >= 0 && newJ >= 0 && newI < n && newJ < m) { // 배열 범위 내
				if(arr[newI][newJ]) circle = true; // 원만나면 
				recur(newI, newJ);
				if(arr[newI][newJ]) circle = false; // 원상복구
			}
			if(newI == n && newJ ==m-1 ||newI == n-1 && newJ == m){ //NXM 칸 넘어가면
				if(circle == originCircle) {// true면 끝까지 안감
					num++; // 갈 수 있는 경로다
				}
				return;
			}
			newI = i - dx[s];
			newJ = j - dy[s];
		}

	}
}