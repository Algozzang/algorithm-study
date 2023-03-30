import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Minju {

    static List<Ball> list = new ArrayList<>(); // 현재 볼들을 저장
    static List<Ball>[][] map; // 맵에서 각 안에 볼리스트들을 저장할 것임 (한 지역에 모인애들 체크하기위해)
    static int n, m, k, result;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new ArrayList[n][n]; //n+1로 했다가 0행생각못하고..
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayList<Ball>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            Ball ball = new Ball(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            list.add(ball);

            map[ball.x][ball.y].add(ball);
        }

        game(0);

        System.out.println(result);

    }

    private static void game(int gameCnt) { // 파이어볼 이동 게임 시작

        if (gameCnt == k) { // 기저조건
            for (int i = 0; i < list.size(); i++) {
                //System.out.println("여기안돌아?");
                result += list.get(i).m; //질량 합 구하기
            }
            return;
        }

        // 파이어볼 개수만큼 이동하기
        for (int i = 0; i < list.size(); i++) {
            move(list.get(i), i);
        }

        // 같은 자리에 있는 파이어볼 체크
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].size() > 1) { // 2개이상이라면

                    int sumM = 0;
                    int sumS = 0;
                    int initialDir = map[i][j].get(0).d;
                    boolean sameDir = true;
                    for (int h = 0; h < map[i][j].size(); h++) {
                        sumM += map[i][j].get(h).m;
                        sumS += map[i][j].get(h).s;
                        list.remove(map[i][j].get(h)); // 지워주고 리스트에선

                        if ((initialDir&1) != (map[i][j].get(h).d &1)) sameDir = false; // 방향 홀짝 섞여있으면
                    }
                    sumM /= 5;
                    sumS /= map[i][j].size();

                    map[i][j].clear(); // 질량 0 볼 소멸

                    if(sumM > 0) {
                        for (int p = 0; p <= 7; p += 2) { // 0246 1357 설정해서 현재 볼 리스트 업데이트
                            if (sameDir) { //모두 같은 홀이거나 짝이면
                                list.add(new Ball(i, j, sumM, sumS, p));
                            } else {
                                list.add(new Ball(i, j, sumM, sumS, p + 1));
                            }
                        }
                    }
                }
            }
        }

        // k될때까지 반복
        game(gameCnt + 1);

    }


    private static void move(Ball ball, int idx) { // 현재 모든 볼 이동
        int x = ball.x;
        int y = ball.y;
        int s = ball.s;
        // n*n 범위 값을 넘어갈수도 있으니 속력 나머지값만 + 행열연결된거 처리

        ball.y = (y + n + dy[ball.d] * (s%n)) % n;
        ball.x = (x +n+ dx[ball.d] * (s%n )) % n;

        map[ball.x][ball.y].add(ball); // 이동한 위치에서 다시 add
        //update
        list.get(idx).x = ball.x;
        list.get(idx).y = ball.y;
    }

}

class Ball {

    int x;
    int y;
    int m; // 질량
    int s; // 속력
    int d; // 방향

    public Ball(int x, int y, int m, int s, int d) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.s = s;
        this.d = d;

    }
}
