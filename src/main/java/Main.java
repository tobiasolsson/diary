import com.fasterxml.jackson.databind.ObjectMapper;
import model.Menu;
import model.Post;
import model.User;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // TODO 1. Save entries to JSON, do not overwrite old entries (check date does not change)
    // ....... Antingen så hämtar jag JSON till en List<>, eler så finns det en JSON metod för att appenda data
    // TODO 2. Read from JSON file the entries
    // TODO 3. Add user JSON and let user create new User
    // TODO 4. Let user log in and only then can you read (only that users entries) and write
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Test users
        List<User> users = new ArrayList<>();
        users.add(new User("Tobias"));

        // Test posts
        // TODO Format long strings on display
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("test1", "test1", LocalDate.now().toString()));
        posts.add(new Post("test2", "test2", LocalDate.now().toString()));
        posts.add(new Post("Lorem ipsum dolor sit amet", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. At erat pellentesque adipiscing commodo elit at. Non nisi est sit amet. Fermentum posuere urna nec tincidunt. A iaculis at erat pellentesque adipiscing commodo elit. In vitae turpis massa sed elementum. Nulla pharetra diam sit amet nisl. Molestie at elementum eu facilisis sed odio morbi quis commodo. Fringilla ut morbi tincidunt augue interdum velit. Lorem ipsum dolor sit amet consectetur adipiscing elit. Blandit massa enim nec dui nunc. Quisque sagittis purus sit amet volutpat.", LocalDate.now().toString()));

        while (true) {
            Menu.displayStartMenu();
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

                    String currentDate = LocalDate.now().toString();

                    Post newEntry = new Post(postTitle, postBody, currentDate);

                    addNewEntry(newEntry);

                } else if (choiceOne == 3) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Använd endast siffror i meny valen!");
            }
        }
    }

    static void addNewEntry(Post newEntry) throws IOException {
        ObjectMapper mapper = new ObjectMapper();


        mapper.writeValue(Paths.get("src/main/resources/diary.json").toFile(), newEntry);
    }
}