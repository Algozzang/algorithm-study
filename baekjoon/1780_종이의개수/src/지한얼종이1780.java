
import java.io.*;
import java.util.*;


public class 지한얼종이1780 {
		static List<String>[] arr;
		static int[] answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.valueOf(br.readLine());
		arr = new List[n];
		answer=new int[3];
		for(int i=0;i<n;i++) { // 담기
			arr[i] = Arrays.asList(br.readLine().split(" "));
		}
		papercut(0, 0, n);
		for(int i=0;i<3;i++)
		System.out.println(answer[i]);
	}
	static void papercut(int row,int col,int n) {
		String firs =  arr[row].get(col); // 첫 자리
		for(int i=0; i<n;i++) {
			for(int j=0; j<n; j++) {
				if(!firs.equals(arr[i+row].get(j+col))) { //다르다면
					for(int a=0;a<3;a++) {	// 3등분할
						for(int b=0;b<3;b++) {
							papercut(n/3*a+row,n/3*b+col,n/3);
						}
					}
					return;
				}
			}
		}
		// 같다면 
		check(firs);
		return;		
	}
	static void check(String s) {
		if(s.equals("-1")) {
			answer[0]++;
		}else if(s.equals("0")) {
			answer[1]++;
		}else {
			answer[2]++;
		}
	}
}


			
