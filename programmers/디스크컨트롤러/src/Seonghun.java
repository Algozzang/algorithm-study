import java.util.Arrays;
import java.util.PriorityQueue;

public class Seonghun {

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution(new int[][] { { 0, 3 }, { 1, 9 }, { 2, 6 } }));
	}

}

class Solution {
	public int solution(int[][] jobs) {
		// 요청시간이 빠른 순서로 작업 정렬
		Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

		// 대기 중인 작업, 소요 시간이 짧은 작업이 우선순위가 크도록 설정
		PriorityQueue<int[]> waiting = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

		//
		int time = 0; // 현재 시간
		int index = 0; // 선택할 작업 번호
		int waitingTime = 0; // 작업들이 대기한 총 시간
		while (index < jobs.length || !waiting.isEmpty()) {
			// 현재 시간을 고려하여 요청된 작업들을 대기줄에 추가
			while (index < jobs.length && jobs[index][0] <= time) {
				waiting.add(jobs[index++]);
			}

			// 대기중인 작업이 없는 경우
			if (waiting.isEmpty()) {
				// 요청시간이 가장 빠른 시간까지 시간 증가
				time = jobs[index][0];
			}
			// 대기중인 작업이 남아있는 경우
			else {
				int[] task = waiting.poll();
				time += task[1];
				waitingTime += time - task[0];
			}
		}

		return waitingTime / jobs.length;
	}
}