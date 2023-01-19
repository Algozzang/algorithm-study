import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
 
    static int answer;
    static int T, N, L;
    static int Ti, Ki; // 맛 점수, 칼로리
    static int[] hamburger; // 칼로리(인덱스), 맛 점수(값)
 
    public static void main(String[] args) throws IOException {
 
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
 
        for (int tc = 1; tc <= T; tc++) {
            // initialize
            N = sc.nextInt();
            L = sc.nextInt();
            hamburger = new int[L + 1];
 
            // solution
            for (int i = 0; i < N; i++) {
                Ti = sc.nextInt();
                Ki = sc.nextInt();
                for (int j = L; j >= Ki; j--) 
                    hamburger[j] = Math.max(hamburger[j], hamburger[j - Ki] + Ti);
            }
            answer = Arrays.stream(hamburger).max().getAsInt(); 
             
            // result
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
 
}