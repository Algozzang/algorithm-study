import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dongwoo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            short[][] map = new short[10001][10001];
            int N = Integer.parseInt(br.readLine());
            int max = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                for(int r = y-5; r<= y+5; r++) {
                    for (int c = x-5; c <= x+5; c++) {
                        int val;
                        try {
                            val = map[r][c];
                        } catch (Exception e) {
                            continue;
                        }
                        map[r][c] ++;
                        max = Math.max(max, map[r][c]);
                    }
                }
            }
            sb.append(max).append("\n");
        }
        System.out.println(sb);
    }
}