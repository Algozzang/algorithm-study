package SWEA;

import java.util.*;
import java.io.*;

public class SWEA4193_수영대회결승전 {
    private static class Node {
        int r, c, time;

        public Node(int r, int c, int time){
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){

            sb.append("#").append(tc).append(" ");

            int n = Integer.parseInt(br.readLine());

            int[][] map = new int[n][n];
            boolean[][] visit = new boolean[n][n];

            for(int i=0; i<n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            //start point
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());


            //end point
            st = new StringTokenizer(br.readLine());
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());

            int[] drow = new int[]{-1, 1, 0, 0};
            int[] dcol = new int[]{0, 0, -1, 1};

            Queue<Node> pq = new ArrayDeque<>();
            pq.add(new Node(sr, sc, 0));

            boolean win = false;

            while(!pq.isEmpty()){

                Node cur = pq.poll();

                if(cur.r == er && cur.c == ec){
                    sb.append(cur.time).append("\n");
                    win = true;
                    break;
                }

                for(int d=0; d<4; d++){
                    int nr = cur.r + drow[d];
                    int nc = cur.c + dcol[d];


                    if(!isIn(nr, nc, n)) continue;
                    else if(visit[nr][nc]) continue;
                    else if(map[nr][nc] == 1) continue;     //장애물 있는 경우
                    else if(map[nr][nc] ==  2){ //소용돌이 있을경우
                        if((cur.time+1)%3 == 0){    //토네이도 없을 때는 갈 수 있다
                            pq.add(new Node(nr, nc, cur.time+1));
                            visit[nr][nc] = true;
                        }
                        else{
                            pq.add(new Node(cur.r, cur.c, cur.time+1)); //토네이도가 있으면 제자리에서 시간만 증가
                        }
                    }
                    else if(map[nr][nc] == 0){      //빈칸은 그냥 갈 수 있음
                        pq.add(new Node(nr, nc, cur.time+1));
                        visit[nr][nc] = true;
                    }

                }

            }
            if(!win){
                sb.append(-1).append("\n");
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }

    private static boolean isIn(int r, int c, int n){
        if(r<0 || c<0 || r>=n || c>= n) return false;
        return true;
    }

}
