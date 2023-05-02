package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 소인수분해해서 다 똑같이 나눠가질수있는만큼 나눠가지기
	// 그 공통값이 gcd
	// 각 숫자별로 부족한 개수만큼만 세주면 움직이는 횟수(남은 애들은 부족한거 채울 때 교환되니까)
	// 999499 소수임
	static int[] total; // 소수리스트랑 연결되며 애들이 차례로 카운트 되어 있음
	static boolean[] primeVisit = new boolean[1000001]; // 소수
	static int[][] numDiv; // 각 숫자마다의 소인수분해 배열 저장
	static int[] num; // 원래입력받는배열
	static List<Integer> prime = new ArrayList<Integer>(); // prime
	static int primeSize;

	public static void main(String[] args) throws IOException, NumberFormatException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		num = new int[N];
		era(); // 에라토스테네스의체
		numDiv = new int[101][primeSize]; 
		total = new int[primeSize];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			if (num[i] != 1) div(num[i], i);
		}
		int moveCnt = 0; // 출력값
		// 공통 GCD
		int gcd = 1;
		int cur = 0;
		for (int i = 0; i < prime.size(); i++) {
			// if(!prime[i]) continue;

			if (total[i] > 0) {
				cur = prime.get(i);
				gcd *= (Math.pow(cur, (total[i] / N))); // 출력할 gcd 
				total[i] /= N; // n만큼 나눠주기
			}

			// 이동 횟수 구하기
			if (total[i] > 0) {
				for (int j = 0; j < N; j++) {
					if (total[i] > numDiv[j][i]) { // 부족하다면
						moveCnt += (total[i] - numDiv[j][i]); // 부족한 개수만큼 이동
					}
				}
			}

		}

		System.out.println(gcd + " " + moveCnt);

	}

	private static void div(int n, int k) { // 소인수분해
		for (int i = 0; i < prime.size(); i++) {
			int cur = prime.get(i);
			while (n % cur == 0) {
				n /= cur;
				total[i]++;
				numDiv[k][i]++;
			}
			if (n == 1)
				break;
		}

	}

	private static void era() {

		for (int i = 2; i < 1000000; i++) {
			if (!primeVisit[i]) {
				prime.add(i);
				for (int j = i; j < 1000000; j += i) {
					primeVisit[j] = true;
				}
			}
		}
		
		primeSize = prime.size();

	}

}