import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class UI {
    private Parser parser;
    private Scanner input;

    public UI() {
        setUp();
        try {
            home();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            System.out.println("There was a problem accessing the link. Let's try a different page.");
            new UI();
        }
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
    public void home() throws URISyntaxException, IOException {
        String command = prompt();

        if (command.equals("yes")) {
            System.out.println("Here's the link!");
            Desktop.getDesktop().browse(new URI("https://stackoverflow.com/questions/748895/how-do-you-open-web-pages-in-java"));
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
