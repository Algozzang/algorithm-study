import java.util.*;
public class 지한얼_뱀 {

	static int c;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n= sc.nextInt(); // 보드 크기
		int k=sc.nextInt(); //사과개수
		int[][] board = new int[n][n];
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};
		
		for(int i=0;i<k;i++) {
			board[sc.nextInt()-1][sc.nextInt()-1]=1; //사과는 1
		}
		
		int[] head = {0,0};
		Queue<int[]> tail =new LinkedList<>();
		int l = sc.nextInt(); //방향 횟수
		Map<Integer, String> map = new HashMap<>();
		for(int i=0;i<l;i++) {
			map.put(sc.nextInt(), sc.next());
		}
//		tail.add(new int[] {0,0});  이런 나러이ㅏ ㅁㄴㄻ ㅏ니엄ㄹ ㄴ이ㅏㅓ
		int time=0;
		c=0;//방향
		board[0][0]=2; //자기자신
		
		while(true) { //시작
			time++;
			int nx=dx[c]+head[0];
			int ny=dy[c]+head[1];
			if(nx<0||ny<0||ny>=n||nx>=n||board[nx][ny]==2) {
				break;                                           //몸이거나 밖이거나.
			}
			if(board[nx][ny]==0) { //사과 없다면
				if(tail.isEmpty()) tail.add(new int[] {0,0}); //거지 같다.....
				int[] tmp = tail.poll();
				board[tmp[0]][tmp[1]]=0; //지나온 자리 초기화
				board[nx][ny]=2; // 위치 갱신
				tail.add(head.clone()); //객체 지향 클론으로 아나 증말 ㅋ,ㅡ리낭ㄹ
				
			}else { //있다면
				tail.add(head.clone());
				
				board[nx][ny]=2;
			}
			head[0]=nx;
			head[1]=ny;
			if(map.containsKey(time)) {
				direction(map.get(time));
			}
		}
		System.out.println(time);
	}
	static void direction(String direc) {
		if(direc.equals("D")) {
			c=(c+1)%4;
		} else {
			
			c=c-1<0?3:c-1;
		}
	}

}


