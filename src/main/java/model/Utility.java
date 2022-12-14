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
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Path path = Paths.get("src/main/resources/diary.json");
    private static List<User> users;

    /**
     * Update the users and entries*
     * If JSON is empty, return empty User List, otherwise it returns a List of what's in the JSON
     *
     * @return list of users
     */
    public static List<User> updatePostsList() {
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
     * @param currentUser the current user, so we add entry to correct user
     * @throws IOException handle Jackson error
     */
    public static void addNewEntry(Entry newEntry, User currentUser) throws IOException {
        // Get current users and entries
        users = updatePostsList();

        // filter users list for the current user
        // add newEntry when user is found
        users.stream()
                .filter(user -> user.name().equalsIgnoreCase(currentUser.name()))
                .forEach(user -> user.entries().add(newEntry));

        // Write new user list to JSON
        mapper.writeValue(path.toFile(), users);
    }

    /**
     * Return current User based on currentUserName, return null if no user is chosen
     *
     * @param currentUserName string of the current username
     * @return current User or null
     */
    public static User getCurrentUser(String currentUserName) {
        users = updatePostsList();
        for (User user:
             users) {
            if (user.name().equalsIgnoreCase(currentUserName)) return user;
        }
        return null;
    }

    /**
     * Update JSON with new user, needed if there are no users and JSON is empty so program don't crash
     *
     * @param newUser user to be added to List
     * @throws IOException to handle Jackson error
     */
    public static void writeNewUserToJSON(User newUser) throws IOException {
        users = updatePostsList();

        users.add(newUser);

        mapper.writeValue(path.toFile(), users);
    }

    /**
     * Check if username already exists to avoid conflicting usernames
     *
     * @param userName input from user
     * @return false if matching username exists, otherwise true if username is available
     */
    public static boolean checkIfUserIsAvailable(String userName) {
        users = updatePostsList();

        for (User user : users) {
            if (user.name().equalsIgnoreCase(userName)) return false;
        }
        return true;
    }

    /**
     * Prints a message and exits with code 0
     */
    public static void exitProgram() {
        System.out.println("Välkommen åter!");
        System.exit(0);
    }
}
