import javax.swing.*;
import java.awt.*;

public class BattleshipGUI extends JFrame {
    private final JButton[][] playerButtons;
    private final JButton[][] cpuButtons;
    private final Board playerBoard;
    private final Board cpuBoard;
    private final Game game;

    public BattleshipGUI(Game game) {
        this.game = game;
        this.playerBoard = game.getPlayerBoard();
        this.cpuBoard = game.getCpuBoard();

        setTitle("Battleship Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(1, 2, 20, 0));

        // Player Panel
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BorderLayout());

        JLabel playerLabel = new JLabel("Your Board", SwingConstants.CENTER);
        playerPanel.add(playerLabel, BorderLayout.NORTH);

        JPanel playerGrid = new JPanel();
        playerGrid.setLayout(new GridLayout(10, 10));
        playerButtons = new JButton[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                playerButtons[i][j] = new JButton("-");
                playerButtons[i][j].setEnabled(false);
                playerGrid.add(playerButtons[i][j]);
            }
        }
        refreshPlayerBoard();
        playerPanel.add(playerGrid, BorderLayout.CENTER);

        // CPU Panel
        JPanel cpuPanel = new JPanel();
        cpuPanel.setLayout(new BorderLayout());

        JLabel cpuLabel = new JLabel("Enemy Board", SwingConstants.CENTER);
        cpuPanel.add(cpuLabel, BorderLayout.NORTH);

        JPanel cpuGrid = new JPanel();
        cpuGrid.setLayout(new GridLayout(10, 10));
        cpuButtons = new JButton[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cpuButtons[i][j] = new JButton("-");
                int x = i;
                int y = j;
                cpuButtons[i][j].addActionListener(e -> {
                    if (!cpuButtons[x][y].isEnabled()) {
                        JOptionPane.showMessageDialog(this, "You have already attacked this coordinate.");
                        return;
                    }
                    boolean hit = cpuBoard.hit(x, y);
                    if (hit) {
                        cpuButtons[x][y].setText("X");
                    } else {
                        cpuButtons[x][y].setText("O");
                    }
                    cpuButtons[x][y].setEnabled(false);

                    // CPU's turn
                    int[] cpuShot = game.cpuShots();
                    updatePlayerBoard(cpuShot[0], cpuShot[1]);
                    checkForWin();
                });
                cpuGrid.add(cpuButtons[i][j]);
            }
        }
        cpuPanel.add(cpuGrid, BorderLayout.CENTER);

        gamePanel.add(playerPanel);
        gamePanel.add(cpuPanel);

        add(gamePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void refreshPlayerBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                char status = playerBoard.getBoard()[i][j];
                if (status == 'S') {
                    playerButtons[i][j].setText("S");
                } else if (status == 'X') {
                    playerButtons[i][j].setText("X");
                } else if (status == 'O') {
                    playerButtons[i][j].setText("O");
                } else {
                    playerButtons[i][j].setText("-");
                }
            }
        }
    }

    private void updatePlayerBoard(int x, int y) {
        char status = playerBoard.getBoard()[x][y];
        if (status == 'X') {
            playerButtons[x][y].setText("X");
        } else if (status == 'O') {
            playerButtons[x][y].setText("O");
        }
    }

    private void checkForWin() {
        if (cpuBoard.allShipsSunk()) {
            JOptionPane.showMessageDialog(this, "Congratulations! You have sunk all enemy ships!");
            System.exit(0);
        } else if (playerBoard.allShipsSunk()) {
            JOptionPane.showMessageDialog(this, "You have been defeated. All your ships are sunk.");
            System.exit(0);
        }
    }
}



