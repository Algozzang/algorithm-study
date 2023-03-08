import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class dongwoo {

	public static void main(String[] args) throws IOException {
		// G가 10만이므로 O(N^2)은 시간초과 나는 문제
		// 그럼 어떻게? 트리 사용해보자
		// 노드 저장 값 : 범위 (start, end), 자식들 중에서 몇 곳이 차있는지
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		int[] flights = new int[P];
		for (int i = 0; i < P; i++) {
			flights[i] = Integer.parseInt(br.readLine());
		}
		int[] gates = new int[G + 1];
		int[] union = new int[G + 1];
		for (int i = 1; i < G + 1; i++) {
			union[i] = i;
		}
		for (int i = 0; i < P; i++) {
			int gate = flights[i];
			if (!docking(gate, gates, union)) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(P);
	}

	private static boolean docking(int gate, int[] gates, int[] union) {
		while (gate > 0) {
			if (gates[gate] == 0) {
				gates[gate] = 1;
				union(gate - 1, gate, union);
				return true;
			}
			gate = find(gate, union);
		}
		return false;
	}

	private static void union(int a, int b, int[] union) {
		a = find(a, union);
		b = find(b, union);
		union[b] = a;
	}

	private static int find(int n, int[] union) {
		if (union[n] == n)
			return n;
		return union[n] = find(union[n], union);
	}

}