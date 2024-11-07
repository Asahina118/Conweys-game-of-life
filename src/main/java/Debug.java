import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Debug {
    public static void out(Serializable output) {
        if (debugMode)
            System.out.println(output);
    }

    public static void showStatus(Cell cell) {
        //if (!debugMode) return;

        int range = 1;
        int xStart = cell.getxPos() - range;
        int xEnd = cell.getxPos() + range;
        int yStart = cell.getyPos() - range;
        int yEnd = cell.getyPos() + range;

        debugMessages.add("Cell at (" + cell.xPos + ", " + cell.yPos + ") with aliveCellsNumber = " + Map.getInstance().getAliveCellsNumber(xStart, xEnd, yStart, yEnd) +
                "; Rule" + cell.lastRuleApplied + " is applied.");

    }
    public static boolean debugMode = false;

    public static List<String> debugMessages = new ArrayList<>();

}
