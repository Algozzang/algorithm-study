import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//촌수계산
//메모리 13024kb, 시간 120ms
public class HyeKyoung {
	static Map<Integer, Integer> map = new HashMap<>();
	static List<Integer> listA = new ArrayList<>();
	static List<Integer> listB = new ArrayList<>();
	static int a, b;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		a = sc.nextInt();
		b = sc.nextInt();
		int m = sc.nextInt(), x, y, result = -1;

		for (int i = 0; i < m; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			map.put(y, x); // 부모를 value값으로 넣음
		}
		if (findParents(a, a) == findParents(b, b)) {
			for (int i = 0; i < Math.min(listA.size(), listB.size()); i++) {
				if (listA.get(listA.size() - 1 - i) != listB.get(listB.size() - 1 - i)) {
					result = listA.size() + listB.size() - 2 * i;
					break;
				} else if (i == Math.min(listA.size(), listB.size()) - 1) {	//for문 마지막
					result = listA.size() + listB.size() - 2 * (i + 1);
				}
			}
		}
		System.out.println(result);
	}

	static int findParents(int key, int num) {
		List<Integer> list = (num == a) ? listA : listB;
		list.add(key);

		if (!map.containsKey(key))
			return key; // 키가 없으면 본인이 제일 조상

		int value = map.get(key);

		if (map.containsKey(value)) { // 부모가 있는지 체크
			return findParents(value, num);
		} else { // 없으면 제일 조상임
			list.add(value);
			return value;
		}

	}
}