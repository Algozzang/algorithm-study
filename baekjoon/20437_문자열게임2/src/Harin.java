package ProgrammingProblems.BOJ;

import java.io.*;
import java.util.*;

public class Harin {
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("/Users/harin/IdeaProjects/ProgrammingProblems/src/main/java/ProgrammingProblems/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        for(int t=0; t<T; t++){
            String str = br.readLine();
            int k = Integer.parseInt(br.readLine());
            sb.append(findString(str, k)).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }

    public static String findString(String str, int k){

        int min = Integer.MAX_VALUE;
        int max = 0;

        HashMap<String, Integer> alphabet = new HashMap<>();
        String abc = "abcdefghijklmnopqrstuvwxyz";

        for(int i=0; i<abc.length(); i++){
            alphabet.put(String.valueOf(abc.charAt(i)), 0);
        }

        for(int i=0; i<str.length(); i++){
            String s = String.valueOf(str.charAt(i));
            alphabet.put(s, alphabet.get(s)+1);
        }

        for(int i=0; i<str.length(); i++){
            String s = String.valueOf(str.charAt(i));

            if(alphabet.get(s) < k) continue;

            int cnt = 0;
            for(int j=i; j<str.length(); j++){
                if(str.charAt(i) == str.charAt(j)){
                    if(++cnt == k){
                        min = Math.min(min, j-i+1);
                        max = Math.max(max, j-i+1);
                        break;
                    }
                }
            }
        }

        if(min == Integer.MAX_VALUE || max == 0) return "-1";

        StringBuffer sb = new StringBuffer();

        return sb.append(min).append(" ").append(max).toString();
    }

}
