import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
 
public class Solution {
 
    static int T;
    static String number;
    static int change;
    static int answer;
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            // input
            st = new StringTokenizer(br.readLine());
            number = st.nextToken();
            change = Integer.parseInt(st.nextToken());
 
            // solution
            answer = 0;
            swapNumber(number, change);
 
            // output
            System.out.printf("#%d %d\n", tc, answer);
        }
 
    }
 
    static void swapNumber(String number, int change) {
 
        if (change == 0) { // 변경 횟수를 모두 사용
            answer = Math.max(answer, Integer.parseInt(number));
            return;
        }
 
        // number을 내림차순으로 정렬한 문자열
        String sortedNumber = Stream.of(number.split("")).sorted(Comparator.reverseOrder())
                .collect(Collectors.joining());
 
        int len = number.length();
        int idx = getBestIndex(number, sortedNumber);
 
        if (idx == -1) { // 현재 이미 최대 상금인 경우
            // 중복된 상금이 없고 남은 변경 횟수가 홀수인 경우, 끝에 두자리 변경
            if (number.length() == Stream.of(number.split("")).distinct().count() && change % 2 == 1)
                number = number.substring(0, len - 2) + number.charAt(len - 1) + number.charAt(len - 2);
            swapNumber(number, 0);
        } else { // 현재 최대 상금이 아닌 경우
            // idx 부터 끝까지 상금 중에서 가장 큰 값
            char maxValue = '0';
            for (char c : number.substring(idx).toCharArray())
                if (c > maxValue)
                    maxValue = c;
            // 가장 큰 값들과 모두 swap하여 결과 비교
            for (int i = idx + 1; i < len; i++)
                if (number.charAt(i) == maxValue)
                    swapNumber(swapIndex(number, idx, i), change - 1);
        }
    }
 
    // 최대 상금이 되기위해 number에서 바뀌어야할 수의 인덱스 반환
    static int getBestIndex(String number, String sortedNumber) {
 
        for (int i = 0; i < number.length(); i++)
            if (number.charAt(i) != sortedNumber.charAt(i))
                return i;
 
        return -1; // 이미 최대 상금인 경우
    }
 
    // 문자열에서 주어진 두 인덱스의 문자 위치를 바꾸어 반환
    static String swapIndex(String str, int i1, int i2) {
        return str.substring(0, i1) + str.charAt(i2) + str.substring(i1 + 1, i2) + str.charAt(i1) + str.substring(i2 + 1);
    }
}