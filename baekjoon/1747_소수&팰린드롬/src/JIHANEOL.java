import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class JIHANEOL{
	static int answer;
	static int N;
	static int si(String s) {return Integer.valueOf(s);}  
	static String si1(int s) {return s+"";}
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		N = si(br.readLine());	
		int answer=0;
		for(int i=N; i<100000000; i++) {
            if(i==1) continue;
			if(isprime(i)) {
				if(ispalin(si1(i))) {
					answer=i;
					break;
				}
			}
		}
		System.out.println(answer);
	}	
	static boolean isprime(int num) {
		for(int i=2; i<=Math.sqrt(num); i++) {
//			System.out.println(num/i);
			if ((num%i)==0) { 
				return false;
			}
		}
		
		return true;
	}
	static boolean ispalin(String s) {
		  StringBuffer sb = new StringBuffer(s);
	      String reversedStr = sb.reverse().toString();
	      if(s.equals(reversedStr)) {
	    	  return true;
	      }
	        
		
		return false;
	}
	
	
}










