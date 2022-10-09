package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static List<User> updatePostsList() throws IOException {
        // Returns empty List if nothing in JSON-file.
        // Print error if JSON-file is empty
        ObjectMapper mapper = new ObjectMapper();

        List<User> updatedList = new ArrayList<>();

        try {
            List<User> tmpUpdatedList = List.of(mapper.readValue(Paths.get("src/main/resources/diary.json").toFile(),
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
        Path path = Paths.get("src/main/resources/diary.json");

        // Update entries list in user
        User updatedUser = updateUserEntries(newEntry, user);

        // Update user in user list
        List<User> updatedUsers = updateUserList(updatedUser, users);

        // Write new user list to JSON
        mapper.writeValue(path.toFile(), updatedUsers);

        // return updated users list with the new entry
        return updatedUsers;
    }

    private static List<User> updateUserList(User updatedUser, List<User> users) {
        for (User user: users) {
            if (user.getName().equalsIgnoreCase(updatedUser.getName())) user.setEntries(updatedUser.getEntries());
        }
        return users;
    }

    static User updateUserEntries(Post newEntry, User user) {
        List<Post> userEntries = user.getEntries();
        userEntries.add(newEntry);
        user.setEntries(userEntries);

        return user;
    }

    public static User getCurrentUser(String currentUserName, List<User> users) {
        for (User user:
             users) {
            if (user.getName().equalsIgnoreCase(currentUserName)) return user;
        }
        return null;
    }
}
