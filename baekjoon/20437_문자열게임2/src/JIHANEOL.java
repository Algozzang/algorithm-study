import java.io.*;
import java.util.*;

public class JIHANEOL {
	static BufferedReader br;
	static StringTokenizer st;
	static char selectStr; // 선택한  문자
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.valueOf(br.readLine());
		for(int t=0; t<test; t++) {
			char[] si = br.readLine().toCharArray();
			int K = Integer.valueOf(br.readLine());
			// abc 값 저장 하기
			if(K== 1) { //k가 1일때
                System.out.println("1 1");
                continue;
            }
			int[] abc = new int[26];
			for(int i=0; i<si.length; i++) {
				abc[si[i]-'a']+=1; 
			}
			// 3번 수행 && 4번 수행
			int min = Integer.MAX_VALUE;
			int max = -1;
			
			for(int i=0; i<si.length; i++) { //1만개
				if(abc[si[i]-'a']<K) continue; // 자리 수보다 작은 놈은 볼 필요가 없지요
				
				int cnt = 1;
				for(int j=i+1; j<si.length; j++) { 
					if(si[i]==si[j]) cnt++;
					
					if(cnt==K) {
						 min = Math.min(min, j-i+1);
	                     max = Math.max(max, j-i+1);
	                     break;
					}
				}
			}
			if(min == Integer.MAX_VALUE || max == -1) System.out.println("-1");
            else System.out.println(min + " " + max);
		}
	}
}
