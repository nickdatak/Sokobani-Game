public class Game {
    public static void main(String[] args) {
        //creating a grid example and moving player around a bit 
        int gridWidth = 5;
        int gridHeight = 5;
        Player player = new Player( new Position(3, 3),gridWidth, gridHeight);
        System.out.println("Game Grid");
        System.out.println("Grid size: " + gridWidth + "x" + gridHeight);
        System.out.println("Player position: " + player.getPosition());
        System.out.println(); 

        for (int y = 0; y < gridHeight; y++){
            for (int x = 0; x < gridWidth; x++) {
                if (player.getPosition()[0]==x && player.getPosition()[1]==y){
                    System.out.print("P ");  
                } else {
                    System.out.print(". "); 
                }
            }
            System.out.println(); 
        }}
}
