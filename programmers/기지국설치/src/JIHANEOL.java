

class JIHANEOL {
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
        
        int s=1; // START
        
        for(int i=0;i<stations.length; i++){
            int e = stations[i]-(w+1); // End 
            if(e>=s){ // =을 붙혀야죠 사람아..
                int diff = e-s+1; 
                if(diff<2*w+1){
                    answer++;
                }else{
                    answer+=(diff-1)/(w*2+1) + 1;
                }
            }
            s=stations[i]+w+1;  
        }
        if(s<=n){ //마지막 처리 부분
            int diff=n-s+1;
             if(diff<2*w+1){
                answer++;
            }else{
                answer+=(diff-1)/(w*2+1) + 1;
            }
        }
        return answer;
    }
}