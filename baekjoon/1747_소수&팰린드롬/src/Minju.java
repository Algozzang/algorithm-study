import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Minju {
	
	// 1-1. 입력받는 수까지 소수 배열 먼저 만들어 두고, 있는지 확인 => 아주 오래걸림 그리고 틀림 
	// 1-2. 입력받는 수를 소수인지 판단 = 자기자신까지 돌면서 배수있으면 소수아니게  = 시간초과
	// 1-3. 범위를 Math.sqrt()까지 받기
	// 2. 팰린드롬 확인 
	
	static int n;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static HashSet<Integer> set =new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); 
		
		while(true) {
			int cur = n++;
			if(isPrime(cur) && isPalin(cur)) {
				System.out.println(cur);
				break;
			}
		}

	}
	
	private static boolean isPrime(int cur) {
	
		if(cur ==1) return false;
		
		int i =2;
		while(i<=Math.sqrt(cur)) {
			if(cur%i==0) return false; //1말고 약수가 있다면 소수아님
			i++;
		}
		return true;
	}

	private static boolean isPalin(int num) {
		String str = Integer.toString(num);
		int start = 0;
		int end = str.length()-1;
		// 투포인터로 같은지 확인
		while(start<end) {
			if(str.charAt(start) == str.charAt(end)) {
				start++;
				end--;
			}else {
				return false;
			}	
		}
		
		return true;
	}
}

// 잘못한 풀이: 소수 배열 만들어 놓기
/*
int high = n*100;
boolean prime[] = new boolean[high+1];
Arrays.fill(prime, true);
for (int i =2;i<=Math.sqrt(high);i++) {
	if(prime[i]) {
		for(int j=i*i; j<=high ;j+=i) {
			prime[j] = false;
		}
	}
	
}
*/
