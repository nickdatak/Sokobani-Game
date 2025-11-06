import java.util.ArrayList;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        //creating a grid example and moving player around a bit 
        int gridWidth = 5;
        int gridHeight = 5;

        //creating objects here
        Player player = new Player( new Position(3, 3),gridWidth, gridHeight);
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



        System.out.println("Game Grid");
        System.out.println("Grid size: " + gridWidth + "x" + gridHeight);
        System.out.println("Player position: " + player.getPositionObject().toString()); 
        System.out.println(); 

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

}
