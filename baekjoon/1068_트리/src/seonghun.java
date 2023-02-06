import java.io.IOException;
import java.util.Scanner;

public class seonghun {

	static int N; // 노드의 개수
	static int M; // 지울 노드 번호
	static int[] parent; // 각 노드의 부모 노드
	static int[] child; // 각 노드의 자식 노드 수
	static int answer;
	
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		// initialize
		N = input.nextInt();
		parent = new int[N];
		child = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = input.nextInt();
			if (parent[i] != -1) {
				child[parent[i]]++;
			}
		}
		M = input.nextInt();
		input.close();
		
		// 제거된 노드의 부모는 자식 수 감소
		if (parent[M] != -1) {
			child[parent[M]]--;
		}

		// 노드가 제거되지 않고 자식 노드 수가 0인 경우 카운팅
		for (int i = 0; i < N; i++) {
			if (!isRemoved(i) && child[i] == 0) {
				answer++;
			}
		}
		
		// result
		System.out.println(answer);
	}

	private static boolean isRemoved(int n) {
		return n == M ? true : parent[n] == -1 ? false : isRemoved(parent[n]);
	}
	
}
