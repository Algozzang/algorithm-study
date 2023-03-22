package AlgoJJANG;

import java.io.*;

public class Harin {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        /*문자열 뒤에 A를 추가
        * 또는 문자열을 뒤집고 뒤에 B를 추가 = 문자열 앞에 B를 추가*/
        String S = br.readLine();
        String T = br.readLine();

        sb = new StringBuffer(T);

        //거꾸로 접근
        while(sb.length() != S.length()) {
            if (sb.charAt(sb.length() - 1) == 'A') {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.deleteCharAt(sb.length() - 1);
                sb.reverse();
            }
        }

        String answer = (S.equals(sb.toString())) ? "1" : "0";
        bw.write(answer);
        bw.close();
    }
}
