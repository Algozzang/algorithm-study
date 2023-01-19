import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class dongwoo {
	public static void main(String[] args) {
		// greedy로 풀 수 있을 것 같음
		// 일단 greedy로 하고 최대값이랑 같으면 홀짝인지 판별하기
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case=1; test_case<=T; test_case++) {
			int originalNum = sc.nextInt();
			int changeNumLimit = sc.nextInt();
			
			String orginalNumStr = Integer.toString(originalNum);
			int size = orginalNumStr.length();
			int[] nums = new int[size];
			
			for (int i=0; i<size; i++) {
				nums[i] = orginalNumStr.charAt(i)-'0';
			}
			int[] nums2 = nums.clone();
			reverseSort(nums2);
			
			// greedy 구현 시작
			
			int changeNum = 0;
			
			for (int j = 0; j<size-1; j++) {
				int maxiumValue = nums[j];
				int maxiumInd = j;
				for (int i=j+1; i<size; i++) {
					if (nums[i]>=maxiumValue) {
						maxiumInd = i;
						maxiumValue = nums[i];
					}
				}
				if (maxiumValue==nums[j]) {
					continue;
				} 
				int temp = nums[j];
				nums[j] = nums[maxiumInd];
				nums[maxiumInd] = temp;
				changeNum ++;
				
				if (changeNumLimit<=changeNum) {
					break;
				}
				if (isEqualArr(nums, nums2)) {
					int movingLeft = changeNumLimit - changeNum;
					if (movingLeft%2!=0) {
						int tmp = nums[nums.length-1];
						nums[nums.length-1] = nums[nums.length];
						nums[nums.length] = tmp;
					}
					break;
				}
			}
			System.out.println(Arrays.toString(nums));
		}
		
	}
	public static boolean isEqualArr(int[] arr1, int[] arr2) {
		int SIZE1 = arr1.length;
		int SIZE2 = arr2.length;
		if(SIZE1!=SIZE2) return false;
		for (int i = 0; i < SIZE1; i++) {
			if (arr1[i]!=arr2[i]) return false;
		}
		return true;
	}
	
	public static void reverseSort (int[] arr) {
		Arrays.sort(arr);
		for (int i=0; i<arr.length/2; i++) {
			int temp = arr[i]; 
			arr[i] = arr[arr.length - i -1];
			arr[arr.length - i - 1] = temp;
		}
	}
}

