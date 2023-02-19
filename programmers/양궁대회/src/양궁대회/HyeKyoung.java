package 양궁대회;

import java.util.Arrays;

public class HyeKyoung {

	public static void main(String[] args) {
		Solution s1 = new Solution();
		Solution s2 = new Solution();
		Solution s3 = new Solution();
		Solution s4 = new Solution();
		System.out.println(Arrays.toString(s1.solution(5, new int[] { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 })));
		System.out.println(Arrays.toString(s2.solution(1, new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 })));
		System.out.println(Arrays.toString(s3.solution(9, new int[] { 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 })));
		System.out.println(Arrays.toString(s4.solution(10, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 })));
	}
}

class Solution {
	boolean possible = false;
	int n, max=0, aScr, rScr;
	int[] im = {-1};
	int[] appeach, ryan, selected, tmp;
	void comb(int start, int cnt, int size) {
		//라이언이 가져갈 라운드 조합
		if(cnt==size) {
			aScr=0; rScr = 0;
			int idx = 0;
			for(int i=0; i<11; i++) {
				
				if(idx<size && selected[idx]==i) {
					rScr+=10-i;
					idx++;
				}
				else {
					if(appeach[i]==0) continue; 
					aScr+=10-i;
				}
			}
			if(max<=rScr && aScr<rScr) {
				shoot(size, rScr);
			}
			return;
		}
		for(int i=start; i<11; i++) {
			selected[cnt]=i;
			comb(i+1, cnt+1, size);
		}
	}
	
	void shoot(int size, int scr) {
		tmp =new int[11];
		int cnt = n;
		int idx = 0;
		for(int i=0; i<11; i++) {
			if(selected[idx] != i) continue;
			else {
				tmp[i]=appeach[i]+1;
				cnt-=tmp[i];
				
				if(cnt<0) return; //불가능한 조합인것
				idx++;
			}
			if(idx == size) {
				remain(tmp, cnt, size);
				possible = true;
				max = scr;
				return;
			}
		}
	}
	
	void remain(int[] tmp, int cnt, int size) {
		int idx = size-1;
		for(int i=10; i>=0; i--) {
			if(cnt<=0) break;
			if(selected[idx]==i) {
				tmp[i]+=cnt;
				break;
			}
			
			if(appeach[i]<tmp[i]+cnt) {
				//어피치가 이겨야하는 라운드면
				int c=appeach[i]-tmp[i];
				tmp[i] = appeach[i];
				cnt-=appeach[i]-tmp[i];
			}
			else {
				tmp[i]+=cnt;
				break;
			}
		}
		
		//뒤부터 체크해서 낮은 점수가 더 많으면
		for(int i=10; i>=0; i--)
			if(ryan[i]<tmp[i]) {
				ryan = tmp.clone();
				return;
			}
	}
	
	public int[] solution(int n, int[] info) {
		this.n = n;
		appeach = info.clone();
		ryan = new int[11];
		
		for(int i=n; i>=1; i--) {
			selected = new int[i];
			comb(0, 0, i);
		}
		return possible ? ryan : im;
	}
}
