package BOJ;

import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class BJ11657_타임머신 {
    
    private static Edge[] edges;
    private static long[] distance;
    private static int n, m;
    private static final int INF = Integer.MAX_VALUE;

    private static class Edge{
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new Edge[m];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(s, e, w);
        }
        //입력 끝

        distance = new long[n+1];

        //무한값으로 초기화
        for(int i=2; i<n+1; i++){
            distance[i] = INF;
        }

        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        if(!bellman()){
            sb.append("-1").append("\n");
            bw.write(sb.toString());
            bw.close();
        }
        else{
            for(int i=2; i<distance.length; i++){
                if(distance[i] == INF) sb.append(-1).append("\n");
                else sb.append(distance[i]).append("\n");
            }
            bw.write(sb.toString());
            bw.close();
        }
    }
    
    private static boolean bellman(){
        //n-1번 만큼 edges 배열을 돌면서 최소 이동 비용으로 갱신
        for(int i=0; i<n; i++){
            for(int j=0; j<edges.length; j++){
                Edge edge = edges[j];
                if(distance[edge.s] != INF && distance[edge.s] + edge.w < distance[edge.e]) {
                    if(i == n-1) return false;
                    distance[edge.e] = distance[edge.s] + edge.w;
                }
            }
        }
        return true;
    }
}
