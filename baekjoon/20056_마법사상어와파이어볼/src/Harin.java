import java.util.*;
import java.io.*;

public class Main {
    private static int[][] direction = new int[][]{{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    private static class FireBall{
        int r, c, m, s, d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<FireBall> balllist = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int mi = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            balllist.add(new FireBall(r, c, mi, s, d));
        }

        Map<Integer, List<FireBall>> aftermove = new HashMap<>();

        for(int i=0; i<k; i++){     //k번 이동
            for(int j=0; j<balllist.size(); j++) {

                FireBall fb = balllist.get(j);
                int nr = fb.r + (direction[fb.d][0] * fb.s);
                int nc = fb.c + (direction[fb.d][1] * fb.s);

                //범위를 벗어날 경우 반대쪽으로 이어진다
                if (nr > n) nr %= n;
                else if (nr < 1) nr += n;

                if (nc > n) nc %= n;
                else if (nc < 1) nc += n;

                //해당 좌표에 볼을 옮긴다
                List<FireBall> list = aftermove.get(n*(nr-1)+nc);

                try {
                    list.add(new FireBall(nr, nc, fb.m, fb.s, fb.d));
                } catch (Exception e) {
                    list = new ArrayList<>();
                    list.add(new FireBall(nr, nc, fb.m, fb.s, fb.d));
                }
                aftermove.put(n*(nr-1)+nc, list);     //이동한 파이어볼을 좌표별로 저장해둔다
            }
            //이동을 마치면 이동 후 볼 넣어주기 위해 리스트 초기화
            balllist.clear();

            for(Map.Entry<Integer, List<FireBall>> entry : aftermove.entrySet()){
                List<FireBall> temp = entry.getValue();

                if(temp.size() >= 2){   //2개 이상이면 분할
                    int weightsum = 0;
                    int speedsum = 0;
                    int[] re = new int[temp.size()];

                    for(int l=0; l<temp.size(); l++){
                        weightsum += temp.get(l).m;
                        speedsum += temp.get(l).s;
                        re[l] = temp.get(l).d%2;
                    }
                    int wavg = weightsum/5;
                    int savg = speedsum/temp.size();

                    int oddOrEven = Arrays.stream(re).sum();
                    int[] newdir = new int[4];

                    if(oddOrEven == 0 || oddOrEven == 4){   //모두 짝수이거나 모두 홀수
                        newdir = new int[]{0, 2, 4, 6};
                    }
                    else{
                        newdir = new int[]{1, 3, 5, 7};
                    }

                    if(weightsum != 0){     //질량이 0이면 사라진다
                        int r = temp.get(0).r; int c = temp.get(0).c;
                        balllist.add(new FireBall(r, c, wavg, savg, newdir[0]));
                        balllist.add(new FireBall(r, c, wavg, savg, newdir[1]));
                        balllist.add(new FireBall(r, c, wavg, savg, newdir[2]));
                        balllist.add(new FireBall(r, c, wavg, savg, newdir[3]));
                    }
                }
                else{   //2개 이상 아니라면 그대로 리스트에 다시 넣어주기
                    balllist.add(new FireBall(temp.get(0).r, temp.get(0).c, temp.get(0).m, temp.get(0).s, temp.get(0).d));
                }
            }
            //리스트에 다시 넣었으면 해시맵 초기화
            aftermove.clear();
        }

        //k번 이동이 끝난 후 남아있는 파이어볼 질량의 합 구하기
        int answer = balllist.stream().map(x -> x.m).mapToInt(x->x).sum();
        sb.append(answer);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }
}
