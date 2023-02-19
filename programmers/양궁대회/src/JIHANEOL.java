import java.util.*;

public class JIHANEOL {

	public static void main(String[] args) {
		Solution s1 = new Solution();
		Solution s2 = new Solution();
		Solution s3 = new Solution();
		Solution s4 = new Solution();
		System.out.println(Arrays.toString(s1.solution(5, new int[] { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 })));
		System.out.println(Arrays.toString(s2.solution(1, new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 })));
		System.out.println(Arrays.toString(s3.solution(9, new int[] { 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 })));
		System.out.println(Arrays.toString(s4.solution(10, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 })));
	}
}

class Solution {
    static int result=-100;
    static int k=10;
    static int lion;
    static int pitch;
    static  int[] answer,temp;

    static public int[] solution(int n, int[] info) {
        answer = new int[]{-1};
        temp = new int[11]; // 임시 저장
        game(0, n, info);
        return answer;
    }
   static  public void game(int idx, int n,int[] info) { // 맨처음 자리수, 화살갯수, 적 점수
	    if(idx==11) { // 기저 부분
	        int lion=0; // 점수들
	        int pitch=0;
	        for(int i=0; i<10; i++) { // 각 점수 계산하기
	            if(info[i]==0 && temp[i]==0) {
	                continue;
	            }else {
	                if(info[i]<=temp[i]) {
	                    lion+=k-i;
	                }else {
	                    pitch+=k-i;
	              
	                }
	            }
	        }
	        if(lion>pitch) { // 라이언이 점수가 높다면.
	            if(result<=lion-pitch) {
	                if(result==lion-pitch) { //같을시 가장 낮은점수 많이 쏜것을 선택한다.
	                    answer=checkArr(temp, answer).clone();
	                }else {
	                    answer=temp.clone();
	                }
	                result=lion-pitch;
	            }
	        }
	        return;
	    }
    for(int i=idx; i<11; i++) { 
        if(n!=0) {// 쏠게있다면
            if(info[i]!=0) { // 점수가 있다면
                if(info[i]+1<=n) {// 점수 +1을 쏠수 있다면
                    temp[i]=info[i]+1;
                    game(idx+1, n-info[i]-1, info);
                    temp[i]=0;
                }else { // 점수 +1을 쏠수 없다면 다음으로 넘어가기
                    if(i==10) {  // 마지막 점수 부분이면 다쏴야지
                        temp[i]=n;
                        game(idx+1, n-n, info);
                        temp[i]=0;
                    }
                    continue;
                }
            }else { // 점수가 없다면 1발만 쏘자
                temp[i]++;
                game(i+1, n-1, info);
                temp[i]--;
            }
        }else {//쏠게 없다면
                game(11, 0, info);
            }

        }
   }
   static int[] checkArr ( int[]a, int[] b ) {// 비교해주는 함수..
	   for(int i = a.length-1; i >=0 ; i--) {
		   if(a[i] == b[i]) continue;
		   if(a[i] < b[i]) return b;
		   break;
	   }
	   return a;
   }
}