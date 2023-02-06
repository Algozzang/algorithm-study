import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class HyeKyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		int k = Integer.parseInt(str[0]), n = Integer.parseInt(str[1]);
		long[] pNum = new long[k];
		PriorityQueue<Long> pQueue = new PriorityQueue<>();
		str = br.readLine().split(" ");
		for (int i = 0; i < k; i++) {
			pNum[i] = Integer.parseInt(str[i]);
			pQueue.add(pNum[i]);
		}
		long num = 0;
		for (int i = 0; i < n; i++) {
			num = pQueue.poll();

			for (int j = 0; j < k; j++) {
				pQueue.add(num * pNum[j]);
				if (num % pNum[j] == 0)
					break;
			}
		}
		System.out.println(num);
	}
}
