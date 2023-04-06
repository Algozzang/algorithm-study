public class Seonghun {
    public static void main(String[] args) {
        Solution s1 = new Solution();
        int n = 11;
        int[] stations = new int[] {4, 11};
        int w = 1;
        int s1Result = s1.solution(n, stations, w);
        System.out.println(s1Result);
        // expected result = 3;
    }
}

class Solution {
    public int solution(int n, int[] stations, int w) {
    	int answer = 0;
    	int begin = 1, end = 1;
    	for (int s : stations) {
    		end = s - w;
            if (begin < end) {
                answer += (end - begin - 1) / (2 * w + 1) + 1;
            }
    		begin = s + w + 1;
    	}
    	if (begin <= n) {
    		answer += (n - begin) / (2 * w + 1) + 1;    		
    	}
        return answer;
    }
}