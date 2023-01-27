
import java.util.Stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Minju { // 1874
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //수열 길이
        int n = Integer.parseInt(br.readLine());
        //stack
        Stack<Integer> stack = new Stack<>();

        int cur = 0;

        OUTER:
        while (n-- > 0) {
            // 숫자 입력받기
            int num = Integer.parseInt(br.readLine());
            // 다음 숫자 입력받기전까지 pop 시키기
            while (true) {
                if (cur < num) { // 현재 오름차순으로 넣는 수가 num보다 작으면 ++
                    // push
                    cur++;
                    stack.add(cur);
                    sb.append("+").append("\n");
                }
                if(stack.peek() == num) {
                    int temp = stack.pop();
                    sb.append("-").append("\n");
                    break;
                }else {
                    // NO 출력 조건
                    if (num < cur) { //현재 오름차순 카운트값이 입력받은 값보다 큼 
                        sb.setLength(0);
                        sb.append("NO");
                        break OUTER;
                    }
                }

            }

        }
        System.out.println(sb);
    }
}
