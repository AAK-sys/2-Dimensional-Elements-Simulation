
import java.awt.Color;

public class Sand extends Element {
    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return Color.YELLOW;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWeight() {
        return 100;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col) {

        if (row < grid.size() - 1 && grid.get(row + 1).get(col).getWeight() < grid.get(row).get(col).getWeight()) {
            grid.get(row).set(col, grid.get(row + 1).get(col));
            grid.get(row + 1).set(col, new Sand());

        } else if (row < grid.size() - 1 && col < grid.get(1).size() - 1
                && grid.get(row + 1).get(col + 1).getWeight() < grid.get(row).get(col).getWeight()) {
            grid.get(row).set(col, grid.get(row + 1).get(col + 1));
            grid.get(row + 1).set(col + 1, new Sand());

        } else if (row < grid.size() - 1 && col > 0
                && grid.get(row + 1).get(col - 1).getWeight() < grid.get(row).get(col).getWeight()) {
            grid.get(row).set(col, grid.get(row + 1).get(col - 1));
            grid.get(row + 1).set(col - 1, new Sand());
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