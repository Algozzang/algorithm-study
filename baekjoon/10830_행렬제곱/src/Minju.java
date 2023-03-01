import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Minju {
    static int n;
    static long B;
    static int[][] hang;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        /* input */
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        hang = new int[n][n]; // 기본 행렬

        for(int i =0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0 ; j<n;j++) {
                hang[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //이미 1제곱이니 B하나 빼주기
        B--;
        int[][] hangCur = hang; //출력할 행렬

        // 행렬 1제곱이라 1000나머지연산 하지 않았을 경우를 위해
        if(B==0){
            for(int i =0;i<n;i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(hangCur[i][j] % 1000 + " ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }
        //분할정복
        while(B>0) {
            if((B&1)==1){ //B가 홀수라면
                hangCur = getHang(hangCur,hang);
            }

            hang = getHang(hang,hang);
            // 2의 1승만큼 나누기
            B>>=1;

        }

        for(int i =0;i<n;i++) {
            for(int j=0 ; j<n;j++) {
                sb.append(hangCur[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[][] getHang(int[][] hang1, int[][] hang2) {// 행렬 제곱 만드는 함수

        int[][] newHang = new int[n][n];
        for(int i =0;i<n;i++) {
            for(int j=0 ; j<n;j++) {
                int cur = 0;
                for(int c=0;c<n;c++) {
                    cur += (hang1[i][c] * hang2[c][j]);
                }
                newHang[i][j] = (cur % 1000) ;// 1000나누어주래
            }
        }
        return newHang;
    }

}
