import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameMap {

    private List<GameObject> staticObjects = new ArrayList<>();
    private List<GameObject> dynamicObjects = new ArrayList<>();
    private int gridHeight;
    private int gridWidth;
    private Player player;
    private boolean isGameWon = false;

    public void loadFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
            return;
        }

        gridHeight = lines.size();
        gridWidth = 0;
        for (String line : lines) {
            if (line.length() > gridWidth) {
                gridWidth = line.length();
            }
        }

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                Position pos = new Position(x, y);

                switch (c) {
                    case '#':
                        staticObjects.add(new Wall(pos));
                        break;
                    case 'T':
                        staticObjects.add(new Target(pos));
                        break;
                    case 'P':
                        player = new Player(pos, gridWidth, gridHeight);
                        dynamicObjects.add(player);
                        break;
                    case 'B':
                        dynamicObjects.add(new Box(pos));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void draw() {
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                Position currentPos = new Position(x, y);
                GameObject dynamicObj = getObjectAt(dynamicObjects, currentPos);
                GameObject staticObj = getObjectAt(staticObjects, currentPos);

                if (dynamicObj != null) {
                    System.out.print(dynamicObj.getSymbol() + " ");
                } else if (staticObj != null) {
                    System.out.print(staticObj.getSymbol() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public void update() {
        draw();
        checkWinCondition();
    }

    public void movePlayer(int dx, int dy) {
        if (isGameWon) return;

        Position playerPos = player.getPositionObject();
        int nextX = playerPos.getx() + dx;
        int nextY = playerPos.gety() + dy;
        Position targetPos = new Position(nextX, nextY);

        if (!isWithinBounds(targetPos)) {
            System.out.println("You can't move outside the map!");
            return;
        }

        GameObject staticObj = getObjectAt(staticObjects, targetPos);
        if (staticObj instanceof Wall) {
            System.out.println("You hit a wall.");
            return;
        }

        GameObject dynamicObj = getObjectAt(dynamicObjects, targetPos);
        if (dynamicObj instanceof Box) {
            Box box = (Box) dynamicObj;
            int boxNextX = nextX + dx;
            int boxNextY = nextY + dy;
            Position boxTargetPos = new Position(boxNextX, boxNextY);

            if (!isWithinBounds(boxTargetPos)) {
                System.out.println("Can't push box out of bounds");
                return;
            }

            if (getObjectAt(staticObjects, boxTargetPos) instanceof Wall ||
                    getObjectAt(dynamicObjects, boxTargetPos) != null) {
                System.out.println("Can't push the box, something is blocking it");
                return;
            }

            box.move(dx, dy);
            player.move(dx, dy);
            System.out.println("Pushed the box");
        } else {
            player.move(dx, dy);
            System.out.println("Moved");
        }
    }

    private void checkWinCondition() {
        boolean allTargetsCovered = true;
        int targetCount = 0;

        for (GameObject staticObj : staticObjects) {
            if (staticObj instanceof Target) {
                targetCount++;
                boolean hasBox = false;
                for (GameObject dynamicObj : dynamicObjects) {
                    if (dynamicObj instanceof Box && dynamicObj.getPositionObject().equals(staticObj.getPositionObject())) {
                        hasBox = true;
                        break;
                    }
                }
                if (!hasBox) {
                    allTargetsCovered = false;
                }
            }
        }

        if (targetCount > 0 && allTargetsCovered) {
            isGameWon = true;
            System.out.println("You win!");
        }
    }

    private GameObject getObjectAt(List<GameObject> objects, Position pos) {
        for (GameObject obj : objects) {
            if (obj.getPositionObject().equals(pos)) {
                return obj;
            }
        }
        return null;
    }

    private boolean isWithinBounds(Position pos) {
        return pos.getx() >= 0 && pos.getx() < gridWidth && pos.gety() >= 0 && pos.gety() < gridHeight;
    }
}