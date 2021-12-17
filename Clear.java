import java.awt.Color;

public class Clear extends Element {
    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return Color.black;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWeight() {
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
        clear(grid, row, col);
    }

    private void clear(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(row).size(); j++) {
                grid.get(i).set(j, new Empty());
            }
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