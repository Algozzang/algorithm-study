import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Minju {
    // 접근
    // 1. 우선순위 큐 두개 만들어 둘다 삽입삭제 => 시간초과
    // 2. 리스트 정렬로 0, length-1 삭제 => 시간초과
    // 3. TreeMap firstKey() = O(logN)
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /* input */
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int tc =0;tc<t;tc++) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for(int i=0;i<k;i++) {
                st = new StringTokenizer(br.readLine());

                if (st.nextToken().equals("I")) { // insert
                    int cur = Integer.parseInt(st.nextToken());
                    map.put(cur, map.getOrDefault(cur, 0)+1);
                } else { // delete
                    if (st.nextToken().equals("-1")) { // 최소 삭제
                        if (!map.isEmpty()) {
                            int num = map.get(map.firstKey());
                            if(num == 1){ // 지금 한개있으면 지움
                                map.remove(map.firstKey());
                            }else {// 여러개 있으면
                                map.put(map.firstKey(), num-1); // 개수 줄이기
                            }
                        }
                    } else { // 최대 삭제
                        if (!map.isEmpty()) {
                            int num = map.get(map.lastKey());
                            if (num == 1) { // 지금 한개있으면 지움
                                map.remove(map.lastKey());
                            } else {// 여러개 있으면
                                map.put(map.lastKey(), num - 1); // 개수 줄이기
                            }
                        }
                    }
                }
            }

            if(map.isEmpty()) {
                sb.append("EMPTY"+"\n");
            }else{
                sb.append(map.lastKey()+" "+map.firstKey()+"\n");
            }
        }
        System.out.println(sb);
    }
}