import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class dongwoo {
	static int[] dx = new int[] { 0, 1, 0, -1 };
	static int[] dy = new int[] { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<Tuple, List<Tuple>> switches = new HashMap<>();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[N][N];
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int[] from = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1 };
			int[] to = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1 };
			if (!switches.containsKey(new Tuple(from))) {
				switches.put(new Tuple(from), new ArrayList<>());
			}
			switches.get(new Tuple(from)).add(new Tuple(to));
		}
		Queue<Tuple> q = new ArrayDeque<>();
		q.add(new Tuple(new int[] { 0, 0 }));
		visited[0][0] = true;
		map[0][0] = true;
		int cnt = 1;
		
		while (!q.isEmpty()) {
			Tuple now = q.poll();
			
			// 현재 위치에서 켤 수 있는 불 다 켜기, 새로 불켠곳이 visited와 인접해있으면 queue에 추가 
			for (Tuple pos : switches.getOrDefault(now, new ArrayList<>())) {
				int x = pos.tuple[0];
				int y = pos.tuple[1];
				if (!map[x][y]) {
					cnt++;
				}
				map[x][y] = true;
				for (int i = 0; i < 4; i++) {
					int newX = x + dx[i];
					int newY = y + dy[i];
					boolean val;
					try {
						val = map[newX][newY];
					} catch (Exception e) {
						continue;
					}
					if (visited[newX][newY] && !visited[x][y]) {
						q.add(new Tuple(new int[] { x, y }));
						visited[x][y] = true;
						break;
					}
				}
			}
			// bfs로 주변 탐색하기. 
			for (int i = 0; i < 4; i++) {
				int x = now.tuple[0];
				int y = now.tuple[1];
				int newX = x + dx[i];
				int newY = y + dy[i];
				boolean val;
				try {
					val = map[newX][newY];
				} catch (Exception e) {
					continue;
				}
				if (visited[newX][newY] || !val) {
					continue;
				}
				q.add(new Tuple(new int[] { newX, newY }));
				visited[newX][newY] = true;

			}
		}
		System.out.println(cnt);
	}
}

class Tuple {
	int[] tuple;

	public Tuple(int[] list) {
		tuple = list;
	}

	@Override
	public boolean equals(Object obj) {
		return Arrays.equals(tuple, ((Tuple) obj).tuple);
	}
	
	@Override
	public int hashCode() {
		return 100*tuple[0]+tuple[1];
	}

}