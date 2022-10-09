import com.fasterxml.jackson.databind.ObjectMapper;
import model.Menu;
import model.Post;
import model.User;
import model.Utility;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    // TODO check wrong input in every menu, to test for errors
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