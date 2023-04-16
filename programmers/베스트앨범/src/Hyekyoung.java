import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hyekyoung {
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

class Song implements Comparable<Song>{
	int play, num;

	public Song(int play, int num) {
		super();
		this.play = play;
		this.num = num;
	}

	@Override
	public int compareTo(Song o) {
		return o.play - this.play;
	}
}

class Genre implements Comparable<Genre>{
	String genre;
	int playCnt;
	
	public Genre(String genre, int playCnt) {
		super();
		this.genre = genre;
		this.playCnt = playCnt;
	}

	@Override
	public int compareTo(Genre o) {
		return o.playCnt - this.playCnt;
	}
}

class Solution {
	public int[] solution(String[] genres, int[] plays) {
		
		Map<String, List<Song>> genre = new HashMap<>();
		Map<String, Integer> playCnt = new HashMap<>();
		List<Genre> gList = new ArrayList<>();
		List<Integer> ans = new ArrayList<>();
		int size = genres.length;
		
		for(int i=0; i<size; i++) {
			if(!playCnt.containsKey(genres[i])) {
				genre.put(genres[i], new ArrayList<>());
				playCnt.put(genres[i], 0);
			}
			genre.get(genres[i]).add(new Song(plays[i], i));
			playCnt.put(genres[i], playCnt.get(genres[i])+plays[i]);
		}
		for (String s : playCnt.keySet()) {
			gList.add(new Genre(s, playCnt.get(s)));
		}
		
		Collections.sort(gList);
		for (Genre g : gList) {
			Collections.sort(genre.get(g.genre));
			ans.add(genre.get(g.genre).get(0).num);
			if(genre.get(g.genre).size()>1) {
				ans.add(genre.get(g.genre).get(1).num);
			}
		}
		
		int[] answer = new int[ans.size()];
		for(int i=0; i<ans.size(); i++) answer[i] = ans.get(i);
		return answer;
	}
}