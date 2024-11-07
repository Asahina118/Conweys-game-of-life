public class Renderer {
    public String displayCharacterAlive;
    public String displayCharacterEmpty;
    private static Renderer instance;
    private Renderer(String displayCharacterAlive, String displayCharacterEmpty) {
        this.displayCharacterAlive = displayCharacterAlive;
        this.displayCharacterEmpty = displayCharacterEmpty;
    }
    public static Renderer getInstance() {
        if (instance == null) {
            throw new IllegalCallerException("No instance is found.");
        }
        return instance;
    }
    public static void buildRenderer(String displayCharacterAlive, String displayCharacterEmpty) {
        instance = new Renderer(displayCharacterAlive, displayCharacterEmpty);
    }

    public void render(){
        Cell cell;
        for (int y = Map.getInstance().size - 1; y >= 0; y--) {
            for (int x = 0; x < Map.getInstance().size; x++) {
                System.out.print(Map.getInstance().map.get(x).get(y).getAlive() ? displayCharacterAlive : displayCharacterEmpty);

                cell = Map.getInstance().map.get(x).get(y);

                if (cell.getAlive())
                    Debug.showStatus(cell);
            }

            System.out.println();
        }

    }

}
