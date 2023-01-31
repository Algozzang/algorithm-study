import java.util.*;
public class ���Ѿ�_�� {

	static int c;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n= sc.nextInt(); // ���� ũ��
		int k=sc.nextInt(); //�������
		int[][] board = new int[n][n];
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};
		
		for(int i=0;i<k;i++) {
			board[sc.nextInt()-1][sc.nextInt()-1]=1; //����� 1
		}
		
		int[] head = {0,0};
		Queue<int[]> tail =new LinkedList<>();
		int l = sc.nextInt(); //���� Ƚ��
		Map<Integer, String> map = new HashMap<>();
		for(int i=0;i<l;i++) {
			map.put(sc.nextInt(), sc.next());
		}
//		tail.add(new int[] {0,0});  �̷� �����̤� ������ ���Ͼ��� ���̤���
		int time=0;
		c=0;//����
		board[0][0]=2; //�ڱ��ڽ�
		
		while(true) { //����
			time++;
			int nx=dx[c]+head[0];
			int ny=dy[c]+head[1];
			if(nx<0||ny<0||ny>=n||nx>=n||board[nx][ny]==2) {
				break;                                           //���̰ų� ���̰ų�.
			}
			if(board[nx][ny]==0) { //��� ���ٸ�
				if(tail.isEmpty()) tail.add(new int[] {0,0}); //���� ����.....
				int[] tmp = tail.poll();
				board[tmp[0]][tmp[1]]=0; //������ �ڸ� �ʱ�ȭ
				board[nx][ny]=2; // ��ġ ����
				tail.add(head.clone()); //��ü ���� Ŭ������ �Ƴ� ���� ��,�Ѹ�����
				
			}else { //�ִٸ�
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


