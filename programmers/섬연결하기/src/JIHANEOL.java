import java.util.Arrays;
import java.util.PriorityQueue;

class Node implements Comparable<Node>{
		int from, to, cost;
		
		Node(int f,int t, int c){
			this.from = f;
			this.to = t;
			this.cost = c;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}
public class JIHANEOL {
	public static void main(String[] args) {
		
		int n = 4;
		int[][] costs = new int[][] { 
			{0,1,1},
			{0,2,2},
			{1,2,5},
			{1,3,1},
			{2,3,8}
		};
		Solution s = new Solution();
		if (s.solution(n, costs)==4) {
			System.out.println("정답");
		} else {
			System.out.println("땡");
		}
			
	}
}

class Solution {
    int[] parents;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 진짜 바보다 n으로  해버렸네 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
        for(int i =0; i<costs.length; i++){
            pq.add(new Node(costs[i][0],costs[i][1],costs[i][2]));
        }
        parents = new int[n];
        for(int i=0 ; i<n; i++){
            parents[i] = i;
        }
        while(!pq.isEmpty()){
            Node now = pq.poll();
            
            if(union(now.from,now.to)){
                answer+=now.cost;
            }
        }
        return answer;
    }
    public boolean union(int x, int y){
        if(find(x) == find(y)){
            return false;
        }else{
            parents[find(y)] = find(x);
            return true;
        }
    }
    public int find(int x){
        if(parents[x]==x){
            return x;
        }
        return parents[x] = find(parents[x]);
        
        
    }
}