import java.io.IOException;
import java.util.Scanner;

public class UI {
    private Parser parser;
    private Scanner input;

    public UI() {
        setUp();
        home();
    }

    // MODIFIES: this
    // EFFECTS: initializes parser and scanner, and generates an http request
    public void setUp() {
        parser = new Parser();
        input = new Scanner(System.in);
        makeHttpRequest();
    }

    private void makeHttpRequest() {
        try {
            HttpRequest.request(parser);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS:
    public void home() {
        String command = prompt();

        if (command.equals("yes")) {
            System.out.println("Here's the link!");
        } else if (command.equals("no")) {
            makeHttpRequest();
            home();
        } else {
            prompt();
        }

    }

    private String prompt() {
        String command;
        System.out.println("Would you like to view the article titled " + parser.getTitle() +
                "? Enter yes or no.");
        command = input.next();
        command = command.toLowerCase();

        return command;
    }
}
