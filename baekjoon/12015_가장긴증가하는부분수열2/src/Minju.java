import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.*;
import java.util.List;
import java.util.StringTokenizer;

public class Minju {

	static List<Integer> dp = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		dp.add(Integer.parseInt(st.nextToken()));
		int dpSize = 1;
		for(int i =1;i<n; i++) {
			int cur = Integer.parseInt(st.nextToken());
			//System.out.println(cur + " " +Arrays.toString(dp.toArray()));
			if(dp.get(dpSize-1)<cur) {
				dp.add(cur);
				dpSize++;
			}else if(dp.get(dpSize-1)>cur){ // 이진탐색으로 끼워넣기
				int idx = Collections.binarySearch(dp, cur);
				//System.out.println(cur + " : "+idx);
				if(idx >= 0) {
					dp.set(idx, cur);
				}else {
					idx = Math.abs(idx)-1;
					dp.set(idx, cur);
				}
			}
		}
		//System.out.println(Arrays.toString(dp.toArray()));
		System.out.println(dp.size());
	}
}