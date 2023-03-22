import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HyeKyoung {
	public static void main(String[] args) {
		Solution s1 = new Solution();
		int s1Result = s1.solution(new int[][] {{0, 3}, {1, 9}, {2, 6}});
		System.out.println(s1Result);
		// expected result = 9;
	}
}

class Solution {
    public int solution(int[][] jobs) {
    	PriorityQueue<int[]> pQueue = new PriorityQueue<>((o1, o2)->o1[1]-o2[1]);
    	int end=0, idx=0, cnt=0, sum=0;
    	int len = jobs.length;
    	
    	Arrays.sort(jobs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
    	
    	while(cnt<len) {
    		while(idx<len && jobs[idx][0]<=end) {
    			// 끝나기 전에 요청 들어온 애 pq에 추가
    			pQueue.add(jobs[idx++]);
    		}
    		
    		if(pQueue.isEmpty()) {
    			//다른애 들어오는 시간으로 점프
    			end = jobs[idx][0];
    			continue;
    		}
    		
    		//수행시간 짧은 순으로 먼저 처리
    		int[] curJob = pQueue.poll();
    		sum+= end + curJob[1] - curJob[0]; //요청시간~수행끝 시간
    		cnt++;
    		end+=curJob[1];
    	}
        return sum / len;
    }
}