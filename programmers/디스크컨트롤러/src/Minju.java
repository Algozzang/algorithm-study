import java.util.Arrays;
import java.util.PriorityQueue;

public class Minju {
	// pq는 시간 순서로 정렬하고, 인풋배열은 시작하는 시간순서로 정렬
	public static void main() {
		Solution s1 = new Solution();
		int s1Result = s1.solution(new int[][] {{0, 3}, {1, 9}, {2, 6}});
		System.out.println(s1Result);
		// expected result = 9;
	}
}

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int doneCnt = 0; // 처리된 디스크 개수
        int cur = 0; //현재 작업 끝난 시간
        Arrays.sort(jobs, (n1,n2) -> n1[0]-n2[0]); //시작하는 시간 먼저인 애로 정렬 asc
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((n1,n2)->n1[1]-n2[1]); // 소요시간 순으로 asc 정렬 
        
        int i =0;
        while(true) {
        
        	if(doneCnt== jobs.length) break; // 처리된 디스크가 입력받은 개수보다 넘으면
        	while(i<jobs.length && jobs[i][0] <= cur) { // 지금 시간이 작업이 들어올수있는경우인 애들만
        		pq.add(jobs[i++]);
        	}
        	if(pq.isEmpty()) { // 비었으면 현재시간은 
        		cur = jobs[i][0];
        	}else { // 안비었으면
        		int[] poll = pq.poll();
        		answer += poll[1] + cur - poll[0];
        		cur += poll[1];
        		doneCnt++;
        	}
        	
        }
        
        //평균
        answer /= jobs.length;
        return answer;
    }
}
