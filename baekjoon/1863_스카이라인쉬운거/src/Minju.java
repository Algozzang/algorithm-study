import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Minju {

	static int N;
	static Stack<Integer> stack = new Stack<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int cnt = 0;
		int cur = 0;
		N = Integer.parseInt(br.readLine());
		for(int i =0;i<N;i++) {
			// Y만보자 
			// 고도 높아지면 스택에 넣고, 들어올값보다 peek이 크면 작아질때까지 pop, pop개수와 그리고 남은 스택사이즈를 더해준다
			st=new StringTokenizer(br.readLine());
			st.nextToken();
			cur = Integer.parseInt(st.nextToken());
		
			while(!stack.isEmpty() && stack.peek() > cur) {
				stack.pop();
				cnt++;
			}
			if(!stack.isEmpty() && stack.peek() < cur) {
				stack.push(cur);
			} 
			if(stack.isEmpty()){
				if(cur==0) continue;
				stack.push(cur);
			}
		}
		System.out.println(cnt+stack.size());
	}
}

/*
4
1 4
2 3
3 5
4 4
답4

6
1 1
2 2
5 3
6 1
7 3
8 2
답5
 */
