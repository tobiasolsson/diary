import model.Menu;
import model.User;
import model.Utility;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    // TODO hur ska jag visa inläggen som nu eller bara titel och datum så får man välja om man vill läsa inlägget?
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // TODO byt till hashmap? <User, Post>?

        List<User> users = Utility.updatePostsList();
        User currentUser;
        String currentUserName = null;

        while (true) {
            boolean isUserNameEmpty = currentUserName == null;
            if (isUserNameEmpty) {
                currentUserName = Menu.userChoiceMenu(currentUserName, users);
            } else if (!isUserNameEmpty) {
                currentUser = Utility.getCurrentUser(currentUserName, users);
                users = Menu.loggedInMenu(currentUserName, currentUser, users);
            } else {
                break;

            }

        }
    }
}