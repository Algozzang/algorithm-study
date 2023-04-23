
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Minju {
	// 앞 숫자가 커야되니까 스택으로 비교하면서 넣기
	// 모든 숫자 같을때 비교 해줄필요없음 
	public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
     
        Stack<Integer> stack = new Stack<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        int curK = k;
        String num = br.readLine();
        int removeCnt = 0;
        while(removeCnt<k) {
        	int isAllSame = 1;
	        for(int i =0;i<num.length();i++){
	            int cur = num.charAt(i)-'0';
	            while(!stack.isEmpty() && stack.peek() < cur && removeCnt <k) { // 계속 지워줘야함 while!!!
	            	stack.pop();
	            	removeCnt++;
	            	curK--;
	            }
	            if(!stack.isEmpty() && cur == stack.peek()) {
	            	isAllSame++;
	            }
	            stack.add(cur);
	        }
	        if(!stack.isEmpty() && removeCnt<k) {
	        	int last = stack.pop();
	        	if(last < stack.peek()) {
	        		curK--;
	        		removeCnt++;
	        	}else {
	        		stack.add(last);
	        	}
	        }
	  
	        if(num.length() == isAllSame) { // 다 같은 숫자면
	        	while(curK-->0) {
	        		stack.pop();
	        		removeCnt++;
	        	}
	        }
	        
	        sb.setLength(0);
	        while(!stack.isEmpty()) {
	        	sb.append(stack.pop());
	        }
	        num = sb.reverse().toString();
        }

        System.out.println(sb.toString());
    }

}

