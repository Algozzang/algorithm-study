package BOJ;

import java.util.*;
import java.io.*;

public class BJ2056_작업 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<Integer, ArrayList<Integer>> adjList = new HashMap<>(); //인접리스트
        for(int i=1; i<=n; i++){
            adjList.put(i, new ArrayList<>());
        }

        int[] degree = new int[n+1];    //진입차수
        int[] times = new int[n+1];     //작업시간
        int[] completeTimes = new int[n+1];     //작업이 완료되는데 걸린 시간

        for(int i=1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());   //작업에 걸리는 시간
            int m = Integer.parseInt(st.nextToken());   //이전 작업이 몇개인지
            times[i] = t;
            degree[i] = m;

            if(m > 0){
                for(int j=0; j<m; j++){
                    int vertex = Integer.parseInt(st.nextToken());
                    adjList.get(vertex).add(i);     //내가 끝나고 수행되는 작업들을 저장
                }
            }
        }

        //위상정렬
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=n; i++){
            if(degree[i] == 0) q.add(i);
        }

        while(!q.isEmpty()){

            int cur = q.poll();
            completeTimes[cur] += times[cur];   //이전까지 작업된 시간에 나의 작업시간을 더함

            //인접 정점 처리
            for(int i=0; i<adjList.get(cur).size(); i++) {
                int nextVertex = adjList.get(cur).get(i);
                if (--degree[nextVertex] == 0) q.add(nextVertex);
                completeTimes[nextVertex] = Math.max(completeTimes[nextVertex], completeTimes[cur]);
            }
        }

        int answer = Arrays.stream(completeTimes).max().getAsInt();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}
