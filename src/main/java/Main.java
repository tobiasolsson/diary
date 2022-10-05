import com.fasterxml.jackson.databind.ObjectMapper;
import model.Menu;
import model.Post;
import model.User;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // TODO Add user JSON and let user create new User
    // TODO Let user log in and only then can you read (only that users entries) and write
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Test users
        List<User> users = new ArrayList<>();
        users.add(new User("Tobias"));

        List<Post> posts = updatePostsList();

        while (true) {
            Menu.displayStartMenu();
            try {
                int choiceOne = Integer.parseInt(scanner.nextLine());

                if (choiceOne == 1) {
                    // Before we list the entries, make sure list is updated from json-file
                    posts = updatePostsList();

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

                    // Update posts List<> to current values in JSON file
                    // returns new list of posts, so we can update posts list
                    posts = addNewEntry(newEntry);

                } else if (choiceOne == 3) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Använd endast siffror i meny valen!");
            }
        }
    }

    // TODO Put in own Class? service/utility
    static List<Post> updatePostsList() throws IOException {
        // Returns empty List if nothing in JSON-file.
        // Print error if JSON-file is empty
        ObjectMapper mapper = new ObjectMapper();

        List<Post> updatedList = new ArrayList<>();

        try {
            updatedList = List.of(mapper.readValue(Paths.get("src/main/resources/diary.json").toFile(),
                Post[].class));
        } catch (Exception e) {
            System.out.println("Inga inlägg ännu.");
        }

        return updatedList;
    }

    static List<Post> addNewEntry(Post newEntry) throws IOException {
        // Update list of posts and write to JSON file, then return the new list to update main function
        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get("src/main/resources/diary.json");

        // TODO Break out to own function when users are involved
        List<Post> updatedPosts = new ArrayList<>(updatePostsList());
        updatedPosts.add(newEntry);

        mapper.writeValue(path.toFile(), updatedPosts);


        return updatedPosts;
    }
}