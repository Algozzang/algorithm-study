import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 사람 수
		int m = Integer.parseInt(st.nextToken()); // 파티 수
		Queue<Integer> knowsQue = new LinkedList<>(); // 진실을 알게된 사람들을 순서대로 줄세워
		Map<Integer, List<Integer>> partyPerson = new HashMap<>(); // key가 파티번호, value가 파티에 참석하는 사람
		Map<Integer, List<Integer>> PersonParty = new HashMap<>(); // key가 사람, value가 그 사람이 참석하는 파티
		boolean[] isLiableParty = new boolean[m]; // 거짓말 할 수 있는 파티면 true, 초기값을 true로 놓고 없애갈 계획
		boolean[] checkedPerson = new boolean[n + 1]; // 그 사람이 참석한 파티를 모두 살펴봤다면 true , 탐색했는지 확인 용도.
		Arrays.fill(isLiableParty, true);

		// 입력.
		st = new StringTokenizer(br.readLine());
		int know = Integer.parseInt(st.nextToken());
		for (int i = 0; i < know; i++) {
			knowsQue.add(Integer.parseInt(st.nextToken()));
		}

		for (int partyNum = 0; partyNum < m; partyNum++) {
			st = new StringTokenizer(br.readLine());
			int partySize = Integer.parseInt(st.nextToken());
			for (int i = 0; i < partySize; i++) {
				int participant = Integer.parseInt(st.nextToken());
				if (!partyPerson.containsKey(partyNum)) {
					partyPerson.put(partyNum, new ArrayList<>());
				}
				if (!PersonParty.containsKey(participant)) {
					PersonParty.put(participant, new ArrayList<>());
				}

				partyPerson.get(partyNum).add(participant);
				PersonParty.get(participant).add(partyNum);
			}
		}
		
		// 로직
		while (!knowsQue.isEmpty()) {
			int knower = knowsQue.poll();				// 1. 뭘 좀 아는놈
			checkedPerson[knower] = true;
			for (int party : PersonParty.getOrDefault(knower, new ArrayList<>())) {		// 2. 그놈이 참석한 파티에
				if (!isLiableParty[party]) {			// 4. 근데 이미 본 파티면 스킵	
					continue;					
				}
				isLiableParty[party] = false;
				for (int person : partyPerson.get(party)) {			
					if (!checkedPerson[person]) {			// 5. 이미 본 놈이여도 스킵
						knowsQue.add(person);					// 3. 같이 참석해서 진실을 알게된 사람들 queue에 추가
					}
				}
			}
		}
		
		// 출력
		int result = 0;
		for (int i = 0; i < isLiableParty.length; i++) {
			if (isLiableParty[i]) {
				result++;
			}
		}
		System.out.println(result);
	}
}
