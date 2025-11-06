public class Wall extends GameObject {
    public Wall(Position position) {
        super(position);
    }

    @Override
    public String getSymbol() {
        return "#";
    }
}