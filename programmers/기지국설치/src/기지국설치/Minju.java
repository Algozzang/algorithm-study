package 기지국설치;

class Minju {
	// s랑 n 같을때 못찾아서 디버깅오래함 ㅜㅡㅜ
	public static void main(String[] args) {
		Solution s1 = new Solution();
		int n = 13;
		int[] stations = new int[] {3,7};
		int w = 3;
		int s1Result = s1.solution(n, stations, w);
		System.out.println(s1Result);
	
	}
}

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int w2 = 2*w+1;
       
        // 설치된 범위 구하기
        int s = 1;
        int e = 0;
        for(int i =0;i<stations.length; i++) {
        	
        	e = stations[i] - w;
        	if(e<=1) { // 기지국 1부터 있음
        		s = stations[i] + w + 1; // 설치된 범위 체크하고 그뒤로부터 스타트가 됨
        	}else {
        		answer += (int)Math.ceil((double)(e-s)/w2); // 계산부터하고
        		s = stations[i] + w + 1; // start 지점 update
        	}
        }
     
        if(s<n) { // 스타트가 n 보다 작으면 남은 뒷부분도 처리해줘야됨
        	//System.out.println(s+" "+ e);
        	answer += (int)Math.ceil((double)(n-s)/w2);	// 마지막 범위 개수 더해주기
        } else if(s==n){ // 스타트가 마지막 남은 하나면 그냥 1더해주기
        	//System.out.println("같을때 도나용");
        	answer += 1;
        }
        return answer;
    }
}



// 어디가 틀린지 모르겠는 이전 57점짜리코드
//package 기지국설치;
//
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//
//class Minju {
//	public static void main(String[] args) {
//		Solution s1 = new Solution();
//		int n = 13;
//		int[] stations = new int[] {5,8,12};
//		int w = 1;
//		int s1Result = s1.solution(n, stations, w);
//		System.out.println(s1Result);
//	
//	}
//}
//
//class Solution {
//  public int solution(int n, int[] stations, int w) {
//      int answer = 0;
//
//      int[] line = new int[n+1];
//      int w2 = 2*w+1;
//     
//      List<int[]> installed = new LinkedList<int[]>();
//      // 설치된 범위 구하기
//      installed.add(new int[] {stations[0]-w,stations[0]+w}); //첫번째 범위 넣고
//      for(int i =1 ; i<stations.length; i++){ 
//      	int size = installed.size();
//          int cur = stations[i];
//          if(cur-w<=installed.get(size-1)[1]) { //범위 겹치면 범위 end 확장시키기
//          	installed.get(size-1)[1] = cur+w;
//          }else {
//          	installed.add(new int[]{cur-w,cur+w}); //범위 안겹치면 그냥 넣고
//          }
//      }
//      //설치 안된 범위부터 개수세기
//      int s=1,e=1;
//      if(installed.get(0)[0] == 1) {
//      	s= installed.get(0)[1]+1;
//      	for(int i =1;i<installed.size(); i++) {
//	        	e = installed.get(i)[1]-1;
//	        	answer += (int)Math.ceil((double)(e-s+1)/w2);	// 개수더해주기
//	        	s = installed.get(i)[0]+1;
//	        }
//      	  e = n;
//      	  System.out.println(answer);
//            answer += (int)Math.ceil((double)(e-s+1)/w2);	// 마지막 범위 개수 더해주기
//      }
//      else {
//	        for(int i =0;i<installed.size(); i++) {
//	        	e = installed.get(i)[0]-1;
//	        	   System.out.println(" end:"+ e + " start:" + s + "num" + (int)Math.ceil((double)(e-s+1)/w2));
//	        	answer += (int)Math.ceil((double)(e-s+1)/w2);	// 개수더해주기
//	        	s = installed.get(i)[1]+1;
//	        }
//	        e = n;
//	        System.out.println(" "+ answer);
//	        answer += (int)Math.ceil((double)(e-s+1)/w2);	// 마지막 범위 개수 더해주기
//      }
//    
//
//      
//      
//      for(int i =0;i<installed.size(); i++) {
//      	System.out.println(Arrays.toString(installed.get(i)));
//      }
//      return answer;
//  }
//}