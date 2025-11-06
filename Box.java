public class Box extends GameObject {
    public Box(Position position) {
        super(position);
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}