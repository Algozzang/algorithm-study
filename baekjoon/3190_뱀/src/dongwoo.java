import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class dongwoo {
	public static final int[][] directions = { 
			{ 1, 0 }, // 오른쪽 방향
			{ 0, 1 }, // 아래쪽 방향
			{ -1, 0 }, // 왼쪽 방향
			{ 0, -1 } // 위쪽 방향
	};
	public static int[][] jido;
	public static int dirIndex = 0;
	public static int[] nowDirection = directions[dirIndex];
	public static int[] head = {0,0};

	public static void main(String[] args) {
//		시작 : 19:21 중간에 멈춤 54:00
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 맵 사이즈 N
		jido = new int[N][N]; // 맵
		int K = sc.nextInt(); // 사과의 개수
		for (int i = 0; i < K; i++) { // 맵에 사과 표시
			int y = sc.nextInt();
			int x = sc.nextInt();
			jido[y-1][x-1] = -1;
		}
		int L = sc.nextInt();
		Map<Integer, String> turningPoint = new HashMap<>();
		for (int i = 0; i < L; i++) {
			int cho = sc.nextInt();
			String DorL = sc.next();
			turningPoint.put(cho, DorL);
		}
		
		// 게임 시작
		jido[head[1]][head[0]] = 1;
		for (int i = 1; i < 10100; i++) { // X가 10000이하, N이 100이하이므로
			if (!goForward()) {
				System.out.println(i);
				return;
			}
			if (turningPoint.containsKey(i)) {
				String directionStr = turningPoint.get(i);
				makeTurn(directionStr);
			}
		}

	}
	
	public static boolean goForward() {
		int N = jido[0].length;
		int newX = nowDirection[0]+head[0];
		int newY = nowDirection[1]+head[1];
		
		if (newX<0||newX>=N||newY<0||newY>=N||jido[newY][newX]>0) {	//맵 밖으로 나가거나 꼬리밟으면 리턴 
			return false;
		}

		int apgil = jido[newY][newX];		// 지도 위에 뱀의 몸체를 머리에서부터 남은 초수로 적어놓음 
		jido[newY][newX] = jido[head[1]][head[0]] +1;
		if (apgil==0) {
			for(int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (jido[i][j]>0) {
						jido[i][j]-=1;
					}
				}
			}
			
		}
		head[0] = newX;
		head[1] = newY;

		
		return true;
		
	}

	public static void makeTurn(String s) {
		// -1%4 일 경우를 대비해 4를 미리 더함
		dirIndex +=4;
		if (s.equals("D")) {
			dirIndex = (dirIndex + 1) % 4;
		} else if (s.equals("L")) {
			dirIndex = (dirIndex - 1) % 4;
		}
		nowDirection = directions[dirIndex];
	}
}
