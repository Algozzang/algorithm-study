import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
 
public class Solution {
 
    static int T, N, answer;
    static int[] queenPos;
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            // initialize
            answer = 0;
            N = Integer.parseInt(br.readLine());
 
            // solution
            nQueen(0, new ArrayList<>());
 
            // output
            System.out.printf("#%d %d\n", tc, answer);
        }
         
    }
 
    static void nQueen(int x, List<Entry<Integer, Integer>> points) {
 
        if (x == N) {
            answer++;
            return;
        }
 
        for (int y = 0; y < N; y++) {
            boolean positionable = true;
            for (int i = 0; i < points.size(); i++) {
                int px = points.get(i).getKey();
                int py = points.get(i).getValue();
                if (py == y || Math.abs(px - x) == Math.abs(py - y)) {
                    positionable = false;
                    break;
                }
            }
             
            if (positionable) {
                Entry<Integer, Integer> point = new SimpleEntry(x, y);
                points.add(point);
                nQueen(x + 1, points);
                points.remove(point);
            }
        }
         
    }
 
}