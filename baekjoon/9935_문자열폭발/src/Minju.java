import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Minju {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/* input */
		StringBuilder sb = new StringBuilder();
		String origin = br.readLine();
		String change = br.readLine();

		int leng = change.length();

		// 슬라이딩 윈도우
		for (int j = 0; j < origin.length(); j++) {
			sb.append(origin.charAt(j));// 원본 문자열 하나씩 넣어보면서
			int sbLeng = sb.length();
			// 방금 추가한 상태로 뒤에서부터 leng만큼 문자열이 바꿀 문자열과 같다면 삭제
			if (leng<=sbLeng && change.equals(sb.substring(sbLeng - leng,sbLeng))) { 
				sb.delete(sbLeng - leng,sbLeng);
			
			}
		}

		if (sb.length() == 0)
			System.out.print("FRULA");
		else
			System.out.print(sb);
	}
}

/* 시간초과 
 
public class Minju {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder(br.readLine());
		String change = br.readLine();

		int leng = change.length();


		// 슬라이딩 윈도우
		for (int j = 0; j < sb.length() - leng + 1; j++) {
			if (j>=0 && change.equals(sb.substring(j, j + leng))) { // 같다면 삭제
				sb.delete(j, j + leng);
				j=j-leng;
			}
		}

		if (sb.length() == 0)
			System.out.print("FRULA");
		else
			System.out.print(sb);
	}
}
*/
