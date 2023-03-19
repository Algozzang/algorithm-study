import java.util.*;

public class sample {
	public static void main(String[] args) {
		Solution s1 = new Solution();
		int s1Result = s1.solution(new int[][] {{0, 3}, {1, 9}, {2, 6}});
		System.out.println(s1Result);
		// expected result = 9;
	}
}

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
        	int[] a = (int[]) o1;
        	int[] b = (int[]) o2;
        	return a[1]-b[1];
        });
        int idx = 0;
        int cnt = 0;
        int position = 0;
        while(cnt<jobs.length) {
        	
        	while(idx < jobs.length &&jobs[idx][0]<=position) {
        		pq.add(jobs[idx++]);
        	}
        	if(pq.isEmpty()) { // 비어있다면 포지션을 갱신
        		position = jobs[idx][0];
        	}else { // 안비어있다면 pq 다뺴기
        		int[] now = pq.poll();
            	position+=now[1];
            	answer +=position-now[0];
            	cnt++;
        	}
        	
        }
        
        return answer/jobs.length;
    }
}








