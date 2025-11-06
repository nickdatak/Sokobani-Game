public class Position {
    //setting up the position here (x,y) nothing complicated and methods to get x,y and set them as well
    private int x;
    private int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;}
    
    public int getx() {
        return x;
    }
    public void setx(int x) {
        this.x = x;
    }
    
    public int gety() {
        return y;
    }
    public void sety(int y) {
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
    
}


