import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//뱀
public class HyeKyoung {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<int[]> list = new ArrayList<>();
		Map<Integer, String> map = new HashMap<>();
		int n = sc.nextInt(), k = sc.nextInt(), dir = 0, second = 0;
		int nr = 1, nc = 1;
		int[][] a = new int[n+1][n+1];

		list.add(new int[] { nr, nc });
		int[] dr = { 0, 1, 0, -1 }; // 우, 하, 좌, 상(오른쪽으로 90도)
		int[] dc = { 1, 0, -1, 0 };

		for (int i = 0; i < k; i++) {
			int x = sc.nextInt(), y = sc.nextInt();
			a[x][y] = 2;
		}

		int l = sc.nextInt();
		for (int i = 0; i < l; i++) {
			int x = sc.nextInt();
			String s = sc.next();
			map.put(x, s);
		}

		while (true) {
			second++;
			nr += dr[dir];
			nc += dc[dir];
			list.add(new int[] { nr, nc });
			if (nr <= 0 || nr > n || nc <= 0 || nc > n)
				break;

			if (a[nr][nc] == 1)
				break;
			else if (a[nr][nc] == 0) {
				a[list.get(0)[0]][list.get(0)[1]] = 0; // 리스트에 저장된 행과열의 정보로 꼬리 삭제
				list.remove(0);
			}
			a[nr][nc] = 1;

			if (map.containsKey(second)) {
				if (map.get(second).equals("D"))
					dir++;
				else if (map.get(second).equals("L"))
					dir--;
				dir %= 4;
				if(dir<0) dir+=4;
			}
		}
		System.out.println(second);
	}

}
