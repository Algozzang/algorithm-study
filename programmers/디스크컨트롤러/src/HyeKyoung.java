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
	PriorityQueue<int[]> pQueue = new PriorityQueue<>((o1, o2)->o1[1]-o2[1]);
	int[][] request;
	
    public int solution(int[][] jobs) {
    	int end=0, idx=0, cnt=0, sum=0;
    	int len = jobs.length;
    	request = new int[jobs.length][];
    	for(int i=0; i<jobs.length; i++) {
    		request[i] = jobs[i].clone();
    	}
    	
    	Arrays.sort(jobs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
    	
    	while(cnt<len) {
    		while(idx<len && jobs[idx][0]<=end) {
    			pQueue.add(jobs[idx++]);
    		}
    		
    		if(pQueue.isEmpty()) {
    			end = jobs[idx][0];
    			continue;
    		}
    		
    		int[] curJob = pQueue.poll();
    		sum+= end + curJob[1] - curJob[0];
    		cnt++;
    		end+=curJob[1];
    	}
        return sum / len;
    }
}