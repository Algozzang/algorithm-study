import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class JIHANEOL {

	static StringTokenizer st;
	static BufferedReader br;
	static String S,T;
	static boolean check;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        boolean re = true; // 뒤로 보고 있으면 true 앞으로 보고 있으면 false
        int start=0,end=T.length()-1;
        while(end-start+1>S.length()) {
        	char temp;
        	if(re) { 
        		temp = T.charAt(end);   		
        	}else {
        		temp = T.charAt(start);        		
        		
        	}
        	if(temp=='A') { // 뒤에 넣어주는거 (안뒤집고 했다는 소리)
        		if(re) {// 뒤면
        			end--;
        		}else { // 앞이면
        			start++;
        		}
        	}else { // 뒤집었다는 소리
        		if(re) { // 뒤면
        			end--;
        		}else { // 앞이면
        			start++;
        		}
        		re=!re; // 뒤집어 주자
        	}
        }
        String si = T.substring(start, end+1);
        StringBuffer sb = new StringBuffer(si);
        String temp = sb.reverse().toString();
        if(re) {
        	if(si.equals(S)) {
        		System.out.println(1);
        	}else {
        		System.out.println(0);
        	}
        }else {
        	
        	if(temp.equals(S)) {
				System.out.println(1);
			}else {
				System.out.println(0);
			}
        }
    }
}
	
