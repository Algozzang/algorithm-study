import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Minju { // 3190 뱀

	static int n, k, l;
	static int[][] arr; //벽인지:-1 사과있는지:2 저장하는 배열 
	static ArrayList<int[]> snake;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine()); // 보드 n*n
		arr = new int[n + 2][n + 2]; // 한바퀴 더 두른 보드
		
		for (int i = 0; i < n + 2; i++) {
			for (int j = 0; j < n + 2; j++) {
				if (i == 0 || j == 0 || i == n + 1 || j == n + 1)
					arr[i][j] = -1; // 벽: -1
			}
		}
		// 사과개수
		k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			String[] apple = br.readLine().split(" ");
			int x = Integer.parseInt(apple[0]);
			int y = Integer.parseInt(apple[1]);
			arr[x][y] = 2; // 사과 저장: 2
			//밑에 줄은 저장이 안됨 왤까?
			//arr[Integer.parseInt(apple[0])][Integer.parseInt(apple[0])] = 2; // 사과 저장: 2
		}
		
		// 방향전환
		l = Integer.parseInt(br.readLine());
		HashMap<Integer, Integer> map = new HashMap<>(); // 몇초후 전환, 1:right 0: left
		int[] turnRight = new int[l];
		for (int i = 0; i < l; i++) {
			String[] turn = br.readLine().split(" ");
			int right = (turn[1].equals("D")) ? 1 : 0;
			map.put(Integer.parseInt(turn[0]), right);
		}
	
		int end = 0;
		snake = new ArrayList<>();
		int direction = 0; // 우 0 , 하 1,좌 2,상 3 방향으로 이동 중
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};
		int x = 1, y = 1;
		snake.add(new int[]{x,y}); //뱀 시작 지점 추가
		
		for (int i = 1; i < 10000; i++) {
			//갈 방향 정하기
			x = x + dx[direction];
			y = y + dy[direction];
			//System.out.println(x+ " : "+y);
			if(isBreak(x,y)) { 
				end = i;
				//System.out.println("break:"+ x+ " : "+y);
				break;
			} else if (arr[x][y] == 2) { // 사과있으면
				snake.add(new int[] {x,y}); //뱀 확장
				arr[x][y] = 0; //사과 제거
			} else {
				snake.remove(0); // 맨 앞 꼬리칸 비우기
				snake.add(new int[] {x,y});
			}

			if (map.containsKey(i)) { // 방향전환
				if (map.get(i) == 1) { // "D"
					if (direction == 3)
						direction = 0;
					else
						direction++;
				} else { // "L"
					if (direction == 0)
						direction = 3;
					else
						direction--;
				}
			}
		}

		System.out.println(end);
	}

	public static boolean isBreak(int x, int y) { //종료조건함수

		if (arr[x][y] == -1) { // 벽이거나
			return true;
		}
		for(int i =0;i<snake.size();i++) { //뱀이거나
			if(x == snake.get(i)[0] && y == snake.get(i)[1]) return true;
		}
		
		return false;
	}
	
}
