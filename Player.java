public class Player extends GameObject implements Movable{

    private int gridWidth;
    private int gridHeight;

    public Player(Position startPosition, int gridWidth, int gridHeight) {
        super(startPosition);
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
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
        return ("P");
    }

    // Movement methods using inherited getPosition
    //I genuinely have no idea why we still need this because we implemented general move method, but there is no harm in keeping them
    public void moveU() {
        int[] pos = getPosition();
        int newY = pos[1] - 1;
        if (newY >= 0) {
            getPosition()[1] = newY; // This direct array update won't affect Position object, so better to use Position setter through another way
            // Adjust Position directly:
            super.getPositionObject().sety(newY);
        } else {
            System.out.println("You can't move outside the map!");
        }
    }

    public void moveD() {
        int[] pos = getPosition();
        int newY = pos[1] + 1;
        if (newY < gridHeight) {
            super.getPositionObject().sety(newY);
        } else {
            System.out.println("You can't move outside the map!");
        }
    }

    public void moveL() {
        int[] pos = getPosition();
        int newX = pos[0] - 1;
        if (newX >= 0) {
            super.getPositionObject().setx(newX);
        } else {
            System.out.println("You can't move outside the map!");
        }
    }

    public void moveR() {
        int[] pos = getPosition();
        int newX = pos[0] + 1;
        if (newX < gridWidth) {
            super.getPositionObject().setx(newX);
        } else {
            System.out.println("You can't move outside the map!");
        }
    }

}
