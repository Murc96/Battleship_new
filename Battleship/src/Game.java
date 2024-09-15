import java.util.*;

public class Game {
    private final Board playerBoard;
    private final Board cpuBoard;
    private final Set<String> cpuAttacks;

    public Game(int size, Board playerBoard) {
        this.playerBoard = playerBoard;
        this.cpuBoard = new Board(size);
        this.cpuAttacks = new HashSet<>();
        setupCPUShips(size);
    }

    private void setupCPUShips(int size) {
        Random rand = new Random();
        int[] shipSizes = {5, 4, 3, 3, 2}; // Example ship sizes
        for (int shipSize : shipSizes) {
            boolean placed = false;
            while (!placed) {
                int x = rand.nextInt(size);
                int y = rand.nextInt(size);
                boolean horizontal = rand.nextBoolean();
                Ship ship = new Ship(shipSize);
                placed = cpuBoard.addShip(ship, x, y, horizontal);
            }
        }
    }

    public int[] cpuShots() {
        Random rand = new Random();
        int x, y;
        String coord;
        do {
            x = rand.nextInt(10);
            y = rand.nextInt(10);
            coord = x + "," + y;
        } while (cpuAttacks.contains(coord));

        cpuAttacks.add(coord);
        playerBoard.hit(x, y);
        return new int[]{x, y};
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public Board getCpuBoard() {
        return cpuBoard;
    }
}

