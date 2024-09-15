import java.util.Scanner;

public class Player {

    private String name;
    private Scanner scanner;

    public Player(String name) {

        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    public String getName() {

        return name;
    }

}
