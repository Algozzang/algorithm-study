package BOJ;

import java.io.*;
import java.util.*;
public class BJ8980_택배 {

    private static class Delivery{
        int from, to, post;

        public Delivery(int from, int to, int post) {
            this.from = from;
            this.to = to;
            this.post = post;
        }

        @Override
        public String toString() {
            return "Delivery{" +
                    "from=" + from +
                    ", to=" + to +
                    ", post=" + post +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        ArrayList<Delivery> deliveries = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());

            deliveries.add(new Delivery(from, to, post));
        }

        Collections.sort(deliveries, (o1, o2) -> o1.from - o2.from);

        ArrayList<Delivery> truck = new ArrayList<>();
        int curTruckContain = 0;
        int delivered = 0;

        for(int town=1; town<=n; town++){

            //1. 배송 할거 먼저 내리고
            if(!truck.isEmpty()){
                ArrayList<Delivery> toDelete = new ArrayList<>();

                //마을에 택배 배송 완료
                for(Delivery del : truck){
                    if(del.to == town){
                        delivered += del.post;
                        curTruckContain -= del.post;
                        toDelete.add(del);
                    }
                }
                //배송 완료된건 리스트에서 삭제
                for(Delivery del : toDelete){
                    truck.remove(del);
                }
            }

            //2. 새로 담기
            for(Delivery del : deliveries){
                if(del.from == town){
                    //용량이 부족할 경우
                    if(curTruckContain + del.post > c){
                        //현재 트럭에 있는 도착지보다 새로 담을 택배 도착지가 먼저인 경우
                        while(!truck.isEmpty() && truck.get(truck.size()-1).to > del.to) {
                            //기존에 담았던 짐을 버리고 가까운 도착지 택배를 담는다
                            //일부만 버려도 되는경우
                            if(truck.get(truck.size()-1).post > del.post){
                                truck.get(truck.size()-1).post -= del.post;
                                curTruckContain -= del.post;
                                break;
                            }
                            //다 버려야 하는 경우
                            else{
                                curTruckContain -= truck.get(truck.size()-1).post;
                                truck.remove(truck.get(truck.size()-1));
                            }
                        }

                        //비웠는데도 용량 부족하면 되는 만큼만 담는다
                        if(curTruckContain + del.post > c){
                            del.post = c - curTruckContain;
                            curTruckContain = c;
                            truck.add(del);
                        }

                        //허용 범위면 그대로 추가
                        else{
                            curTruckContain += del.post;
                            truck.add(del);
                        }
                    }

                    //용량이 부족하지 않은 경우 그냥 추가
                    else{
                        curTruckContain += del.post;
                        truck.add(del);
                    }


                }
            }
        }
        System.out.println(delivered);
    }
}
