import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//dp알겠는데 어떻게 방향을 저장하지 하다가 3차원배열에 힌트를 얻고 풂
public class Minju {
    /* 중요한 것은..
       1. 대각선으로 놓을 시에도 벽에 긁힌다는 것!!!!
       2. 더하다가 경우의 수가 int형을 넘을 수 있다는 것 3의 900승이니까
       3. 3가지 경우의 수가 있으니 3차원 배열을 쓸 것!
    */

    //전 위치 찾기== 방향 0 -> , 1 오른쪽대각선, 2 아래
    public static int[] dx = {0, -1, -1};
    public static int[] dy = {-1, -1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /* input */
        int n = Integer.parseInt(br.readLine()); // 전깃줄개수

        int[][] map = new int[n][n]; // 맵 입력받을 배열
        long[][][] dp = new long[n][n][3]; // 각 좌표마다 올수있는 경로의 수
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //(0,1)부터 시작
        // 앞에 두줄 (0,1) 빼고 모두 0 이니 그대로두기
        // 첫째 행은 가로 경우밖에 없으니 모두 1 세팅
        for(int i =1;i<n;i++){
            if(map[0][i] == 1) break; // 근데 벽이 있다면 거기서부터 n-1까지 0으로 둬야함
            dp[0][i][0] = 1;
        }

        // dp
        for (int i = 1; i < n; i++) {
            for (int j = 2; j < n; j++) {
                if(map[i][j] == 1) continue; //벽이면 못가요
                for(int dir=0;dir<3;dir++){
                    //이전 좌표 설정
                    int px = i+ dx[dir];
                    int py = j+ dy[dir];
                    if(map[px][py] == 1) continue; // 전 위치가 벽이면 넘어감
                    // 체크할 좌표의 상태마다의 전위치들의 합을 더해줌
                    if(dir == 0) dp[i][j][dir] = dp[px][py][0] + dp[px][py][1];
                    else if(dir == 2) dp[i][j][dir] = dp[px][py][1]+ dp[px][py][2];
                    else { //대각선
                        if(map[i-1][j] ==1 || map[i][j-1] ==1) continue;// 벽 긁히는 것 생각하자
                        dp[i][j][dir] = dp[px][py][0] + dp[px][py][1]+ dp[px][py][2];
                    }
                }
            }
        }

        System.out.println(dp[n-1][n-1][0]+dp[n-1][n-1][1]+dp[n-1][n-1][2]);
    }
}
