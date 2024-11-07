public class Rule4 implements Rule{
    int range = 1;
    @Override
    public void apply(Cell cell) {
        if (cell.getAlive() || cell.getRuleApplied()) {
            return;
        }

        int xStart = cell.getxPos() - range;
        int xEnd = cell.getxPos() + range;
        int yStart = cell.getyPos() - range;
        int yEnd = cell.getyPos() + range;

        int aliveCells = Map.getInstance().getAliveCellsNumber(xStart, xEnd, yStart, yEnd);

        if (aliveCells == RuleManager.reproductionNumber) {
            cell.setAliveNextFrame(true);
            cell.setRuleApplied(true);
            cell.setLastRuleApplied(4);
        }
    }

    public void setRange(int range) {
        if (range < 1)
            throw new IllegalArgumentException("range must be >=2.");
        this.range = range;
    }
}
