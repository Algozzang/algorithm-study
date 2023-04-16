package Programmers;

import java.util.*;

class 베스트앨범 {

    private static class Genre{
        String title;
        int cnt;

        public Genre(String title, int cnt) {
            this.title = title;
            this.cnt = cnt;
        }
    }

    private static class Song{
        int index;
        int cnt;

        public Song(int index, int cnt) {
            this.index = index;
            this.cnt = cnt;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Song>> songMap = new HashMap<>();
        Map<String, Integer> genreMap = new LinkedHashMap<>();

        for(int i=0; i<genres.length; i++){

            String genre = genres[i];
            int songPlay = plays[i];

            Song song = new Song(i, songPlay);

            try{
                songMap.get(genre).add(song);
            } catch(NullPointerException e){
                songMap.put(genre, new ArrayList<Song>());
                songMap.get(genre).add(song);
            }

            try{
                int totalPlay = genreMap.get(genre);
                genreMap.put(genre, totalPlay + songPlay);
            } catch(NullPointerException e){
                genreMap.put(genre, songPlay);
            }
        }

        List<Genre> genreList = new ArrayList<>();

        for(Map.Entry<String, Integer> entry : genreMap.entrySet()){
            String genre = entry.getKey();
            Integer cnt = entry.getValue();

            genreList.add(new Genre(genre, cnt));
        }

        Collections.sort(genreList, (o1, o2) -> Integer.compare(o2.cnt, o1.cnt));

        for(Map.Entry<String, List<Song>> entry : songMap.entrySet()){
            Collections.sort(entry.getValue(), (o1, o2) -> Integer.compare(o2.cnt, o1.cnt));
        }

        List<Integer> answer = new ArrayList<>();

        for(Genre genre : genreList){
            String title = genre.title;
            List<Song> songs = songMap.get(title);

            if(songs.size() > 1){
                answer.add(songs.get(0).index);
                answer.add(songs.get(1).index);
            }
            else{
                answer.add(songs.get(0).index);
            }
        }

        return answer.stream().mapToInt(x->x).toArray();
    }
}
