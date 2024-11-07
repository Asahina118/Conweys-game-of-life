public class Rule2 implements Rule{
    int range = 1;
    @Override
    public void apply(Cell cell) {
        if (!cell.getAlive() || cell.getRuleApplied()) {
            return;
        }
        int xStart = cell.getxPos() - range;
        int xEnd = cell.getxPos() + range;
        int yStart = cell.getyPos() - range;
        int yEnd = cell.getyPos() + range;

        // -1 for overcounting the current cell
        int aliveCells = Map.getInstance().getAliveCellsNumber(xStart, xEnd, yStart, yEnd) - 1;
        if (aliveCells >= RuleManager.surviveLowerBound && aliveCells <= RuleManager.surviveUpperBound){
            cell.setAliveNextFrame(true);
            cell.setRuleApplied(true);
            cell.setLastRuleApplied(2);
        }
    }

    public void setRange(int range) {
        if (range < 1)
            throw new IllegalArgumentException("range must be >=2.");
        this.range = range;
    }
}
