import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class dongwoo {
	public static void main(String[] args) {
//		시작 2:25 끝 2:47
		Scanner sc = new Scanner(System.in);
		
		HashMap<Integer, Integer> bottomUp = new HashMap<Integer, Integer>();
		int n = sc.nextInt();
		int from = sc.nextInt();
		int to = sc.nextInt();
		int connections = sc.nextInt();
		for (int i = 0; i < connections; i++) {
			int p = sc.nextInt();
			int c = sc.nextInt();
			bottomUp.put(c, p);
		}
		ArrayList<Integer> pOfFrom = new ArrayList<Integer>();
		ArrayList<Integer> pOfTo = new ArrayList<Integer>();
		
		int now = from;
		pOfFrom.add(now);
		while (bottomUp.containsKey(now)) {
			pOfFrom.add(bottomUp.get(now));
			now = bottomUp.get(now);
		}
		now = to;
		pOfTo.add(now);
		while (bottomUp.containsKey(now)) {
			pOfTo.add(bottomUp.get(now));
			now = bottomUp.get(now);
		}
		boolean isFamily = false;
		for (int i:pOfTo) {
			Integer Wrapper = Integer.valueOf(i);
			if (pOfFrom.contains(Wrapper)) {
				System.out.println(pOfFrom.indexOf(Wrapper)+pOfTo.indexOf(Wrapper));
				isFamily = true;
				break;
			}
		}
		if (!isFamily) {
			System.out.println(-1);
		}
		
	}
}
