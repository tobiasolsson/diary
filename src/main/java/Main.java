import model.Menu;
import model.Post;
import model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // TODO Allow mulit lines in diary entry, 2 empty rows = save
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load menus
        Menu menuOptions = new Menu();

        // Test users
        List<User> users = new ArrayList<>();
        users.add(new User("Tobias"));

        // Test posts
        // TODO Format long strings on display
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("test1", "test1", LocalDate.now()));
        posts.add(new Post("test2", "test2", LocalDate.now()));
        posts.add(new Post("Lorem ipsum dolor sit amet", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. At erat pellentesque adipiscing commodo elit at. Non nisi est sit amet. Fermentum posuere urna nec tincidunt. A iaculis at erat pellentesque adipiscing commodo elit. In vitae turpis massa sed elementum. Nulla pharetra diam sit amet nisl. Molestie at elementum eu facilisis sed odio morbi quis commodo. Fringilla ut morbi tincidunt augue interdum velit. Lorem ipsum dolor sit amet consectetur adipiscing elit. Blandit massa enim nec dui nunc. Quisque sagittis purus sit amet volutpat.", LocalDate.now()));

        while (true) {
            menuOptions.displayStartMenu();
            try {
                int choiceOne = Integer.parseInt(scanner.nextLine());

                if (choiceOne == 1) {
                    for (Post post : posts) {
                        System.out.printf("%s - %s \n", post.getDate(), post.getTitle());
                        System.out.printf("%s\n", post.getBody());
                    }
                } else if (choiceOne == 2) {
                    System.out.println("Ange din titel:");
                    String postTitle = scanner.nextLine();

                    System.out.println("Skriv ditt inlägg: ");
                    String postBody = scanner.nextLine();

                    posts.add(new Post(postTitle, postBody, LocalDate.now()));
                } else if (choiceOne == 3) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Använd endast siffror i meny valen!");
            }
        }
    }
}