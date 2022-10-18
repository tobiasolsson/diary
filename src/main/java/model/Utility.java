package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides various utility functions to be used throughout the program
 */
public class Utility {
    /**
     * Path to the JSON file for read and write access
     */
    static Path path = Paths.get("src/main/resources/diary.json");

    /**
     * Update the users and entries
     *
     * If JSON is empty, return empty User List, otherwise it returns a List of what's in the JSON
     *
     * @return List<User>
     */
    public static List<User> updatePostsList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<User> updatedList = new ArrayList<>();

        // try/catch, to handle if JSON is empty
        try {
            List<User> tmpUpdatedList = List.of(mapper.readValue(path.toFile(),
                    User[].class));
            updatedList.addAll(tmpUpdatedList);
        } catch (Exception e) {
            System.out.println("Kunde inte läsa databas");
        }

        return updatedList;
    }

    /**
     * Update list of posts and write to JSON file, then return the new list to update main function
     *
     * @param newEntry the new entry to add to the users entries List
     * @param user the current user, so we add entry to correct user
     * @param users the list of users, that we can use to update JSON
     * @return users is the new updates list of users
     * @throws IOException
     */
    public static List<User> addNewEntry(Post newEntry, User user, List<User> users) throws IOException {
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

    /**
     * Return current User based on currentUserName, return null if no user is chosen
     *
     * @param currentUserName
     * @param users
     * @return current User or null
     */
    public static User getCurrentUser(String currentUserName, List<User> users) {
        for (User user:
             users) {
            if (user.getName().equalsIgnoreCase(currentUserName)) return user;
        }
        return null;
    }

    /**
     * Update JSON with new user, needed if there are no users
     *
     * @param newUser user to be added to List
     * @param users List of users
     * @throws IOException
     */
    public static void writeNewUserToJSON(User newUser, List<User> users) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        users.add(newUser);

        mapper.writeValue(path.toFile(), users);
    }
}
