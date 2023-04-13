import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Seonghun {
 
    static int T; // 테스트 케이스 수
    static int N; // 수영장의 크기
    static int[][] pool; // 수영장
    static boolean[][] visited; // 방문 여부
    static int A, B; // 시작 위치
    static int C, D; // 도착 위치
    static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
 
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            // initialize
            N = Integer.parseInt(br.readLine());
            pool = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    pool[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
 
            // solution
            int answer = -1;
            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[] { A, B, 0 });
            visited[A][B] = true;
            while (!q.isEmpty()) {
                int[] p = q.poll();
                int x = p[0];
                int y = p[1];
                int t = p[2];
                if (x == C && y == D) {
                    answer = t;
                    break;
                }
                for (int d = 0; d < 4; d++) {
                    int dx = x + direct[d][0];
                    int dy = y + direct[d][1];
                    if (inRange(dx, dy) && pool[dx][dy] != 1 && !visited[dx][dy]) {
                        if (pool[dx][dy] == 0) {
                            q.add(new int[] { dx, dy, t + 1 });
                            visited[dx][dy] = true;
                        } else { // 소용돌이
                            if (t % 3 == 2) { // 소용돌이 사라짐
                                q.add(new int[] { dx, dy, t + 1 });
                                visited[dx][dy] = true;
                            } else { // 소용돌이가 있음
                                q.add(new int[] { x, y, t + 1 }); // 대기
                            }
                        }
                    }
                }
            }
 
            // result
            System.out.println("#" + tc + " " + answer);
        }
    }
 
    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
 
}
 