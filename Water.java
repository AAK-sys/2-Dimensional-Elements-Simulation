import java.awt.Color;
import java.util.Random;

public class Water extends Element {
    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return Color.RED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWeight() {
        return 90;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
        double dir = Math.random() * 100 % 4;
        if (dir < 1 && row < grid.size() - 1
                && grid.get(row + 1).get(col).getWeight() < grid.get(row).get(col).getWeight()) {
            grid.get(row).set(col, grid.get(row + 1).get(col));
            grid.get(row + 1).set(col, new Water());
        }
        if (dir < 2 && col < grid.get(1).size() - 1
                && grid.get(row).get(col + 1).getWeight() < grid.get(row).get(col).getWeight()) {
            grid.get(row).set(col, grid.get(row).get(col + 1));
            grid.get(row).set(col + 1, new Water());
        }
        if (dir < 3 && col > 0 && grid.get(row).get(col - 1).getWeight() < grid.get(row).get(col).getWeight()) {
            grid.get(row).set(col, grid.get(row).get(col - 1));
            grid.get(row).set(col - 1, new Water());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean push(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
        // metal can't be pushed
        return false;
    }
}