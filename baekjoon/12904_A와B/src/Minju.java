import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Minju {
	// 그냥 완탐 재귀로 풀면 시간초과남
	// 1. t가 B로 끝나면 s는 무조건 뒤집고 뒤에 b를 추가해야 함
	// 2. t가 A로 끝나면 s는 무조건 a 추가해야 함
	// 거꾸로 생각하면 t가 A로 끝나면 s는 a추가했던 것, 그니까 a 빼주기
	static int possible =1;
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder t = new StringBuilder();
		String s = br.readLine();
		t.append(br.readLine());

		while(!t.toString().equals(s)) {
			if(t.length() == 0) {possible = 0; break;}
			int len = t.length();
			if(t.charAt(len-1) == 'A') {
				t.setLength(len-1);
			}else {
				t.setLength(len-1);
				t.reverse();
			}
		}
		System.out.println(possible);
	}

}
