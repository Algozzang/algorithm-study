import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Minju { 
	static int n, min, max, maxIndex; 
	static int[] origin; //지금 탭 개수
	static int[] correct; // 올바른 탭 개수
	static int[] diff; // 현재탭과 올바른 탭의 차이 개수 

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/* input */
		n = Integer.parseInt(br.readLine());
		origin = new int[n];
		correct = new int[n];
		diff = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i =0;i<n;i++) origin[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i =0;i<n;i++) {
			correct[i] = Integer.parseInt(st.nextToken());
			diff[i] = correct[i] - origin[i];
			if(max < Math.abs(diff[i])) {
				max = Math.abs(diff[i]);
				maxIndex = i;
			}
		}
		
		//diff 값이 큰 애들주위부터 같은 부호라면 연산 후 같이 0과 가깝게 만들어주고 나머지 한줄씩 남은 애들 처리해주기
		recur(maxIndex);
		/* output */
		System.out.println(min);
	}
	
	private static void recur(int idx) {
		
		
		int diffCur = diff[idx];
		if(diffCur == 0 ) return; //모든 줄이 차이값이 0이 되면 탈출
		
		//위아래 줄로 탐색해서 같은 부호 찾기
		if(diffCur>0) { //양수
			diff[idx]--; //탭하나 
			//idx 윗줄 검색
			if(idx-1>=0) { // 0번째줄아래로 내려가지 않게
				for(int i = idx-1;i>=0;i--) {
					if(diff[i]>0) { // 현재 diff가 MAX인 값과 같은부호일시
						diff[i]--; // 같이 tab 삭제
					}else { //다른부호나 0이라면
						break;
					}
				}
			
			}
			if(idx+1<n){ //아랫줄
				for(int i = idx+1;i<n;i++) {
					if(diff[i]>0) { // 현재 diff가 MAX인 값과 같은부호일시
						diff[i]--; // 같이 tab 삭제
					}else { //다른부호나 0이라면
						break;
					}
				}
			
			}
			for(int j =0;j<n;j++) { // update max
				max = 0;
				if(max<Math.abs(diff[j])) {
					max = Math.abs(diff[j]);
					maxIndex = j;
				}
			}
			recur(maxIndex); //다시 맥스로 재귀돌리기
			min++; //출력값 증가
			
		}else { // 음수
			diff[idx]++; //탭하나 추가
			//idx 윗줄 검색
			if(idx-1>=0) { // 0번째줄아래로 내려가지 않게
				for(int i = idx-1;i>=0;i--) {
					if(diff[i]<0) { // 현재 diff가 MAX인 값과 같은부호일시
						diff[i]++; // 같이 tab 추가
					}else { //양수나 0이라면
						break;
					}
				}
			
			}
			if(idx+1<n){ //아랫줄
				for(int i = idx+1;i<n;i++) {
					if(diff[i]<0) { // 현재 diff가 MAX인 값과 같은부호일시
						diff[i]++; 
					}else { //양수나 0이라면
						break;
					}
				}
			
			}
			for(int j =0;j<n;j++) { // update max
				max = 0;
				if(max<Math.abs(diff[j])) {
					max = Math.abs(diff[j]);
					maxIndex = j;
				}
			}
			recur(maxIndex); //다시 맥스로 재귀돌리기
			min++;
		}
	}

}
