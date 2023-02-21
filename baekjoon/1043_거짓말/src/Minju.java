import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Minju {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int result = m;
		// 파티 정보 
		List<Integer>[] party = new ArrayList[m];
		
		st = new StringTokenizer(br.readLine());
		int knowNum = Integer.parseInt(st.nextToken());// 진실아는 사람 수
	
		HashSet<Integer> knowPeople = new HashSet<>(); //진아사
		for (int i = 0; i < knowNum; i++) { 
			knowPeople.add(Integer.parseInt(st.nextToken())); //진실아는 사람 추가
		}
		
		for(int i = 0;i<m;i++) { // party input
			party[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int pNum = Integer.parseInt(st.nextToken());
			
			for(int j=0;j<pNum;j++) { // 파티에 있는 사람들 파티 i번째에 넣기
				party[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		boolean[] partyCheck = new boolean[m]; // 확인한 파티 체크
		boolean[] peopleCheck = new boolean[n+1]; //1부터n까지의 사람 체크
		
		// 진실을 아는 사람 모두 큐에 넣기
		for(Integer kp : knowPeople) {
			q.add(kp);
			peopleCheck[kp] = true;
		}
		
		while(!q.isEmpty()) {//bfs
			
			int cur= q.poll();
			
			for(int i =0;i<m;i++) { 
				if(partyCheck[i]) continue; // 이미 확인한 파티면 넘김
				if(party[i].contains(cur)) continue; // 자기자신이면 넘김
				for(int j = 0;j<party[i].size(); j++) { // 없으면 파티 인원 확인하면서
					int next= party[i].get(j); // 다음 사람 가져옴
					
					if(peopleCheck[next]) continue; 
					peopleCheck[next] = true;
					
					q.add(next);
				}
				partyCheck[i] = true;
				result--;
			}
		}
		
		System.out.println(result);
	}
}






/*
 import java.io.BufferedReader;
 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Minju {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		List<Set<Integer>> people = new ArrayList<>();
		for(int i =0;i<n;i++) {
			people.add(new ArrayList<Integer>());
		}
		
		
		st = new StringTokenizer(br.readLine());
		int knowNum = Integer.parseInt(st.nextToken());
	
		HashSet<Integer> knowPeople = new HashSet<>();
		for (int i = 0; i < knowNum; i++) { // 거짓말을 아는 사람
			knowPeople.add(Integer.parseInt(st.nextToken()));
		}
		
		List<HashSet<Integer>> party = new ArrayList<>();
		
		for (int i = 0; i < m; i++) { // 파티 인풋
			st = new StringTokenizer(br.readLine());
			int partyNum = Integer.parseInt(st.nextToken()); // 현재 파티 사람 수
			
			HashSet<Integer> set = new HashSet<>();
			for(int j = 0;j<partyNum;j++) {
				boolean known = false;
				int curPerson = Integer.parseInt(st.nextToken());
				set.add(curPerson);
				if(knowPeople.contains(Integer.valueOf(curPerson))) {
					known = true;
				}
				
				
				
				if(known) {
					for (Integer in : set) {
						knowPeople.add(Integer.valueOf(in));
					}
				}
			}
			party.add(set);
		}
		
		int result = 0;
		OUTER: for (Iterator iterator = party.iterator(); iterator.hasNext();) {
			HashSet<Integer> set = (HashSet<Integer>) iterator.next();
			
			for (Integer in : set) {
				if(knowPeople.contains(Integer.valueOf(in))){ // 진실 아는사람 있으면
					continue OUTER;
				}
			}
			result++;
		}

		System.out.println(result);
	}

}
*/