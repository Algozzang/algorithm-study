import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Minju {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int result = 0;
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[h][w]; //입력 배열

		List<Integer>[] row = new ArrayList[h]; // 각 행마다 무슨 열에 블록 가지고 있는지 체크
		
		for(int i =0;i<h;i++) {
			row[i] = new ArrayList<Integer>();
		}
		
		st = new StringTokenizer(br.readLine());
		int col = 0;
		for (int j = 0; j < w; j++) { // input
			int cur = Integer.parseInt(st.nextToken());
			for (int r = h - 1; r >= 0; r--) {
				if (cur-- > 0) {
					map[r][col] = true;
				}
			}
			col++;
		}
		
		
		for (int j = h-1; j >=0; j--) { //블록있으면 리스트에 열 인덱스 넣기
			for (int i = 0; i < w; i++) {
				if(map[j][i]) row[j].add(i);
	
			}
		}
		// 행 리스트 돌면서 차이 1보다 크면 그 차이-1만큼 더해주기
		// 행에 1,5 열에 원소 있다면 5-1-1 만큼 더해주는 것
		for (int i = 0; i < h; i++) { 
			for (int j = 1; j < row[i].size(); j++) {
				int diff = row[i].get(j) - row[i].get(j-1);
				if(diff>1) result += diff-1 ;
			}
		}
		System.out.println(result);
	}

}
