public class Box extends GameObject implements Movable{
    public Box(Position position) {
        super(position);
    }

    @Override
    public void move(int dx, int dy) {
        Position pos = super.getPositionObject();
        int newX = pos.getx() + dx;
        int newY = pos.gety() + dy;
        pos.setx(newX);
        pos.sety(newY);
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}