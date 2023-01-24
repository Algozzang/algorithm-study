import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class dongwoo {

	public static void main(String args[]) {
		// 시작 00:43 끝 02:24
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		
		HashMap<Character, Long> weight = new HashMap<Character, Long>();
		HashMap<Long, ArrayList<Character>> weightRev = new HashMap<Long, ArrayList<Character>>();
		HashSet<Character> nonZero = new HashSet<>();
		long minval = Integer.MAX_VALUE;
		char minchar = ' ';
		for (int test_case = 0; test_case < T; test_case++) {
			String numStr = sc.nextLine();
			nonZero.add(numStr.charAt(0));
			int len = numStr.length()-1;
			for (Character c : numStr.toCharArray()) {
				weight.put(c, weight.getOrDefault(c, (long) 0) + (long)Math.pow(10, len));
				len--;
			}
		}
		for(Character c: weight.keySet()) {
			long now = weight.get(c);
			weightRev.put(now, weightRev.getOrDefault(now, new ArrayList<Character>()));
			weightRev.get(now).add(c);
			if(weight.get(c)<minval) {
				minval = now;
				minchar = c;
			}
		}
		Object[] weights = weight.values().toArray();
		Arrays.sort(weights, Collections.reverseOrder());
		long sum = 0;
		int SIZE = weight.size();
		int rank[] = {9,8,7,6,5,4,3,2,1,0};
		char en[] = new char[10];
		int ind = 0; 
		long now=-1;
		for (Object object : weights) {
			long temp = Long.parseLong(object.toString());
			if (now == temp) {
				continue;
			}
			now = temp;
			for (char c:weightRev.get(now)) {
				en[ind] = c;
				ind++;
			}
		}
		for (int i=SIZE-1; i>0; i--) {
			if (rank[i]==0 && nonZero.contains(en[i])) {
				int tmp = rank[i];
				rank[i] = rank[i-1];
				rank[i-1] = tmp;
			}
		}
		for (int i = 0; i < SIZE; i++) {
			sum+=(long)rank[i]*weight.get(en[i]);
		}
		
		System.out.println(sum);
	}
}
