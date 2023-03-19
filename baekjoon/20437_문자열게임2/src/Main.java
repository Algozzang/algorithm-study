import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	//3번이나 4번이나 문자열 첫번째와 마지막은 같은 문자임
	//알파벳 별로 나온 인덱스를 저장하는 리스트를 만들어서 인덱스에서 개수만큼 차이를 구해 길이를 구함
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> alpha[] = new ArrayList[26]; // 알파벳 별로 인덱스 저장
		int t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			String w = br.readLine();
			int k = Integer.parseInt(br.readLine());
			int leng = w.length();
			char[] charArr = new char[leng];
			charArr = w.toCharArray();
		
			for(int i =0;i<26;i++) {
				alpha[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < leng; i++) {
				alpha[charArr[i]-'a'].add(i); // 해당 문자 인덱스 저장
			}
			
			int min =100000;	
			int max =0;

			for (int i = 0; i < 26; i++) { //알파벳 돌면서
				int size= alpha[i].size();
				if(size<k) continue; //나온 개수가 k가 되지 않으면 컨티뉴
				for(int j =0;j<=size-k;j++) { //인덱스 비교하면서 길이 최소 최대 찾기
					int len = alpha[i].get(j+k-1)-alpha[i].get(j)+1; // 길이니까 인덱스빼서 1더해주기
					if(max<len) max =len;
					if(min>len) min =len;
				}
			}
	
			if(max == 0) sb.append("-1\n"); // 개수가 없다면
			else sb.append(min + " " + max+ "\n");
		}
		System.out.println(sb);

	}
	
/* 시간초과코드 => 맵에 char랑 인덱스큐 저장해서 인덱스 연산을 업데이트
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<Character, List<Integer>> map = new HashMap<>();
		int t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			String w = br.readLine();
			int k = Integer.parseInt(br.readLine());
			int leng = w.length();
			char[] charArr = new char[leng];
			charArr = w.toCharArray();
		
			int maxNum = 1;
			for (int i = 0; i < leng; i++) {
				char c = charArr[i];
				if (map.get(c) == null) {
					map.put(c, new LinkedList<Integer>(Arrays.asList(i)));
				} else {
					int num = map.get(c).size() + 1;
					if (num > maxNum)
						maxNum = num;
					map.get(c).add(i);
				}
			}
		
			if (maxNum < k) {
				sb.append(-1+ "\n");
				continue;
			}
			
			int min =100000;	
			int max =0;
			for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
				Character key = entry.getKey();
				List<Integer> val = entry.getValue();
				
				if(val.size()>=k) { // 인덱스 저장한 개수 k넘는 애들 중에서
					for(int i =0;i<val.size()-1;i++) { //인덱스 비교하면서 길이 최소 최대 찾기
						if(i<=val.size()-k) {
							int len = val.get(i+k-1)-val.get(i)+1; // 길이니까 인덱스빼서 1더해주기
						
							if(max<len) max =len;
							if(min>len) min =len;
							
						}
					}
				}
			}
		
			sb.append(min + " " + max+ "\n");
		}
		System.out.println(sb);

	}
*/
}
