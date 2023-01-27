import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class dongwoo {
	public static void main(String[] args) {
		// 소요시간 45분
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		Stack<Integer> stack = new Stack<>();
		int ind = 0;			// original에서 현재 보고있는 인덱스 값을 나타냄
		int partial_max = 1;	// 지금까지 탐색한 값중 가장 큰 값, 이 값을 기준으로 original을 더 탐색할지, 아니면 stack에서 꺼낼지를 결정 
		int original[] = new int[N+1];		// 1~N까지의 수열
		ArrayList<Integer> result = new ArrayList<>();				// +는 1 -는 0
		
		for(int i=0; i<=N;i++) {
			original[i] = i+1;
		}
		
		for(int i=0; i<N;i++) {
			int target = sc.nextInt();
			while (partial_max<=target) {
				stack.push(original[ind]);
				result.add(1);
				ind++;
				partial_max = original[ind];
			}
			if (partial_max>=target) {
				int now = stack.pop();
				if (now!=target) {
					result.set(0, -1);
					break;
				}
				result.add(0);
			}
		}
		for (int i=0; i<result.size(); i++) {
			int pushOrPop = result.get(i);
			if (pushOrPop==-1) {
				System.out.println("NO");
				break;
			};
			if (pushOrPop==0) {
				System.out.println("-");
			}
			if (pushOrPop==1) {
				System.out.println("+");
			}
		} 
		
	}
}
