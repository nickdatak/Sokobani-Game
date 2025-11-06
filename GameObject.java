abstract class GameObject{

    private Position position;
    
    public GameObject(Position Pos){
        this.position = Pos; 
    }

    public int[] getPosition(){
        int[] positions = new int[2];
        positions[0] = position.getx();
        positions[1] = position.gety();
        return positions;
    }

    public Position getPositionObject() {
        return position;
    }   

    abstract String getSymbol();
}

