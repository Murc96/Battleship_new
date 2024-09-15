import java.util.ArrayList;
import java.util.List;

public class Board {
    private final char[][] board;
    private final List<Ship> ships;
    private final List<int[]> missedShots; // To store missed shots

    public Board(int size) {
        this.board = new char[size][size];
        this.ships = new ArrayList<>();
        this.missedShots = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean addShip(Ship ship, int startX, int startY, boolean horizontal) {
        if (canPlaceShip(ship.getSize(), startX, startY, horizontal)) {
            int[][] positions = new int[ship.getSize()][2];
            for (int i = 0; i < ship.getSize(); i++) {
                if (horizontal) {
                    positions[i][0] = startX;
                    positions[i][1] = startY + i;
                    board[startX][startY + i] = 'S';
                } else {
                    positions[i][0] = startX + i;
                    positions[i][1] = startY;
                    board[startX + i][startY] = 'S';
                }
            }
            ship.setPositions(positions);
            ships.add(ship);
            return true;
        }
        return false;
    }

    public boolean canPlaceShip(int size, int startX, int startY, boolean horizontal) {
        if (horizontal) {
            if (startY + size > board.length) return false;
            for (int i = 0; i < size; i++) {
                if (board[startX][startY + i] == 'S') return false;
            }
        } else {
            if (startX + size > board.length) return false;
            for (int i = 0; i < size; i++) {
                if (board[startX + i][startY] == 'S') return false;
            }
        }
        return true;
    }

    public boolean hit(int x, int y) {
        if (board[x][y] == 'S') {
            board[x][y] = 'X';
            for (Ship ship : ships) {
                if (ship.isHitAt(x, y)) {
                    return true;
                }
            }
        } else if (board[x][y] == '-') {
            board[x][y] = 'O';
            missedShots.add(new int[]{x, y});
        }
        return false;
    }

    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public boolean collisionCheck(int x, int y) {
        return board[x][y] == 'S';
    }

    public List<int[]> getMissedShots() {
        return missedShots;
    }
}

