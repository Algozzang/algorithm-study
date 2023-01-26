import java.io.*;
import java.util.Stack;

public class 김혜경 {
    public static void main(String args[]) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int n=Integer.parseInt(br.readLine()), index=1;
        Stack<Integer> stack=new Stack<>();
        boolean isTrue=true;
        
        OUTER:for(int i=0; i<n; i++){
            int s=Integer.parseInt(br.readLine());
            while(true){
                if(s>=index){
                    stack.push(index++);
                    sb.append("+\n");
                }
                else{
                    if(stack.peek()!=s){
                        isTrue=false;
                        break OUTER;
                    }
                    else{
                        stack.pop();
                        sb.append("-\n");
                        break;
                    }
                }
            }
        }
        System.out.print((isTrue) ? sb : "NO");
    }
}