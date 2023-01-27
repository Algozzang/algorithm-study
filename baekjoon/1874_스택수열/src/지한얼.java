import java.util.*;

import java.io.*;
//속도가 2배 이상차이 ㄷㄷ 
public class 지한얼{
//	public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
    	BufferedReader bf1 = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.valueOf(bf1.readLine());
    	int data[] = new int[n];
    	
    	for(int i=0; i<n;i++) {
    		data[i] =Integer.valueOf(bf1.readLine());
    	}
    	
    	Stack<Integer> stack = new Stack<>();
    	StringBuffer bf = new StringBuffer();
    	
    	int asc =1;
    	
    	for (int i=0; i<n;i++) { //n번까지
    		
    		if(data[i]>=asc) {
    			
    			while(data[i]>=asc) {
    				stack.push(asc++);
    				bf.append("+\n");
    			}
    			stack.pop();
    			bf.append("-\n");
    		}else {
    			if(stack.peek()!=data[i]) {
    				System.out.println("NO");
    				return;
    			}
    			else {
                    stack.pop();
    				bf.append("-\n");
    			}
    			
    			
    		}
    	}
    	System.out.println(bf.toString());
   }
}
