import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static int DEFAULT_MAP_SIZE = 30;

    //In ms, 500000
    public static long TIME_FRAME = 500000;
    public static String displayAlive = "■";
    public static String displayDead = "□";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("d = default init; n = new init.");
        List<List<Integer>> initList;
        String choiceInput = scanner.next();

        if (choiceInput.equals("d")) {
            System.out.println("Using the default coordinates for the alive cells init.");
            System.out.println("Enter the choice for the default pattern.");

            initList = getDefaultInit();

        } else if (choiceInput.equals("n")) {
            initList = getInputInit();

        } else {
            throw new IllegalArgumentException("Choice is not recognized.");
        }

        System.out.println("Enter debug mode? ('y' for yes):");
        Debug.debugMode = scanner.next().equals("y")? true: false;

        initList.forEach(entry -> {
            Map.getInstance().updateAlive(entry.get(0), entry.get(1), true);
        });
        Debug.out("Map init success with size: " + Map.getInstance().getSize() + " and alive cells coordinates: ");
        Map.getAliveCellsCoordinates().forEach(coor -> {
            Debug.out("[" + coor.get(0) + ", " + coor.get(1) + "]");
        });

        Debug.out("Rule manager init...");

        List<Rule> rules = Arrays.asList(new Rule1(), new Rule2(), new Rule3(), new Rule4());
        RuleManager.builder(rules);
        Debug.out("Rule manager init success.");

        Debug.out("Renderer init...");
        Renderer.buildRenderer(displayAlive, displayDead);
        Debug.out("Renderer init success.");

        System.out.println("Start rendering...");

        try {
            Debug.out("Rendering begins.");
            while (true) {

                Debug.debugMessages.clear();

                Renderer.getInstance().render();
                Map.update();

                if (Debug.debugMode) {
                    Debug.debugMessages.forEach( message -> {
                        Debug.out(message);
                    });
                    Debug.out("Press 'n' for next frame.");
                    if (!scanner.next().equals("n")) break;
                } else {
                    TimeUnit.MICROSECONDS.sleep(TIME_FRAME);
                }

                //clear the last time frame
                for (int i = 0; i < 20; i++) {
                    System.out.println();
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Stimulation finished. Now terminating.");

    }
    public static List<List<Integer>> getInputInit() {
        Scanner scanner = new Scanner(System.in);
        List<List<Integer>> coordinates = new ArrayList<>();
        int inputCount = 0;

        System.out.print("Enter the map size: ");
        int inputSize = scanner.nextInt();
        Map.builder(inputSize);
        Debug.out("Map init success with size: " + Map.getInstance().getSize());

        System.out.println("Enter coordinates of alive cells. Enter 'q' for quitting. ");
        while (inputCount <= Map.getInstance().getSize()) {
            System.out.print("Input: ");
            String input = scanner.next();
            if (input.equals("q"))
                break;

            inputCount++;
            List<Integer> coordinate = Stream.of(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt).collect(Collectors.toList());

            coordinates.add(coordinate);
        }
        return coordinates;
    }
    public static List<List<Integer>> getDefaultInit() {

        System.out.println("'1' : gliders");
        System.out.println("'2' : blinkers");
        System.out.println("'3' : osillators");
        System.out.println("'4' : Gosper glider gun");

        int choice = new Scanner(System.in).nextInt();

        switch (choice) {
            case 1:
                Map.builder(DEFAULT_MAP_SIZE);
                return gliders();

            case 2:
                Map.builder(DEFAULT_MAP_SIZE);
                return blinkers();

            case 3:
                Map.builder(DEFAULT_MAP_SIZE);
                return pulsars();

            case 4:
                Map.builder(100);
                return GosperGliderGun();

            default:
                System.out.println("Cannot recognize input. Returning the default option: gliders");
                return gliders();
        }

    }
    public static List<List<Integer>> gliders() {
        List<List<Integer>> returnList = new ArrayList<>();

        returnList.add(List.of(20, 20));
        returnList.add(List.of(21, 20));
        returnList.add(List.of(22, 20));
        returnList.add(List.of(22, 21));
        returnList.add(List.of(21, 22));

        return returnList;
    }
    public static List<List<Integer>> blinkers() {
        List<List<Integer>> returnList = new ArrayList<>();

        returnList.add(List.of(20, 20));
        returnList.add(List.of(20, 21));
        returnList.add(List.of(20, 22));

        return returnList;
    }
    public static List<List<Integer>> pulsars() {
        List<List<Integer>> returnList = new ArrayList<>();

        //left bottom
        returnList.add(List.of(12,12));
        returnList.add(List.of(12,13));
        returnList.add(List.of(12,14));

        returnList.add(List.of(14,10));
        returnList.add(List.of(15,10));
        returnList.add(List.of(16,10));

        returnList.add(List.of(20,10));
        returnList.add(List.of(21,10));
        returnList.add(List.of(22,10));

        returnList.add(List.of(24,12));
        returnList.add(List.of(24,13));
        returnList.add(List.of(24,14));

        returnList.add(List.of(24,18));
        returnList.add(List.of(24,19));
        returnList.add(List.of(24,20));

        returnList.add(List.of(22,22));
        returnList.add(List.of(21,22));
        returnList.add(List.of(20,22));

        returnList.add(List.of(16,22));
        returnList.add(List.of(15,22));
        returnList.add(List.of(14,22));

        returnList.add(List.of(12,20));
        returnList.add(List.of(12,19));
        returnList.add(List.of(12,18));

        //inside

        //left bottom, anti-clockwise
        returnList.add(List.of(14,15));
        returnList.add(List.of(15,15));
        returnList.add(List.of(16,15));

        returnList.add(List.of(17,14));
        returnList.add(List.of(17,13));
        returnList.add(List.of(17,12));

        returnList.add(List.of(19,14));
        returnList.add(List.of(19,13));
        returnList.add(List.of(19,12));

        returnList.add(List.of(20,15));
        returnList.add(List.of(21,15));
        returnList.add(List.of(22,15));

        returnList.add(List.of(20,17));
        returnList.add(List.of(21,17));
        returnList.add(List.of(22,17));

        returnList.add(List.of(19,18));
        returnList.add(List.of(19,19));
        returnList.add(List.of(19,20));

        returnList.add(List.of(17,18));
        returnList.add(List.of(17,19));
        returnList.add(List.of(17,20));

        returnList.add(List.of(16,17));
        returnList.add(List.of(15,17));
        returnList.add(List.of(14,17));

        return returnList;
    }
    public static List<List<Integer>> GosperGliderGun() {
        List<List<Integer>> returnList = new ArrayList<>();

        returnList.add(List.of(30, 30));
        returnList.add(List.of(30, 31));
        returnList.add(List.of(31, 30));
        returnList.add(List.of(31, 31));

        returnList.add(List.of(40, 30));
        returnList.add(List.of(40, 31));
        returnList.add(List.of(40, 29));

        returnList.add(List.of(41, 28));
        returnList.add(List.of(41, 32));

        returnList.add(List.of(42, 33));
        returnList.add(List.of(42, 27));

        returnList.add(List.of(43, 33));
        returnList.add(List.of(43, 27));

        returnList.add(List.of(44, 30));

        returnList.add(List.of(45, 32));
        returnList.add(List.of(45, 28));

        returnList.add(List.of(46, 31));
        returnList.add(List.of(46, 30));
        returnList.add(List.of(46, 29));

        returnList.add(List.of(47, 30));

        returnList.add(List.of(50, 31));
        returnList.add(List.of(50, 32));
        returnList.add(List.of(50, 33));

        returnList.add(List.of(51, 31));
        returnList.add(List.of(51, 32));
        returnList.add(List.of(51, 33));

        returnList.add(List.of(52, 34));
        returnList.add(List.of(52, 30));

        returnList.add(List.of(54, 34));
        returnList.add(List.of(54, 35));
        returnList.add(List.of(54, 30));
        returnList.add(List.of(54, 29));

        returnList.add(List.of(64, 31));
        returnList.add(List.of(64, 32));

        returnList.add(List.of(65, 31));
        returnList.add(List.of(65, 32));

        return returnList;
    }
}