import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JIHANEOL {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>(); // 오름차순정렬 key 값으로
            int n = Integer.parseInt(br.readLine());

            while(n-- > 0) {
                st = new StringTokenizer(br.readLine());
                String str = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                switch(str) {
                    case "I" :// 넣기
                        treeMap.put(num, treeMap.getOrDefault(num, 0) + 1); // 값이 없으면 1 있으면 get(num)+1; //중복 확인
                        break;
                    case "D" : // 빼기
                        if(treeMap.isEmpty()) break;
                        if(num == -1) { // 최소값
                            int minKey = treeMap.firstKey();
                            if(treeMap.get(minKey) == 1) {
                                treeMap.remove(minKey);
                            }else {
                                treeMap.put(minKey, treeMap.get(minKey) - 1);
                            }
                        }else { // 최댓값
                            int maxKey = treeMap.lastKey();
                            if(treeMap.get(maxKey) == 1) { 
                                treeMap.remove(maxKey);
                            }else { 
                                treeMap.put(maxKey, treeMap.get(maxKey) - 1);
                            }
                        }
                        break;
                }
            }

            if(treeMap.isEmpty()) {
                sb.append("EMPTY\n");
            }else {
                sb.append(treeMap.lastKey() + " " + treeMap.firstKey() + "\n");
            }
        }
        System.out.println(sb.toString());
    }
}