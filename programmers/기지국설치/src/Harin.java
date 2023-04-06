class Solution {
    public int solution(int n, int[] stations, int w) {
        
        int answer = 0;
        int coverage = 2*w+1;
        
        if(stations.length > 1){
            //중간에 빈 구역들
            for(int i=0; i<stations.length-1; i++){

                int left = stations[i]+w + 1;       //현재 구역의 오른쪽 끝 다음
                int right = stations[i+1]-w - 1;    //다음 구역의 왼쪽 끝 이전
                
                if(left <= right){
                    int emptyLen = right-left+1;
                    answer += ((emptyLen+coverage-1)/coverage);

                }
            }
        }
        //시작, 끝 구역
        if(stations[0] > 1+w){
            int left = stations[0]-w-1; 
            answer += (left+coverage-1)/coverage;
        }
        if(stations[stations.length-1] < n-w){
            int right = stations[stations.length-1] + w + 1;
            answer += (n-right+1 + coverage-1)/coverage;
        }
        return (int) answer;        
    }
}
