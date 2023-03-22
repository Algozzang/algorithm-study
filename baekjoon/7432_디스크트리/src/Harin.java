
import java.io.*;
import java.util.*;

public class Harin {
    private static Node foundNode;
    private static class Node implements Comparable<Node>{
        String key;
        String data;
        ArrayList<Node> children;
        int level;

        public Node(String key, int level) {
            this.key = key;
            this.data = null;
            this.children = new ArrayList<>();
            this.level = level;
        }

        @Override
        public boolean equals(Object o) {
            Node node = (Node) o;
            if(this.key.equals(node.key)){
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key='" + key + '\'' +
                    ", data='" + data + '\'' +
                    ", level=" + level +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return this.level != o.level ? Integer.compare(o.level, this.level) : this.key.compareTo(o.key);
        }

        public void sortChildren(){
            Collections.sort(this.children);
        }
    }

    private static class Tree{
        Node root;

        public Tree(Node root) {
            this.root = root;
        }

        private Node insert(Node node, String str, int index, int level){

            //마지막 글자까지 삽입했으면
            if(index == str.length()){
                node.data = str;    //데이터 입력
                return node;
            }

            //str 한글자씩 자식 노드로 삽입
            String key = String.valueOf(str.charAt(index));
            Node newNode = new Node(key, level);   //레벨은 상위레벨 + 1

            //해당 글자가 없다면 새로운 노드로 삽입
            if(!node.children.contains(newNode)){
                node.children.add(newNode);
            }

            //해당 글자가 있다면 자식 노드로 다음 글자 삽입
            else {
                for (int i = 0; i < node.children.size(); i++) {
                    if (node.children.get(i).equals(newNode)) {
                        newNode = node.children.get(i);
                    }
                }
            }
            Node rtrnNode = null;
            rtrnNode = insert(newNode, str, index+1, level);
            return rtrnNode;
        }

        private void search(Node node, String str){

            if(node.data != null && node.data.equals(str)){
                foundNode = node;
                return;
            }
            else if(node.children.isEmpty()){
                return;
            }
            for(int i=0; i<node.children.size(); i++){
                search(node.children.get(i), str);
            }
        }

        private void search_children(Node node, String str){
            for(int i=0; i<node.children.size(); i++){
                search(node.children.get(i), str);
            }
        }

        private void printAnswer(Node node){
            if(node.data != null) {
                int space = node.level-1;
                for(int i=0; i<space; i++) System.out.print(" ");
                System.out.println(node.data);
            }
            if(node.children.isEmpty()) return;

            for(int i=0; i<node.children.size(); i++){
                printAnswer(node.children.get(i));
            }
        }

        private void sort(Node node){
            Collections.sort(node.children);
            if(node.children.isEmpty()) return;
            for(int i=0; i<node.children.size(); i++){
                sort(node.children.get(i));
            }
        }

        private void sort(){
            sort(this.root);
        }

        private static void printNode(Node node){
            System.out.println(node);
            System.out.println("\t\tchildren : " + node.children);

            if(node.children.isEmpty()){
                return;
            }
            for(int i=0; i<node.children.size(); i++){
                printNode(node.children.get(i));
            }
        }
        private void printAnswer(){
            printAnswer(this.root);
        }

        private void printNode(){
            printNode(this.root);
        }
    }

    public static void main(String[] args) throws IOException {

        Node root = new Node(null, 0);
        Tree tree = new Tree(root);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), "\\");
            Node prevNode = root;

            while(st.hasMoreTokens()){
                foundNode = null;

                String dirname = st.nextToken();
                tree.search_children(prevNode, dirname);

                //없으면 상위레벨 밑에 이어붙인다
                if(foundNode == null){
                    prevNode = tree.insert(prevNode, dirname, 0, prevNode.level+1);
                }
                //있으면 현재 디렉토리를 상위로 가르키고 다음 탐색
                else{
                    prevNode = foundNode;
                }
            }
        }
        tree.sort();
        tree.printAnswer();
    }
}