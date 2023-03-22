import java.util.Arrays;
import java.util.PriorityQueue;

class dongwoo {
    public static void main(String[] args) {
        Solution s1 = new Solution();
        int s1Result = s1.solution(new int[][] { { 0, 3 }, { 1, 9 }, { 2, 6 } });
        System.out.println(s1Result);
        // expected result = 9;
    }
}

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int time = 0;
        int answer = 0;
        int i = 0;
        while (true) {
            while (i < jobs.length && jobs[i][0] <= time) {
                pq.add(jobs[i++]);
            }
            if (pq.isEmpty() && i < jobs.length) {
                time = jobs[i][0];
                while (i < jobs.length && jobs[i][0] <= time) {
                    pq.add(jobs[i++]);
                }
            }
            if (pq.isEmpty()) {
                break;
            }
            int[] now = pq.poll();
            time += now[1];
            answer += (time - now[0]);
        }

        return answer / jobs.length;
    }
}