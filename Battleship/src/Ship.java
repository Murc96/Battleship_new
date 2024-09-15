public class Ship {
    private final int size;
    private int[][] positions;
    private boolean[] hit;

    public Ship(int size) {
        this.size = size;
        this.positions = new int[size][2];
        this.hit = new boolean[size];
    }

    public int getSize() {
        return size;
    }

    public void setPositions(int[][] positions) {
        if (positions.length == size) {
            this.positions = positions;
        }
    }

    public int[][] getPositions() {
        return positions;
    }

    public boolean isHitAt(int x, int y) {
        for (int i = 0; i < size; i++) {
            if (positions[i][0] == x && positions[i][1] == y) {
                hit[i] = true;
                return true;
            }
        }
        return false;
    }

    public boolean isSunk() {
        for (boolean h : hit) {
            if (!h) {
                return false;
            }
        }
        return true;
    }
}

