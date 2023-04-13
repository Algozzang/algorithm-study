package BOJ;

import java.io.*;
import java.util.*;

public class BJ18405_경쟁적전염 {
    private static int n;
    private static class Node implements Comparable<Node>{
        int r, c, virus, time;

        public Node(int r, int c, int virus, int time) {
            this.r = r;
            this.c = c;
            this.virus = virus;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time == o.time ? this.virus - o.virus : this.time - o.time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   //map 크기
        int k = Integer.parseInt(st.nextToken());   //바이러스 종류 수

        int[][] map = new int[n+1][n+1];

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i=1; i<n+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<n+1; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num != 0){
                    pq.add(new Node(i, j, num, 0));
                }
                map[i][j] = num;
            }
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        //입력 끝

        int[] drow = new int[]{-1, 1, 0, 0};
        int[] dcol = new int[]{0, 0, -1, 1};

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(int d=0; d<4; d++){
                int nr = cur.r + drow[d];
                int nc = cur.c + dcol[d];

                if(!isIn(nr, nc) || map[nr][nc] != 0 || cur.time+1 == s+1) continue;

                map[nr][nc] = cur.virus;
                pq.add(new Node(nr, nc, cur.virus, cur.time+1));
            }

        }

        sb.append(map[x][y]);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }

    private static boolean isIn(int r, int c){
        if(r<1 || c<1 || r>n || c>n) return false;
        return true;
    }
}
