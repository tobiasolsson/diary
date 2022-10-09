package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    static Path path = Paths.get("src/main/resources/diary.json");
    public static List<User> updatePostsList() throws IOException {
        // Returns empty List if nothing in JSON-file.
        // Print error if JSON-file is empty
        ObjectMapper mapper = new ObjectMapper();

        List<User> updatedList = new ArrayList<>();

        try {
            List<User> tmpUpdatedList = List.of(mapper.readValue(path.toFile(),
                    User[].class));
            updatedList.addAll(tmpUpdatedList);
        } catch (Exception e) {
            System.out.println("Inga inlägg ännu.");
        }

        return updatedList;
    }

    public static List<User> addNewEntry(Post newEntry, User user, List<User> users) throws IOException {
        // Update list of posts and write to JSON file, then return the new list to update main function
        ObjectMapper mapper = new ObjectMapper();

        // Update entries list in user
        updateUserEntries(newEntry, user);

        // Update user in user list
        updateUserList(user, users);

        // Write new user list to JSON
        mapper.writeValue(path.toFile(), users);

        // return updated users list with the new entry
        return users;
    }

    private static void updateUserList(User updatedUser, List<User> users) {
        for (User user: users) {
            if (user.getName().equalsIgnoreCase(updatedUser.getName())) user.setEntries(updatedUser.getEntries());
        }
    }

    static void updateUserEntries(Post newEntry, User user) {
        List<Post> userEntries = user.getEntries();
        userEntries.add(newEntry);
        user.setEntries(userEntries);
    }

    public static User getCurrentUser(String currentUserName, List<User> users) {
        for (User user:
             users) {
            if (user.getName().equalsIgnoreCase(currentUserName)) return user;
        }
        return null;
    }

    public static void writeNewUserToJSON(User newUser, List<User> users) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        users.add(newUser);

        mapper.writeValue(path.toFile(), users);
    }
}
