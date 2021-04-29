import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

// represents the console user-interface
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
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("There was an exception thrown when trying to access the selected page. " +
                    "Let's try a different page.");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes parser and scanner, and generates an http request
    public void setUp() {
        parser = new Parser();
        input = new Scanner(System.in);
        makeHttpRequest();
    }

    // EFFECTS: requests a random wikipedia page
    private void makeHttpRequest() {
        try {
            HttpRequest.requestRandomPage(parser);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: console interface where the user decides if they want to view a given article
    public void home() throws URISyntaxException, IOException, InterruptedException {
        String command = checkArticlePrompt();

        if (command.equals("yes")) {
            System.out.println("Opening your web browser...");
            HttpRequest.requestPage(parser);
            Desktop.getDesktop().browse(new URI(parser.getUrl()));
            repeatPrompt();
        } else if (command.equals("no")) {
            makeHttpRequest();
            home();
        } else {
            checkArticlePrompt();
        }

    }

    // EFFECTS: console interface that asks the user if they want to view a given article
    private String checkArticlePrompt() {
        String command;
        System.out.println("Would you like to view the article titled " + parser.getTitle() +
                "? Enter yes or no.");
        command = input.next();
        command = command.toLowerCase();

        return command;
    }

    // EFFECTS: console interface where the user is asked if they want to find another random wiki page
    private void repeatPrompt() {
        String command;
        System.out.println("Would you like to visit another random article? Enter yes or no.");
        command = input.next();
        command = command.toLowerCase();

        if (command.equals("yes")) {
            new UI();
        } else if (command.equals("no")) {
            System.out.println("Closing program...");
            System.exit(0);
        } else {
            repeatPrompt();
        }
    }
}
