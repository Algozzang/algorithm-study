import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Minju {
// 1일때 그냥 탈출말고 밑에 입력값받고 탈출하자!!
    public static void main(String[] args) throws Exception {
        PriorityQueue<Long> pq = new PriorityQueue<>((a, b) -> Long.compare(a, b));
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        Long result = 0L;
        int N =0;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            pq.clear();
            N = Integer.parseInt(br.readLine());
            result = 1L;
            // 입력받기
            if (N == 1) {
                sb.append("1\n");
                br.readLine();
                continue;
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                pq.add(Long.parseLong(st.nextToken()));
            }
            // pq에서 빼서 곱해주고 넣고 곱해주고 넣고..
            while (!pq.isEmpty()) {
                Long a = pq.poll();
                Long b = pq.poll();
                Long mul = (a * b);
                result *= (mul)%1_000_000_007 ;
                result %= 1_000_000_007;
                if (pq.size() == 0){
                    break;
                }
                pq.add(mul); // pq는 순위있으니까 그대로 넣어주기
            }
            sb.append(result+"\n");
        }
        System.out.println(sb);
    }

}
