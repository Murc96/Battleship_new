import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Battleship Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Battleship", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 18));
        add(startButton, BorderLayout.CENTER);

        startButton.addActionListener(e -> {
            this.setVisible(false);
            new ShipPlacementScreen();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}


