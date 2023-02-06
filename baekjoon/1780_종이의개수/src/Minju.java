import java.util.Scanner;

public class Minju {
	static int[][] arr;
	static int[] result = new int[3];// -1, 0 ,1 종이의 개수

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int in = sc.nextInt();
				if(in == -1) in =2; // 배열때문에 -1입력을 2로 받음
				arr[i][j] = in;
			}
		}

		recur(0, 0, n);
		
		System.out.println(result[2]); // -1
		System.out.println(result[0]);
		System.out.println(result[1]);
		
	}

	static void recur(int x, int y, int n) {

		if(same(x,y,n)) {
			result[arr[x][y]]++; //현재 종이 증가
			return; // 모두 같으면 종이 1장으로 빠져나옴
		}
		
		//같지 않다면 분할 3등분씩
		int div3 = n/3;
		for(int i =x; i<x+n; i+=div3) {
			for(int j =y;j<y+n;j+=div3) {
				recur(i,j,div3);
			}
		}
		

	}
	
	static boolean same(int x, int y, int n) { //범위 내 모두 같은지 확인하는 함수
		int cur = arr[x][y];
		for (int i = x; i < x + n; i ++) {
			for (int j = y; j < y + n; j ++) {
				if (cur != arr[i][j]) return false; //현재 값이랑 다르면 false로 리턴
			}
		}
		return true; //범위 속 모든 값이 같을 때
	}

}
