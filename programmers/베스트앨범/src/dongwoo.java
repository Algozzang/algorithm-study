import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class dongwoo {
	public static void main(String[] args) {
		String[] genres = new String[] { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = new int[] { 500, 600, 150, 800, 2500 };
		Solution s1 = new Solution();
		if (Arrays.equals(new int[] { 4, 1, 3, 0 }, s1.solution(genres, plays))) {
			System.out.println("통과");
		} else {
			System.out.println("땡");
		}
	}
}

class Solution {
	public int[] solution(String[] genres, int[] plays) {
		List<Integer> answer = new ArrayList<>();
		Map<String, Music> musics = new HashMap<>();
		for (int i = 0; i < plays.length; i++) {
			if (!musics.containsKey(genres[i])) {
				musics.put(genres[i], new Music());
			}
			musics.get(genres[i]).totalPlay += plays[i];
			musics.get(genres[i]).pq.add(new int[] { plays[i], i });
		}
		PriorityQueue<Music> pq = new PriorityQueue<>((o1, o2) -> o2.totalPlay - o1.totalPlay);
		for (String genre : musics.keySet()) {
			pq.add(musics.get(genre));
		}
		while (!pq.isEmpty()) {
			Music now = pq.poll();
			for (int i = 0; i < 2; i++) {
				if (!now.pq.isEmpty()) {
					int[] song = now.pq.poll();
					answer.add(song[1]);
				}
			}
		}
		return answer.stream().mapToInt(i->i).toArray();
	}
}

class Music {
	int totalPlay;
	PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] == o1[0] ? o1[1] - o2[1] : o2[0] - o1[0]); // 0:
																												// plays,
																												// 1:number

}