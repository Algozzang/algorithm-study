import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Seonghun {
 
    static int T;
    static int N, M, K;
    static List<Microbe>[][] grid;
    static int[][] direct = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
 
    static class Microbe {
        int cnt; // 미생물 수
        int dir; // 이동 방향
 
        Microbe(int cnt, int dir) {
            this.cnt = cnt;
            this.dir = dir;
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
 
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            // initialize
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            grid = getGrid();
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                grid[x][y].add(new Microbe(cnt, dir));
            }
 
            // solution
            while (M-- > 0) {
                List<Microbe>[][] newGrid = getGrid();
                // 군집 이동
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        for (Microbe m : grid[x][y]) {
                            int dx = x + direct[m.dir][0];
                            int dy = y + direct[m.dir][1];
                            // 테두리로 이동하면
                            if (dx == 0 || dx == N - 1 || dy == 0 || dy == N - 1) {
                                m.cnt /= 2;
                                m.dir = (m.dir % 2 == 1) ? m.dir + 1 : m.dir - 1;
                            }
                            newGrid[dx][dy].add(m);
                        }
                    }
                }
                // 군집 합치기
                grid = getGrid();
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        if (newGrid[x][y].size() == 1) {
                            grid[x][y].add(newGrid[x][y].get(0));
                        } else if (newGrid[x][y].size() >= 2) {
                            int sum = 0; // 미생물 수의 합
                            int max = 0; // 미생물 수의 최댓값
                            int dir = -1; // 미생물 수가 가장 많은 군집의 방향
                            for (Microbe m : newGrid[x][y]) {
                                sum += m.cnt;
                                if (max < m.cnt) {
                                    max = m.cnt;
                                    dir = m.dir;
                                }
                            }
                            grid[x][y].add(new Microbe(sum, dir));
                        }
                    }
                }
            }
 
            // 총 미생물 수 계산
            int answer = 0;
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (!grid[x][y].isEmpty()) {
                        answer += grid[x][y].get(0).cnt;
                    }
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
 
    // 새로운 미생물 그리드를 생성하여 반환
    static List<Microbe>[][] getGrid() {
        List<Microbe>[][] grid = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }
        return grid;
    }
 
}