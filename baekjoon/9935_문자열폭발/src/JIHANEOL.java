import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class JIHANEOL {
	static  BufferedReader br;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		String boom = br.readLine();
		StringBuilder newWord = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<word.length(); i++) {
			// boom에 포함되었나.
			if(boom.contains(""+word.charAt(i))){
				// 1. boom에서 마지막 문자면 넣고 스택 확인.
				if(boom.charAt(boom.length()-1)==word.charAt(i)) {
					stack.add(word.charAt(i));
					check(stack,boom);
				}else {
				// 2. 넣기만 하기
					stack.add(word.charAt(i));
				}
				
				
			// 포함 되지 않으면
			}else {
				// 1. 스택이 남아 있다면 새로운 단어에 넣고 현재 단어 넣기.
				if(!stack.isEmpty()) {
					for(int j=0; j<stack.size(); j++) {
						newWord.append(stack.get(j));
					}
					stack.clear();
					newWord.append( word.charAt(i));
				}else {
					//2 . 비어 있다면 그냥 넣기
					newWord.append(word.charAt(i));
				}
				
			}
		}
		if(!stack.isEmpty()) {
			for(int i=0; i<stack.size(); i++) {
				newWord.append(stack.get(i));
			}
		}
		if(newWord.toString().equals("")) {
			System.out.println("FRULA");
		}else {
			
			System.out.println(newWord.toString());
		}
		
	}
	public static void check(Stack<Character> stack,String boom) {
		boolean flag = false;
		if(stack.size()>=boom.length()) {
			for(int i=0; i<boom.length(); i++) {
				if(stack.get(stack.size()-boom.length()+i)!=(boom.charAt(i))){
					flag = true;	
					break;
				}
			}
		}else {
			return;
		}
		
		if(flag) {
			return;
		}else {
			for(int i=0; i<boom.length(); i++) {
				stack.pop();
			}
		}
	}
}