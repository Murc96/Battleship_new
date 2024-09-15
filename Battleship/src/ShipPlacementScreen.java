import javax.swing.*;
import java.awt.*;

public class ShipPlacementScreen extends JFrame {
    private final JButton[][] gridButtons;
    private final Board playerBoard;
    private int shipSize = 2;
    private boolean horizontal = true;
    private int shipsPlaced = 0;
    private final int[] shipSizes = {5, 4, 3, 3, 2};

    public ShipPlacementScreen() {
        setTitle("Place Your Ships");
        setSize(500, 500);
        setLayout(new BorderLayout());

        playerBoard = new Board(10);
        JPanel gridPanel = new JPanel(new GridLayout(10, 10));
        gridButtons = new JButton[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gridButtons[i][j] = new JButton("-");
                int x = i;
                int y = j;
                gridButtons[i][j].addActionListener(e -> placeShip(x, y));
                gridPanel.add(gridButtons[i][j]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();

        JLabel sizeLabel = new JLabel("Select Ship Size:");
        Integer[] sizes = {2, 3, 3, 4, 5};
        JComboBox<Integer> sizeComboBox = new JComboBox<>(sizes);
        sizeComboBox.setSelectedIndex(0);
        sizeComboBox.addActionListener(e -> {
            Object selectedItem = sizeComboBox.getSelectedItem();
            shipSize = (selectedItem != null) ? (Integer) selectedItem : 2;
        });
        controlPanel.add(sizeLabel);
        controlPanel.add(sizeComboBox);

        JButton rotateButton = new JButton("Rotate Ship");
        rotateButton.addActionListener(e -> horizontal = !horizontal);
        controlPanel.add(rotateButton);

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> {
            if (shipsPlaced == shipSizes.length) {
                this.setVisible(false);
                new BattleshipGUI(new Game(10, playerBoard));
            } else {
                JOptionPane.showMessageDialog(this, "You need to place all your ships before starting the game.");
            }
        });
        controlPanel.add(startGameButton);

        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void placeShip(int x, int y) {
        if (shipsPlaced < shipSizes.length) {
            shipSize = shipSizes[shipsPlaced];
            if (playerBoard.canPlaceShip(shipSize, x, y, horizontal)) {
                Ship ship = new Ship(shipSize);
                playerBoard.addShip(ship, x, y, horizontal);
                for (int i = 0; i < shipSize; i++) {
                    if (horizontal) {
                        gridButtons[x][y + i].setText("S");
                        gridButtons[x][y + i].setEnabled(false);
                    } else {
                        gridButtons[x + i][y].setText("S");
                        gridButtons[x + i][y].setEnabled(false);
                    }
                }
                shipsPlaced++;
            } else {
                JOptionPane.showMessageDialog(this, "Cannot place ship here.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "All ships have been placed.");
        }
    }
}




