import java.util.Scanner;
import java.util.stream.Stream;
 
public class Solution {
 
    static long answer;
    static int T;
    static String word;
 
    public static void main(String[] args) {
 
        Scanner sc = new Scanner(System.in);
 
        T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            // initialize
            answer = 1;
            word = sc.next();
 
            // solution
            int len = word.length();
            for (int i = 0; i < len; i++) {
                // 각 문자에 대해서 좌우 문자를 붙인 부분 문자열 생성
                String substr = word.substring(Math.max(0, i - 1), Math.min(len, i + 2));
                answer *= Stream.of(substr.split("")).distinct().count();
                answer %= 1000000007;
            }
 
            // result
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}