import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Song {
	int num;
	int play;
	String genre;

	Song(int num, int play, String genre) {
		this.num = num;
		this.play = play;
		this.genre = genre;
	}
}

class Solution {
	public int[] solution(String[] genres, int[] plays) {
		int N = genres.length;
		Map<String, Integer> genrePlay = new HashMap<>(); // 장르별 총 재생 수 저장
		List<Song> songs = new ArrayList<>(); // 각 노래들 정보 저장
		List<Integer> album = new ArrayList<>(); // 앨범에 수록되는 노래 순서

		// 앨범 정보 등록
		for (int i = 0; i < N; i++) {
			genrePlay.put(genres[i], plays[i] + genrePlay.getOrDefault(genres[i], 0));
			songs.add(new Song(i, plays[i], genres[i]));
		}

		// 노래 수록 기준 정렬
		Collections.sort(songs,
				(o1, o2) -> genrePlay.get(o1.genre) != genrePlay.get(o2.genre)
						? genrePlay.get(o2.genre) - genrePlay.get(o1.genre)
						: (o1.play != o2.play) ? o2.play - o1.play : o1.num - o2.num);

		// 각 장르별로 2개의 노래만 수록
		for (int i = 0; i < N; i++) {
			if (i < 2 || !songs.get(i - 2).genre.equals(songs.get(i - 1).genre)
					|| !songs.get(i - 1).genre.equals(songs.get(i).genre)) {
				album.add(songs.get(i).num);
			}
		}

		// 결과 배열 만들고 반환
		int[] answer = new int[album.size()];
		for (int i = 0; i < album.size(); i++) {
			answer[i] = album.get(i);
		}
		return answer;
	}
}

public class Seonghun {
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