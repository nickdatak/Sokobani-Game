import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap();
        gameMap.loadFromFile("level1.txt");

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("--- Sokobani ---");

        while (isRunning) {
            gameMap.update();

            System.out.println("\nEnter move (w/a/s/d) or 'q' to quit: ");
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "w":
                    gameMap.movePlayer(0, -1);
                    break;
                case "a":
                    gameMap.movePlayer(-1, 0);
                    break;
                case "s":
                    gameMap.movePlayer(0, 1);
                    break;
                case "d":
                    gameMap.movePlayer(1, 0);
                    break;
                case "q":
                    isRunning = false;
                    System.out.println("Quitting game. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input. Please use w/a/s/d or q.");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }
}