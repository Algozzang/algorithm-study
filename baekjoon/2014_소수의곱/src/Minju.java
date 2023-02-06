import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Minju {
	// 감이 안와서 구글링
	
	static int N, K;
	static int[] prime; 
	
	static PriorityQueue<Long> pq = new PriorityQueue<Long>();
	
	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		prime = new int[K + 1]; //소수 배열
	
		for(int i =0;i<K;i++) {
			prime[i] = Integer.parseInt(st.nextToken());
			pq.add((long) prime[i]); // 주어진 소수들을 우선순위 큐에 add
		}
		long curMin = 0;
		
		for(int i =0;i<N;i++) { //N번째 수까지 poll
			curMin = pq.poll(); //현재 최솟값빼서 곱하기
			
			for(int j=0;j<K;j++) { //소수개수만큼
				pq.add(curMin*prime[j]); //poll한 값과 소수 곱하기
				if(curMin % prime[j] == 0) break; //순서대로 주어진 것만 받음 (중복제거)
			}
		}

		System.out.println(curMin);
	}
}
