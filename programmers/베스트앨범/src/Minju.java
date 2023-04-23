import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Minju {
	public static void main(String[] args) {
		String[] genres = new String[] { "classic", "Newage", "pop", "classic", "classic", "pop", "Newage"};
		int[] plays = new int[] {500, 1700, 600, 150, 800, 2500, 1500};
		Solution s1 = new Solution();
		if (Arrays.equals(new int[] {1, 6, 5, 2, 4, 0}, s1.solution(genres, plays))) {
			System.out.println("통과");
		} else {
			System.out.println("땡");
		}
	}
}

class Solution {
	public int[] solution(String[] genres, int[] plays) {
		int[] answer = {};
		
		HashMap<String,HashMap<Integer,Integer>> genresMap = new HashMap<>(); // 장르, idx, 재생횟수 담은 맵
		HashMap<String,Integer> genresCnt = new HashMap<>(); // 곡수많은 장르 세기위한 맵
	
		for(int i =0;i<plays.length;i++) {
			String g = genres[i];
			if(genresMap.containsKey(g)) {
				genresMap.get(g).put(i,plays[i]);
				genresCnt.put(g,genresCnt.get(g)+plays[i]);
			}else {
				genresMap.put(g, new HashMap<>());
				genresMap.get(g).put(i,plays[i]);
				genresCnt.put(g,plays[i]);
			}
		}
		
		List<String> keySortList = new ArrayList<>(genresCnt.keySet());
		Collections.sort(keySortList, (a,b)->genresCnt.get(b)-genresCnt.get(a));//list 합 큰 순서로 키 리스트 정렬
		
		ArrayList<Integer> result = new ArrayList<>();
		for(int i =0;i<keySortList.size(); i++) {
			HashMap<Integer,Integer> map = genresMap.get(keySortList.get(i));
			List<Entry<Integer,Integer>> list1 = new ArrayList<Entry<Integer,Integer>>(map.entrySet());
			// 재생횟수대로 내림차순 정렬, 같으면 인덱스 오름차순
			Collections.sort(list1, (a,b)->(b.getValue()==a.getValue())? a.getKey()-b.getKey():b.getValue()-a.getValue()); 
			
			for(int j =0;j<2;j++) { // 상위 2개 뽑아 인덱스 추가
				if(j<list1.size()) {
					result.add(list1.get(j).getKey());
				}
			}
			
		}
	
		answer = new int[result.size()];
		
		for(int i =0;i<result.size(); i++) {
			answer[i] = result.get(i);
		}
	
		return answer;
	}
}