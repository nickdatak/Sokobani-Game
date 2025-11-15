import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        //creating a grid example and moving player around a bit 
        int gridWidth = 5;
        int gridHeight = 5;

        //creating objects here
        Player player = new Player(new Position(3, 3), gridWidth, gridHeight);
        Target target = new Target(new Position(1, 1));
        Wall wall1 = new Wall(new Position(2, 2));
        Wall wall2 = new Wall(new Position(4, 0));
        Box box = new Box(new Position(1, 3));

        //creating this list thing to hold object together
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(player);
        gameObjects.add(target);
        gameObjects.add(wall1);
        gameObjects.add(wall2);
        gameObjects.add(box);


        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            //printing (that's why I implemented method below)
            printGrid(gridWidth, gridHeight, gameObjects);

            System.out.println("\nEnter move (w/a/s/d) or 'q' to quit: ");
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "w": // Up
                    movePlayer(0, -1, player, gameObjects, gridWidth, gridHeight);
                    break;
                case "a": // Left
                    movePlayer(-1, 0, player, gameObjects, gridWidth, gridHeight);
                    break;
                case "s": // Down
                    movePlayer(0, 1, player, gameObjects, gridWidth, gridHeight);
                    break;
                case "d": // Right
                    movePlayer(1, 0, player, gameObjects, gridWidth, gridHeight);
                    break;
                case "q": // Quit
                    isRunning = false;
                    System.out.println("Quitting game. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input. Please use w/a/s/d or q.");
                    break;
            }
            System.out.println();
        }
    }

    //putting this into a method, so that it would be easier to implement input logic
    public static void printGrid(int gridWidth, int gridHeight, List<GameObject> gameObjects) {

        System.out.println("--- Sokobani ---");
        System.out.println("Grid size: " + gridWidth + "x" + gridHeight);

        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                GameObject obj = getObjectAtPosition(gameObjects, new Position(x, y));
                if (obj != null) {
                    System.out.print(obj.getSymbol() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    // Helper method to find object at given position
    private static GameObject getObjectAtPosition(List<GameObject> objects, Position pos) {
        for (GameObject obj : objects) {
            if (obj.getPositionObject().equals(pos)) {
                return obj;
            }
        }
        return null;
    }

    //I decided to implement a single method to check boundaries, since we have already done it quite  few times and it seems we are going to do it much more in the future
    private static boolean isWithinBounds(Position pos, int gridWidth, int gridHeight) {
        return pos.getx() >= 0 && pos.getx() < gridWidth && pos.gety() >= 0 && pos.gety() < gridHeight;
    }


    //method that takes a direction and tries to move the player
    private static void movePlayer(int dx, int dy, Player player, List<GameObject> gameObjects, int gridHeight, int gridWidth) {

        Position playerPos = player.getPositionObject();
        Position targetPos = new Position(playerPos.getx() + dx, playerPos.gety() + dy);

        if (!isWithinBounds(targetPos, gridWidth, gridHeight)) {
            System.out.println("You can't move outside the map!");
            return;
        }

        GameObject targetObj = getObjectAtPosition(gameObjects, targetPos);

        if (targetObj == null) {
            player.move(dx, dy);
            System.out.println("Moved to empty space.");
            return;
        }

        if (targetObj instanceof Wall) {
            System.out.println("You hit a wall.");
            return;
        }
        if (targetObj instanceof Box) {
            Movable movableBox = (Movable) targetObj;
            Position posBehind = new Position(targetPos.getx() + dx, targetPos.gety() + dy);

            if (!isWithinBounds(posBehind, gridWidth, gridHeight)) {
                System.out.println("Can't push box out of bounds");
                return;
            }
            GameObject objBehindBox = getObjectAtPosition(gameObjects, posBehind);

            if (objBehindBox == null) {
                movableBox.move(dx, dy);
                player.move(dx, dy);
                System.out.println("Pushed the box");
            } else {
                System.out.println("Can't push the box, something is behind it");
            }
        }
        if (targetObj instanceof Target) {
            player.move(dx, dy);
            System.out.println("Moved onto a target");
        }
    }
}
