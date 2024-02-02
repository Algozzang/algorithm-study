package BOJ;

import java.io.*;
import java.util.*;

public class BJ11967_불켜기 {

    private static int n;
    private static Map<Integer, ArrayList<int[]>> roomMap;
    private static int[] drow = new int[]{-1, 1, 0, 0};
    private static int[] dcol = new int[]{0, 0, -1, 1};
    private static boolean[][] light;
    private static boolean[][] visit;
    private static int lightCnt = 1;

    private static class Room{
        int r, c;

        public Room(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        roomMap = new HashMap<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());   //방 위치
            int c = Integer.parseInt(st.nextToken());
            int sr = Integer.parseInt(st.nextToken());  //스위치 위치
            int sc = Integer.parseInt(st.nextToken());

            int roomNum = n*(r-1) + c;

            try{
                roomMap.get(roomNum).add(new int[]{sr, sc});
            } catch(Exception e){
                roomMap.put(roomNum, new ArrayList<>());
                roomMap.get(roomNum).add(new int[]{sr, sc});
            }
        }

        light = new boolean[n+1][n+1];
        visit = new boolean[n+1][n+1];
        light[1][1] = true;
        int lightBefore = 0;

        do{
            lightBefore = lightCnt;
            lightCnt = bfs();
        } while(lightBefore != lightCnt);

        System.out.println(lightCnt);
    }

    private static int bfs(){

        for(boolean[] arr : visit){Arrays.fill(arr, false);}
        visit[1][1] = true;

        int lightUpdate = lightCnt;

        Queue<Room> q = new ArrayDeque<>();
        q.add(new Room(1, 1));

        while(!q.isEmpty()){
            Room cur = q.poll();
            int roomNum = n*(cur.r-1) + cur.c;

            //현재 도착한 방에서 불켜기
            if(roomMap.containsKey(roomNum)){
                for(int[] arr : roomMap.get(roomNum)){
                    int r = arr[0], c = arr[1];
                    if(!light[r][c]){   //불켜기
                        light[r][c] = true;
                        lightUpdate++;
                    }
                }
            }

            for(int d=0; d<4; d++){
                int nr = cur.r + drow[d];
                int nc = cur.c + dcol[d];

                try{
                    //방문하지 않았고 불이 켜져있으면 갈 수 있음
                    if(!visit[nr][nc] && light[nr][nc]){
                        visit[nr][nc] = true;
                        q.add(new Room(nr, nc));
                    }
                } catch(Exception e){ continue; }
            }
        }
        return lightUpdate;
    }
}
