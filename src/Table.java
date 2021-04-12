public class Table {

    String table;
    int score;
    public Table(String item) {
        table = item;
    }

    public int team() {
        if (table.length() % 2 == 1) {
            return 1;
        }
        return -1;
    }
    @Override
    public String toString(){
        int[] gameTable = toArray();
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for (int i=0; i<56; i++){
            if (i%8==0&&i!=0) sb.append("|\n|");
            if (gameTable[i]==0){
                sb.append("| |");
            }
            else if (gameTable[i]==1){
                sb.append("|R|");
            }
            else if (gameTable[i]==-1){
                sb.append("|A|");
            }
        }
        sb.append("|");
        return sb.toString();
    }
    public int[] toArray(){
        int[] tableland = new int[56];
        int team = 1;
        for (int i=0; i<table.length(); i++){
            int pos =47+ Integer.parseInt(String.valueOf(table.charAt(i)));
            while (tableland[pos]!=0&&pos>0){ pos-=8;}
            tableland[pos]=team;
            team=-team;
        }
        return tableland;
    }
    public int goDown(int[] table , int pos){
        int sol=-1;
        while (pos<56&&table[pos]==0){ sol++; pos+=8;}
        return sol;
    }

    public int scoreUp() {
        int solution = -1;

        for (int i = 1; i < 9; i++) {
            int cont = 0;
            for (int j = table.length() - 1; j >= 0; j--) {
                if (i == Integer.parseInt(String.valueOf(table.charAt(j)))){
                    if (j%2!=table.length()%2)cont++;
                    else break;
                }
            }
            int hight=0;
            if (cont>0){
                for (int j=0; j<table.length(); j++){ if (i == Integer.parseInt(String.valueOf(table.charAt(j)))) { hight++;} }
                if (7-hight>=4-cont) { solution = Math.max(solution, cont); }
            }
        }
        return 4-solution;
    }
    public int scoreNext(int[] arr) {
        int team = team();
        int solution =30;

        for (int i=0; i<7; i++){
            for (int j=0; j<5; j++){
                int chips=0;
                int need=0;
                for (int k=0; k<4; k++) {
                    int pos= (i*8)+j+k;
                    if (arr[pos] == -team) break;
                    else if (arr[pos] == 0){ need += goDown(arr, pos)+1; chips++;}
                    else chips++;
                }
                if (chips==4){ solution = Math.min(solution, need); }
            }
        } return solution;
    }
    public int scoreDiagL(int[] arr) {
        int team = team();
        int solution = 999;
        for (int i=3; i<7; i++){
            for (int j=0; j<4; j++){
                int chips=0;
                int need=0;
                for (int k=0; k<4; k++) {
                    int pos= (i*8)-(k*7)-(j*7);
                    if (pos<0||pos>55||arr[pos] == -team) break;
                    if (arr[pos] == 0){ need += goDown(arr, pos)+1; chips++;}
                    if(arr[pos]==team){chips++;}
                }
                if (chips==4){ solution = Math.min(solution, need); }
            }
        }
        for(int i=49; i<53; i++){
            for (int j=0; j<4; j++){
                int chips=0;
                int need=0;
                for (int k=0; k<4; k++) {
                    int pos= i-(k*7)-(j*7);
                    if (pos%8==0||pos<0||pos>55||arr[pos] == -team) break;
                    else if (arr[pos] == 0){ need += goDown(arr, pos)+1; chips++;}
                    else if (arr[pos]==team){chips++;};
                }
                if (chips==4){ solution = Math.min(solution, need); }
            }
        }
        return solution;
    }
    public int scoreDiagR(int[] arr) {
        int team = team();
        int solution = 999;
        for (int i=0; i<5; i++){
            for (int j=0; j<4; j++){
                int chips=0;
                int need=0;
                for (int k=0; k<4; k++) {
                    int pos= i+(j*9)+(k*9);
                    if (pos<0||pos>55||(pos%8==0&&pos!=0)||arr[pos] == -team) break;
                    if (arr[pos] == 0){ need += goDown(arr, pos)+1; chips++;}
                    if(arr[pos]==team){chips++;}
                }
                if (chips==4){ solution = Math.min(solution, need); }
            }
        }
        for(int i=1; i<4; i++){
            for (int j=0; j<4; j++){
                int chips=0;
                int need=0;
                for (int k=0; k<4; k++) {
                    int pos= (i*8)+(k*9)+(j*9);
                    if (pos<0||pos>55||arr[pos] == -team) break;
                    else if (arr[pos] == 0){ need += goDown(arr, pos)+1; chips++;}
                    else if (arr[pos]==team){chips++;};
                }
                if (chips==4){ solution = Math.min(solution, need); }
            }
        }
        return solution;
    }

    public void setScore(){
        int actualChips = (table.length()+1)/2;
        int[]table = toArray();
        int solution= Math.min(scoreUp(),scoreNext(table));
        int solution2 = Math.min(scoreDiagL(table), scoreDiagR(table));
        int sol = Math.min(solution, solution2);
        if (sol==0) this.score = 999*team();
        else this.score= (34-(actualChips+sol))*team();
    }
    public int getScore(){
        int[]table = toArray();
        int solution= Math.min(scoreUp(),scoreNext(table));
        int solution2 = Math.min(scoreDiagL(table), scoreDiagR(table));
        int sol = Math.min(solution, solution2);
        if (sol==0){return 999*team();}
        else{ return 0; }
    }



    public boolean posibleMove(int num){
        int cont=0;
        if (num<9&&num>0){
            for (int i=0; i<table.length(); i++){
                if (num== Integer.parseInt(String.valueOf(table.charAt(i)))){
                    cont++;
                }
            }
            if (cont<7){return true;}
        }

        return false;
    }
    public Table move(int n){
        String newTable = table+n;
        return new Table(newTable);
    }

    public static void main(String[] args) {
        Table t = new Table("775423655217723322774542556");
        int[] arr = t.toArray();
        System.out.println(t);
        t.setScore();
        System.out.println("Score up: "+t.scoreUp());
        System.out.println("Score next: "+t.scoreNext(arr));
        System.out.println("Score diagL: "+t.scoreDiagL(arr));
        System.out.println("Score diagR: "+t.scoreDiagR(arr));

    }

}
