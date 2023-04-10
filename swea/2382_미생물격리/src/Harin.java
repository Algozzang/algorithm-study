package SWEA;

import java.util.*;
import java.io.*;

public class SWEA2382_미생물격리 {

    private static int[] drow = new int[]{0, -1, 1, 0, 0};
    private static int[] dcol = new int[]{0, 0, 0, -1, 1};
    private static int[] dirReverse = new int[]{0, 2, 1, 4, 3};

    private static class Micro{
        int r, c, cnt, dir;

        public Micro(int r, int c, int cnt, int dir) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        for(int tc=1; tc<=T; tc++){

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());   //셀 개수
            int m = Integer.parseInt(st.nextToken());   //격리 시간
            int k = Integer.parseInt(st.nextToken());   //미생물 군집 개수

            List<Micro> microList = new ArrayList<>();
            List<Micro> microListTemp = new ArrayList<>();

            Map<Integer, List<Micro>> map = new HashMap<>();
            for(int i=0; i<n*n; i++){
                map.put(i, new ArrayList<Micro>());
            }

            for(int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                microList.add(new Micro(r, c, cnt, dir));
            }

            for(int i=0; i<m; i++){     //격리 시간

                //이동때마다 초기화
                for(int j=0; j<n*n; j++){
                    map.get(j).clear();
                }

                for(Micro micro : microList){   //각 군집 이동
                    int nr = micro.r + drow[micro.dir];
                    int nc = micro.c + dcol[micro.dir];

                    if(!isIn(nr, nc, n)) continue;
                    map.get(nr*n + nc).add(new Micro(nr, nc, micro.cnt, micro.dir));    //이동한 위치 셀번호에 저장
                }

                microList.clear();

                //map에서
                for(Map.Entry<Integer, List<Micro>> entry: map.entrySet()){
                    Integer cellNum = entry.getKey();
                    List<Micro> cellList = entry.getValue();

                    //약품이 칠해진 구역인 경우
                    if( (0<= cellNum && cellNum <= n-1) ||
                            (cellNum % n == 0) ||
                            (n*(n-1) <= cellNum && cellNum <= n*n-1) ||
                            (cellNum % n == n -1)){

                        List<Micro> newList = new ArrayList<>();

                        if(!cellList.isEmpty()){
                            for(Micro micro : cellList){
                                if(micro.cnt/2 != 0){
                                    newList.add(new Micro(micro.r, micro.c, micro.cnt/2, dirReverse[micro.dir]));
                                }
                            }
                        }
                        map.put(cellNum, newList);
                    }

                    //여러 미생물 군집이 있는 경우
                    if(cellList.size() > 1){
                        int sum = cellList.stream().map(x -> x.cnt).mapToInt(x->x).sum();
                        int maxCnt = cellList.stream().map(x -> x.cnt).mapToInt(x->x).max().getAsInt();
                        Micro maxCntMicro = cellList.stream().filter(x -> x.cnt == maxCnt).findAny().get();
                        Micro newMicro = new Micro(maxCntMicro.r, maxCntMicro.c, sum, maxCntMicro.dir);
                        List<Micro> newList = new ArrayList<>();
                        newList.add(newMicro);

                        map.put(cellNum, newList);
                    }

                    for(Micro micro : map.get(cellNum)){
                        microList.add(micro);
                    }
                }
            }

            int remainMicroCnt = microList.stream().map(x -> x.cnt).mapToInt(x->x).sum();
            System.out.println("#" + tc + " " + remainMicroCnt);;
        }
    }

    private static boolean isIn(int r, int c, int n){
        if(r<0 || c<0 || r>=n || c>=n) return false;
        return true;
    }
}
