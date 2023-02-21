import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
/*
5 5 2
1 0 1 1 1
0 1 1 1 1
1 0 1 0 1
1 1 0 1 0
1 0 1 0 1
 */

public class Minju { 
    /*
     * 조합으로 궁수3명 뽑고 궁수 한명당 모든 적들 돌면서 가까운 적 찾아 리스트에 저장해두기
     * 리스트에 저장해둔 적 지우고 한턴 끝났으니 적들 내려오고
     * 성 만나면 적들 지우고 아니면 움직이고 다시 궁수가 공격하기
     */
	static int n, m, d, eNum, arNum, result;
	static boolean[] isArcher;
	static ArrayList<Integer[]> enemy;


	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		isArcher = new boolean[m];

		enemy = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) { // 적 저장
					enemy.add(new Integer[] { i, j });
					eNum++; // 적 수 증가
				}
			}
		}

		recur(0, 0); // 궁수3개뽑으면서 적 최대수 구하기
		System.out.println(result);
	}

	private static void recur(int idx, int cnt) {

		if(result == eNum) return; //이미 result가 최대면 돌지 않음
		
		if (cnt == 3) { // 궁수3명 됨
			int removeResult = 0;
			
			/*** deep copy ***/
			ArrayList<Integer[]> enemyClone = new ArrayList<>(); // 원본 적은 건들이지 않음
			for(Integer[] x: enemy) {
				enemyClone.add(x.clone());
			}
		
			while (!enemyClone.isEmpty()) {
				List<Integer> removeIdx = new ArrayList<>();
				for (int c = 0; c < m; c++) { // 열돌면서 궁수 있으면 적 없애기
					int minCol = 0;
					int minIndex = -1;
					int distMin = 100;
					if (isArcher[c]) { // archer 행은 n, 열은 c
						for (int i = 0; i < enemyClone.size(); i++) {// 적 한명씩 확인
							int dist = Math.abs(enemyClone.get(i)[0] - n) + Math.abs(enemyClone.get(i)[1] - c);
							if (dist <= d && distMin >= dist) { // 공격할 수 있고, 더 거리 짧으면 update
								if (distMin == dist) { // 같으면 왼쪽에 있는거로 지정
									if (minCol > enemyClone.get(i)[1]) {
										minIndex = i; 
										minCol = enemyClone.get(i)[1]; 
										distMin = dist;
									} else { // 현재 더 오른쪽이면 아무것도 안함
										continue;
									}
								}else {
									distMin = dist;
									minIndex = i; // 지워야 되니까 저장
									minCol = enemyClone.get(i)[1];// 기억해두기 위해 열 저장
								}
							}
						}
						if(!removeIdx.contains(minIndex)) removeIdx.add(minIndex); // 중복 넣으면 안됨 셋으로하면 인덱스 없음
					}
				}
				Collections.sort(removeIdx,Collections.reverseOrder()); // 내림차순 정렬해야 인덱스 이슈 안생김
				
				// 턴: 궁수가 지워야할 애들 지우기
				while(removeIdx.size()>0) { 
					int rmv = removeIdx.get(0);
					removeIdx.remove(0);
					if(rmv == -1) continue; // 공격안함
					enemyClone.remove(rmv);
					removeResult++;
					if(enemyClone.size() == 0 || removeResult == eNum) {
						result = Math.max(result, removeResult);
						return;
					}
				}
				
				int originSize = enemyClone.size();
				int i =0;
				// 열 다 돌았으면
				while(originSize-->0) {
					int nextRow = enemyClone.get(i)[0] + 1;
					if (nextRow >= n) { // 적이 내려가다 성을 만나면 제거됨
						enemyClone.remove(i);
						if(enemyClone.size() == 0) {
							result = Math.max(result, removeResult);
							return;
						}
						continue;
					}else {
						enemyClone.get(i)[0]++; // 성안나면 내려옴
						i++;
					}
					
				}
			}
			result = Math.max(result, removeResult);
			return;
		}

		for (int i = idx; i < m; i++) { // 조합으로 궁수 3명 뽑기
			if (isArcher[i]) continue;
			isArcher[i] = true;
			recur(i + 1, cnt + 1);
			isArcher[i] = false;
		}
	}
}

