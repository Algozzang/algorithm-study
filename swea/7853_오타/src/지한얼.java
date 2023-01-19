import java.util.Scanner;

public class 지한얼 {
	static String str ;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t=sc.nextInt();
		for(int test=1;test<=t;test++) {
			long answer=1;
			str = sc.next();
			
			for(int i=0; i<str.length();i++) {
				
				answer*=check(i);
				answer%=(10e7+7);
				
			}
			
			
			
			
			System.out.println("#"+test+" "+answer);
			
			
			
		}
		
	}
	public static int check(int i) {
		int num=1;
		if(i==0) {
			if(str.charAt(i)!=str.charAt(i+1)) {
				return 2;
			}else {
				return 1;
			}
		}else if(i==str.length()-1) {
			if(str.charAt(i)!=str.charAt(i-1)) {
				return 2;
			}else {
				return 1;
			}
		}else {
			for(int c=i; c<i+2;c++) {
				if(str.charAt(i-1)!=str.charAt(c)) num++;
			}
	}
		return num;
	}

}

