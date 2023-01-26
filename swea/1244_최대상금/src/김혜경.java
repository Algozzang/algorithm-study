import java.util.Scanner;

public class 김혜경 {
    static int n;
    static char[] ch;
    static int result=0;
    
    static void change(int idx, int cnt){
        if(cnt==n){
            result=Math.max(result, Integer.parseInt(String.valueOf(ch)));
            return;
        }
        
        for(int i=idx; i<ch.length-1; i++){
            for(int j=i+1; j<ch.length; j++){
                swap(i, j);
                change(i, cnt+1);
                swap(i, j);
            }
        }
    }
    
    static void swap(int i, int j){
        char tmp=ch[i];
        ch[i]=ch[j];
        ch[j]=tmp;
    }
    
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for(int test_case=1; test_case<=T; test_case++){
            result=0;
            String s=sc.next();
            ch=new char[s.length()];
            ch=s.toCharArray();
            n=sc.nextInt();
            change(0, 0);
            
            System.out.printf("#%d %d\n", test_case, result);
        }
    }
}
