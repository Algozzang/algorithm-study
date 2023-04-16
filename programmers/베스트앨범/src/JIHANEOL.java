import java.util.*;

public class JIHANEOL {
	public static void main(String[] args) {
		String[] genres = new String[] { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = new int[] { 500, 600, 150, 800, 2500 };
		Solution s1 = new Solution();
		if (Arrays.equals(new int[] {4,1,3,0}, s1.solution(genres, plays))) {
			System.out.println("통과");
		} else {
			System.out.println("땡");
		}
	}
}
class Album{
	Map<String, Music> map = new HashMap();
}
class Music implements Comparable<Music>{
	
	int total;
	List<int[]> list = new ArrayList(); // idx,plays..
	public Music(int total) {
		this.total = total;
	}
	@Override
	public int compareTo(Music o) {
		return  o.total- this.total ;
	}
	
}
class Solution {
	public int[] solution(String[] genres, int[] plays) {
		List<Integer> answer = new ArrayList();
		Album album = new Album();
		Map<String, Music> map = album.map;
		for(int i=0; i<genres.length; i++){
			if(map.containsKey(genres[i])) {
				Music m = map.get(genres[i]);
				m.list.add(new int[] {i,plays[i]});
				m.total+=plays[i];
			}else {// 없다면
			   map.put(genres[i], new Music(plays[i]));
			   Music m = map.get(genres[i]);
				m.list.add(new int[] {i,plays[i]});
			}
        }
		
		// 장르 선택하기
		
		 List<Music> m = new ArrayList(map.values());
		Collections.sort(m);
		for(int i=0; i<m.size(); i++) {
			Music mu = m.get(i);
			List<int[]> l = mu.list;
			
			l.sort((a,b)->{
				return b[1]-a[1]==0?a[0]-b[0]:b[1]-a[1];
			});
			
			if(l.size()==1) {
				answer.add(l.get(0)[0]);
			}else {
				answer.add(l.get(0)[0]);
				answer.add(l.get(1)[0]);
			}
		}
		System.out.println(answer);
		
		
		return new int[] {0};
	}
}