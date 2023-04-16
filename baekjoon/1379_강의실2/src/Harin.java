package BOJ;

import java.util.*;
import java.io.*;

public class BJ1379_강의실2 {

    private static class Lecture{
        int index, start, end;

        public Lecture(int index, int start, int end) {
            this.index = index;
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        //강의를 시작시간 순으로 정렬
        PriorityQueue<Lecture> lectureQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.start, o2.start));

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lectureQueue.add(new Lecture(index, start, end));
        }

        int[] roomNumber = new int[n+1];    //각 강의에 배정한 강의실 번호
        int[] endTimeOfRoom = new int[n+1];     //각 강의실에 배정된 마지막 회의가 끝나는 시간

        while(!lectureQueue.isEmpty()){
            Lecture lec = lectureQueue.poll();

            for(int i=1; i<n+1; i++){
                if(endTimeOfRoom[i] <= lec.start){  //끝나는 시간이 나의 시작 시간보다 같거나 작으면 배정 가능
                    roomNumber[lec.index] = i;      //해당 강의실로 배정하고
                    endTimeOfRoom[i] = lec.end;     //강의실의 끝나는 시간 업데이트
                    break;
                }
            }
        }

        int needRoomNum = Arrays.stream(roomNumber).max().getAsInt();   //강의실 번호중 가장 큰 번호가 필요한 강의실의 개수
        sb.append(needRoomNum).append("\n");

        for(int i=1; i<=n; i++){
            sb.append(roomNumber[i]).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }
}
