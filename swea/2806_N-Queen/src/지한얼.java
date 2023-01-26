import java.util.Scanner;

public class 지한얼 {
		static int[] arr;
		static int n,answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for(int test=1; test<=T;test++) {
			answer=0;
			 n=sc.nextInt();
			 arr=new int[n+1];
			 dfs(1);
			
			 System.out.println("#"+test+" "+answer);
		}

	}
	public static void dfs(int row) {
		if(row==n+1) { //끝 도착하면 +1;
				answer++;
				return;
		}
		for(int c=1;c<n+1;c++) { //열
			arr[row]=c;
		if(check(row))//대각선 확인
		dfs(row+1);
		}
		
	}
	public static boolean check(int row) {
		
		for(int r=1; r<row; r++) {//현재 퀸기준으로 위에 퀸행 비교
			if(row-r==Math.abs(arr[row]-arr[r])||arr[row]==arr[r]) { // 대각선과 열 비교
				return false;
			}
		}
		
		return true;
	}

}
/*
파이썬코드
def promising(x,y):
    for i in range(8):
        nx=dx[i]+x
        ny=dy[i]+y
        while nx>=0 and ny>=0 and ny<n and nx<n:
            if data[nx][ny]==1:
                return False
            nx+=dx[i]
            ny+=dy[i]
    
    return True

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    answer=0
    n=int(input())
    data=[]
    queen=[]
    dx=[0,1,1,1,0,-1,-1,-1]
    dy=[1,1,0,-1,-1,-1,0,1]
    for i in range(n):
        data.append([0]*n)

    visited={} # 가지치기
    q=[]
    def solution(cnt):
        global answer
        if cnt==n:
            answer+=1
            return
        else:
            for i in range(n):
                
                visited[(cnt,i)]=1
                data[cnt][i]=1

                if promising(cnt,i):
                    solution(cnt+1)

                data[cnt][i]=0
                visited[(cnt,i)]=0


    solution(0)
    print(f'#{test_case} {answer}')






*/