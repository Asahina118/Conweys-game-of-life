public class Cell {
    public int xPos;
    public int yPos;
    public boolean alive;

    //alive will stay for rule application for current time frame
    public boolean aliveNextFrame;
    public boolean ruleApplied;

    public int lastRuleApplied = -1;

    public Cell(int x, int y){
        xPos = x;
        yPos = y;
        alive = false;
        aliveNextFrame = false;
        ruleApplied = false;
    }
   public int getNeighbourCells(int range) {
        if (range < 1)
            throw new IllegalArgumentException("range must be bigger 1");
        return Map.getInstance().getAliveCellsNumber(xPos - range, xPos + range, yPos - range, yPos + range) - 1;
   }

    public boolean getAlive() {
        return alive;
    }
    public void setAlive(boolean input) {
        alive = input;
    }
    public void setAliveNextFrame(boolean input) {
        aliveNextFrame = input;
    }
    public void setRuleApplied(boolean input) {
        ruleApplied = input;
    }
    public void setLastRuleApplied(int input) {
        lastRuleApplied = input;
    }
    public int getxPos() {
        return xPos;
    }
    public int getyPos() {
        return yPos;
    }
    public boolean getRuleApplied() { return ruleApplied; }
}
