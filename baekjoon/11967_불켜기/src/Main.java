import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 점점 어려워지는 bfs..
public class Main {
	static int n, m, result;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][] visited;
	static boolean[][] light;

	static List<Info> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n + 1][n + 1];
		light = new boolean[n + 1][n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		bfs(1, 1);

		System.out.println(result + 1); // (1,1) 켜진상태 더해주기
	}

	static void bfs(int sx, int sy) {

		Queue<Room> q = new LinkedList<>();

		q.add(new Room(sx, sy));

		while (!q.isEmpty()) {

			Room poll = q.poll();

			int curx = poll.x;
			int cury = poll.y;
			light[curx][cury] = true;
			visited[curx][cury] = true;
		
			// 불 정보담긴 리스트 돌면서 불 키기
			listLoop: for (int j = 0; j < list.size(); j++) {
				int toX = list.get(j).toX; // 불킬 수 있는 좌표x
				int toY = list.get(j).toY; // 불킬 수 있는 좌표y
				
				// 현재위치에서 켜지는 불먼저 키기
				if(curx == list.get(j).fromX && cury == list.get(j).fromY) {
					if (!light[toX][toY]) {
						light[toX][toY] = true;
						result++;
						for (int k = 0; k < 4; k++) {
							int lightNearX = toX + dx[k];
							int lightNearY = toY + dy[k];
							if (lightNearX <= 0 || lightNearY <= 0 || lightNearX > n || lightNearY > n) continue;
							if (visited[lightNearX][lightNearY]) { // 불을 킨 방의 주변이 왔던길이면
								q.add(new Room(toX, toY)); // 큐에 넣기
								list.remove(j--); //리스트에서 삭제
								continue listLoop;
							}
						}
					}
				}
			}
			// 4방 탐색
			for (int i = 0; i < 4; i++) {
				int nx = curx + dx[i];
				int ny = cury + dy[i];

				if (nx <= 0 || ny <= 0 || nx > n || ny > n) continue;
				
				if (visited[nx][ny] || !light[nx][ny]) {
					continue; //갔던 길이거나 불이 안켜져있으면 못감
				}
				q.add(new Room(nx, ny)); //갈수있는길이면 큐에 담기
				visited[nx][ny] = true;
	
		}

	}
	}
}

class Info {
	int fromX;
	int fromY;
	int toX;
	int toY;

	public Info(int fromX, int fromY, int toX, int toY) {
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
	}
}

class Room {
	int x;
	int y;

	public Room(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
