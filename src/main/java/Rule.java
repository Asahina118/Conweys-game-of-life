import java.util.ArrayList;
import java.util.List;

public interface Rule {
//    Any live cell with fewer than two live neighbours dies, as if by underpopulation.
//    Any live cell with two or three live neighbours lives on to the next generation.
//    Any live cell with more than three live neighbours dies, as if by overpopulation.
//    Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
    public void apply(Cell cell);
}
