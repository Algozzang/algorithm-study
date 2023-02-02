import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;

public class dongwoo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp;
		tmp = br.readLine().split(" ");
		int N = Integer.parseInt(tmp[1]); // 목표, N번째 숫자 출력해야함. 
		
		HashSet<Integer> jungbok = new HashSet<>();
		PriorityQueue<Integer> priorityQueueLowest = new PriorityQueue<>(); // 우선순위 큐로 현재 가장 낮은 숫자를 관리 
		int K = Integer.parseInt(tmp[0]); // K, 소수의 개수
		tmp = br.readLine().split(" ");
		int[] numbers = new int[K];
		
		for (int i=0; i<K; i++) {
			numbers[i] = Integer.parseInt(tmp[i]);
			priorityQueueLowest.add(numbers[i]);
			jungbok.add(numbers[i]);
		}
		
		int maxValue = numbers[numbers.length-1];
		for (int i=0; i<N-1; i++) {
			int now = priorityQueueLowest.poll();
			for (int j=0; j<K; j++) {
				int newNum = now*numbers[j];
				if (jungbok.contains(newNum) || (int)Integer.MAX_VALUE/now < numbers[j]) {
					continue;
				}
				if (priorityQueueLowest.size()>N && maxValue<newNum) {
					break;
				}
				priorityQueueLowest.add(newNum);
				jungbok.add(newNum);
				maxValue = Math.max(newNum, maxValue);
			}
		}
		System.out.println(priorityQueueLowest.peek());
	}

}
