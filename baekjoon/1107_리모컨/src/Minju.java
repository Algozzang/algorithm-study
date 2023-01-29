import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Minju {
	static int n, result;
	static boolean[] wrong;
	static boolean check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine()); // 이동하려는 채널
		int m = Integer.parseInt(br.readLine());
		wrong = new boolean[10]; // 고장난 버튼 체크할 배열
		
		if (m != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				wrong[Integer.valueOf(st.nextToken())] = true;
			}
		}
		
		if (n == 100) {
		} else if (m == 0) { // 고장난 버튼 없을 때
			String s = Integer.toString(n);
			result = Math.min(Math.abs(n - 100), s.length()); // + - 으로 이동하는 것과, 채널 치는 것 중 최솟값
		} else if (m == 10) { // 모든 버튼이 다 고장났을 때 + -로만 이동
			result = Math.abs(n - 100);
		} else { // 고장 난 버튼 있을 때
			result = Math.abs(n-100); // + - 로만 이동하기 저장해두고
			get();
		}

		System.out.println(result);
	}

	private static void get() {
		// 채널의 수는 500,000이지만 누를 수 있는 채널은 999,999 이므로
		for(int i = 0; i <= 999999; i++) { // 0 ~ 999999: 완전탐색
			//탐색숫자를 스트링으로 변환 후 각 자리를 돌면서 고장난 버튼을 눌러야 되면 
            String str = String.valueOf(i);  
            int len = str.length();
            
            boolean isBreak = false;
            for(int j = 0; j < len; j++) {
                if(wrong[str.charAt(j) - '0']) { //현 자리수가 고장난 버튼이면
                    isBreak = true; 
                    break; // 고장난 버튼을 누르는 케이스이면 제외시키기
                }
            }
            if(!isBreak) { //고장난 버튼을 누르지 않았다면
                int min = Math.abs(n - i) + len; // +-를 눌러야하는 이동횟수 + i의 숫자열 길이
                result = Math.min(min, result); //최솟값 업데이트
            }
        }        
	}
}




/*
import java.io.BufferedReader;
 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Minju { // 실패코드
	static int n, result;
	static int[] wrong;
	static List<Integer> w;
	static boolean check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 이동하려는 채널
		int m = Integer.parseInt(br.readLine());
		wrong = new int[m]; // 고장난 버튼들

		check = false; //10의배수인지
		w = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			w.add(i);
		}

		if(m!=0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				w.remove(Integer.valueOf(st.nextToken()));
			}
		}
		Collections.sort(w);
		
		if (n == 100) {
		} // 기본값
		else if (m == 0) { // 고장난 버튼 없을 때
			String s = Integer.toString(n);
			result = Math.min(Math.abs(n - 100), s.length()); // + - 으로 이동하는 것과, 채널 치는 것 중 최솟값
		} else if (m == 10) { // 모든 버튼이 다 고장났을 때
			result = Math.abs(n - 100);
		} else { // 고장 난 버튼 있을 때
			minCnt();
		}

		System.out.println(result);
	}

	private static void minCnt() { // 앞자리 수부터 검사하다가 고장났으면 차이 구하기

		String s = Integer.toString(n);
		for (int i = 0; i < s.length(); i++) {
			int cur = s.charAt(i) - '0';
			if (w.contains(Integer.valueOf(cur))) { // 수가 고장안났다면
				result++; // 그 수를 사용하고
				if( i<s.length()-1 && (s.charAt(i+1) - '0' == 0)) {
					n += Math.pow(10, s.length()-1);
					check =true;
					result--;
				}
				n -= (Math.pow(10, s.length() - 1 - i) * cur); // 수 줄이기
				
			} else { // 수가 고장났다면 차이가 가장 적은 수에서 +,- 이동하기 = 차이수만큼 ret에 더하기
				if(i==0) {
					check=true;
				}
				result = Math.min(dfs1(cur, n, result), dfs2(cur, n, result));
				return;
			}

		}
	}

	private static int dfs1(int cur, int num, int ret) { // ret : 현재 버튼 입력 수

		if (cur > 9) {
			return Integer.MAX_VALUE;
		}

		if (w.contains(cur)) { // 수가 고장안났다면
			ret++; // 그 수를 사용하고
			return (ret + makeMin(cur, Integer.toString(num)));
		} else {
			return dfs1(cur + 1, num, ret);
		}
	}

	private static int dfs2(int cur, int num, int ret) { // ret : 현재 버튼 입력 수

		if (cur < 0) {
			return Integer.MAX_VALUE;
		}
		if (w.contains(cur)) { // 수가 고장안났다면
			ret++; // 그 수를 사용하고
			return (ret + makeMin(cur, Integer.toString(num)));

		} else {
			return dfs2(cur - 1, num, ret);
		}
	}

	private static int makeMin(int cur, String num) {

		int cnt = 0;
		String max = Integer.toString(cur);
		for (int i = 1; i < num.length(); i++) {
			max += w.get(0);
			cnt++; // 자릿수만큼 채널 더하기
		}

		String min = Integer.toString(cur);
		for (int i = 1; i < num.length(); i++) {
			min += w.get(w.size() - 1);
		}
		int ten = Integer.MAX_VALUE;
		
		if(w.contains(Integer.valueOf(0)) && check) {ten = (int)Math.pow(10, num.length());}
	
		int maxDiff = Math.abs(Integer.parseInt(max) - Integer.parseInt(num));
		int minDiff = Math.abs(Integer.parseInt(min) - Integer.parseInt(num));
		int tenDiff = Math.abs(Integer.parseInt(num) - ten);
		if(tenDiff<Math.min(maxDiff, minDiff)) {
			cnt += ++tenDiff; //자릿수 하나 늘려서 저장
		}else {
			cnt += Math.min(maxDiff, minDiff);
		}
		return cnt;
	}
}
*/