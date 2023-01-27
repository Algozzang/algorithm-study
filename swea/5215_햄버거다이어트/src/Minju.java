import java.io.*;


//230122

public class Minju { // 5215 햄버거 다이어트
	
	/*
	 * T
	 * N재료수 L제한칼로리
	 * S맛점수 K칼로리
	 * ...
	 */
	
	//냅색 알고리즘 공부하고 풂
	
	static int[] s;
	static int[] k;
	static int N;
	static int L;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			String[] line = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			L = Integer.parseInt(line[1]);
			
			s = new int[N]; 
			k = new int[N]; 
			for(int i =0;i<N;i++) {
				String[] str = br.readLine().split(" ");
				s[i] = Integer.parseInt(str[0]);//점수
				k[i] = Integer.parseInt(str[1]);//칼로리
			}
		
			System.out.println("#"+tc+" "+ getMax(0,0));
		}
		
	}
	
	private static int getMax(int i, int l){ // 몇개 담았는지, 칼로리 몇인지
		
		if(i==N) return 0; //더이상 더 넣을 수 없을 때 0더하기
		
		// 포함하여 계산
		int case1 = 0;
		if(l+k[i] <= L) {//지금까지 칼로리 + i번째 칼로리
			case1 = s[i] + getMax(i+1, l+k[i]); //i번째 점수와 미래의 점수배열
		}
		// 포함하지 않고 계산
		int case2 = getMax(i+1,l);
		
		return Math.max(case1, case2);
	}
	
}
