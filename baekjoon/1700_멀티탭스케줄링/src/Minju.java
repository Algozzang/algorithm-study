import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Minju {
    // 우선순위큐 인덱스 아이디어 얻고 풂
    static PriorityQueue<Integer>[] count; // 각 기기마다 인덱스 저장할 배열
    static int min, seq[];
    static List<Integer> power =new ArrayList<>(); // 현재 콘센트 정보

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /* input */
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());//구멍개수
        int k= Integer.parseInt(st.nextToken());
        seq = new int[k]; // 순서 입력받을 배열
        count = new PriorityQueue[k+1];

        for(int i=0;i<=k;i++){
            count[i] = new PriorityQueue<>();
        }

        st = new StringTokenizer(br.readLine());

        int startRemember = 0; //초기상태 지정해주고 시작할 인덱스

        for(int i=0;i<k;i++){
            seq[i] = Integer.parseInt(st.nextToken()); //입력받은 기기번호

            // 처음 초기상태 꽂기
            if(power.size()<n){
                if(power.contains(seq[i])) continue; // 리스트라 이미 있다면 담 순서로 넘김
                power.add(seq[i]); // 현재 콘센트에 꽂기
                startRemember = i;
            }else { // 콘센트 다 채웠으면
                count[seq[i]].add(i); // 각 번호마다 인덱스 저장
            }
        }

        for(int i=startRemember+1;i<k;i++){
            int cur = seq[i];
            if(power.contains(cur)) {
                //다음 순서가 이미 꽂혀져 있다면 pq에서 인덱스 삭제해주고 넘어가기
                count[cur].poll();
                continue;
            }
            // 플러그 빼기 규칙
            // 1. 현재 꽂혀있는 애들 중 pq사이즈가 0인 애 먼저
            // 2. 사이즈 다 있다면 pq들어간 idx 젤 느린애 먼저
            int pollIdx=-1, remove = -1;
            boolean sizeZero = false;
            for(int j =0;j<n;j++){ //power 콘센트 돌면서
                int curNum = power.get(j);
                if(count[curNum].size()==0){    //1. 사이즈 0인애 있으면
                    power.remove(j);
                    sizeZero = true;
                    break;
                }else{ // 2.
                    if(pollIdx <count[curNum].peek()) {
                        pollIdx = count[curNum].peek();
                        remove = curNum;
                    }
                }
            }

            min++; // 플러그 하나 뽑음
            count[cur].poll(); // 이제 들어갈애 pq에서 인덱스 없애주기
            power.add(cur); //현재 값 더해주기
            if(sizeZero)  {// 이미 사이즈 0으로 뽑았으니 삭제안하려고
                continue;
            }
            power.remove(Integer.valueOf(remove)); //삭제할 값 삭제하고
        }
        System.out.println(min);
    }
}