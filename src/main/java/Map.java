import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Map {
    public static List<List<Cell>> map = null;
    public static int size = 0;
    private static Map instance;
    private Map(int sizeInput) {
        this.size = sizeInput;
        map = IntStream.range(0, size)
                .mapToObj(x -> IntStream.range(0, size)
                        .mapToObj(y -> new Cell(x, y))
                        .toList())
                .toList();

    }
    public static Map getInstance(){
        if (instance == null || size == 0 || map == null)
            throw new IllegalCallerException("Map is not properly initialized. (either instance is null or size is 0 or map is null.");

        return instance;
    }
    public static void builder(int size) {
        instance = new Map(size);
    }
    public static void update() {
        map.forEach(row -> {
            row.forEach(entry -> {
                RuleManager.getInstance().applyRules(entry);
            });
        });

        map.forEach(row -> {
            row.forEach(entry -> {
                entry.setAlive(entry.aliveNextFrame);
                entry.setRuleApplied(false);
            });
        });
    }

    public void updateAlive(int xPos, int yPos, boolean newStatus) {
        map.get(xPos).get(yPos).setAlive(newStatus);
    }

    public int getAliveCellsNumber(int xStart, int xEnd, int yStart, int yEnd) {
        int count = 0;
        xStart = inBoundary(xStart);
        xEnd = inBoundary(xEnd);
        yStart = inBoundary(yStart);
        yEnd = inBoundary(yEnd);

        for (int i = xStart; i <= xEnd; i++){
            for (int j = yStart; j <= yEnd; j++){
                if (map.get(i).get(j).getAlive())
                    count++;

            }
        }

        return count;
    }

    public static List<List<Integer>> getAliveCellsCoordinates() {
        List<List<Integer>> aliveCoordinates = new ArrayList<>();
        for (int x = 0; x < Map.size; x++) {
            for (int y = 0; y < Map.size; y++) {
                if (map.get(x).get(y).getAlive() == true)
                    aliveCoordinates.add(Arrays.asList(x, y));
            }
        }

        return aliveCoordinates;
    }
    public int getSize(){
        return size;
    }
    public int inBoundary(int input) {
        if (input < 0)
            return 0;

        if (input >= size)
            return size - 1;

        return input;
    }
}