import java.util.ArrayList;
import java.util.Scanner;

public class GameIA {

    public class Node{
        Table t;
        ArrayList<Node> arr;

        public Node(Table table)
        { t= table; arr= new ArrayList<>();}
    }

    Node node;

    public GameIA(String t){
        node = new Node(new Table(t));
    }

    public void addNode(Node curr, int prof) {

        if (prof == 0) { return; }
        if (curr.t.getScore()!= 0){ curr.t.score = 999*curr.t.team(); }
        else {
            for (int i = 1; i < 9; i++) {
                if (curr.t.posibleMove(i)) {
                    Node newNode = new Node(curr.t.move(i));
                    if (prof == 1) { newNode.t.setScore(); }
                    addNode(newNode, prof - 1);
                    curr.arr.add(newNode);
                }
            }
        }
    }

    public void negamax(Node node, int alpha, int betta) {
        if (node == null||node.t.score==999||node.t.score==-999) return;
            int score;
            if (node.t.team() == 1) {
                score = Integer.MAX_VALUE;
                for (Node x : node.arr) {
                    if (x.t.score==0) negamax(x, alpha, betta);
                    if (x.t.score>betta) return;
                    score = Math.min(x.t.score, score); }
                if (score>alpha) betta=score;
            } else {
                score = Integer.MIN_VALUE;
                for (Node x : node.arr) {
                    if (x.t.score==0)negamax(x, alpha, betta);
                    if (x.t.score<alpha) return;
                    score = Math.max(x.t.score, score); }
                if (score>alpha) alpha=score;
            }
            node.t.score = score;

    }

    public Node getBestMove(){
        addNode(this.node, 7);
        negamax(this.node, Integer.MIN_VALUE, Integer.MAX_VALUE);
        for (Node x : this.node.arr){
            if (x.t.getScore()==999*x.t.team()){
                return x;
            }

        }
        Node curr = this.node.arr.get(0);
        ArrayList<Node> list = new ArrayList<>();
        for (Node x : this.node.arr){
            if (x.t.score>curr.t.score){curr=x; list.clear();}
            if (x.t.score==curr.t.score){list.add(x); list.add(curr);}
        }
        if (list.size()>1){
            int random =(int)(Math.random()*list.size());
            return list.get(random);
        }
        return curr;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        GameIA ia = new GameIA("4");
        while (true){
            if (ia.node.t.team()==-1){
                ia.node = ia.getBestMove();
                System.out.println(ia.node.t);
            }else{
                ia = new GameIA(ia.node.t.move(scan.nextInt()).table);
                System.out.println(ia.node.t);
            }
            System.out.println();
        }
    }
}


