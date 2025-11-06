public class Target extends GameObject {
    public Target(Position position) {
        super(position);
    }

    @Override
    public String getSymbol() {
        return "T";
    }
}