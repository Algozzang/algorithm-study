import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Minju {

    static int n,m,k;
    static List<Misaeng> list = new ArrayList<>();
    static int[] dx = {0,-1,1,0,0};
    static int[] dy = {0,0,0,-1,1};
    
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        /* input */
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for(int tc=1;tc<=t;tc++) {
            st = new StringTokenizer(br.readLine());
            // init
            int result =0;
            list.clear();
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
        
            for(int i =0;i<k;i++) {
                st = new StringTokenizer(br.readLine());
                list.add(new Misaeng(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())));
            }

            for(int i =0;i<m;i++) {
                move();
            }
            for(int i =0;i<list.size();i++) {
                result += list.get(i).num;
            }
            sb.append("#"+tc+" "+result+"\n");
        }
        System.out.println(sb);
    }

    private static void move() {
     
        for(int i =0;i<list.size(); i++) {

            Misaeng mi = list.get(i);
            int nx = mi.x + dx[mi.dir];
            int ny = mi.y + dy[mi.dir];
            
            if(nx==0 || ny == 0 || nx == n-1 || ny == n-1) {
                // 이동방향 반대
                int dir = mi.dir;
                if(dir==1) dir = 2;
                else if(dir==2) dir = 1;
                else if(dir==3) dir = 4;
                else if(dir==4) dir = 3;
                list.get(i).x = nx;
            	list.get(i).y = ny;
                list.get(i).dir = dir;
                list.get(i).pos = nx*n+ny;
                
                // 미생물 절반 죽
                double num = list.get(i).num;
                num = Math.floor(num/2);
        
                if(num == 0) list.remove(i--); // 미생물 사라짐 
                else list.get(i).num = (int) num;
            }else { // 일반 이동
            	list.get(i).x = nx;
            	list.get(i).y = ny;
            	list.get(i).pos =  nx*n+ny;
      
            }
        }

        // 미생물 여러개 모이는거 처리하기 전에 정렬
        Collections.sort(list);

        // 미생물 하나이상있는거 골라
        for(int i =0; i<list.size()-1; i++) {
            if(list.get(i).pos == list.get(i+1).pos) { //같은 셀이냐?
            	// 개수 합치기
            	list.get(i).num += list.get(i+1).num;
            	list.remove(i+1); // 정렬했으니 더 개수 더작은애 삭제
            	i--;
            }
        }
    }
    
    static class Misaeng implements Comparable<Misaeng>{
        int x;
        int y;
        int num; // 미생물 수
        int dir; // 1 상 2 하 3 좌 4 우
        int pos; // 2차원배열을 일차원배열처럼 (0,0)부터 0,1,2,3,....

        public Misaeng(int x, int y, int num, int dir) {
            super();
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
            this.pos = x*n + y;
        }

		@Override
		public int compareTo(Misaeng o) {
			// 일차원배열 순서대로 정렬한 후 같은 셀이면 그 중에서 개수 많은애로 정렬
			return (this.pos == o.pos) ? o.num - this.num : this.pos-o.pos;
		}

    }
}