import model.Menu;
import model.User;
import model.Utility;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    // TODO Skriv något om det inte är några inlägg ännu
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        List<User> users = Utility.updatePostsList();
        User currentUser;
        String currentUserName = null;

        while (true) {

            boolean isUserNameEmpty = currentUserName == null;

            if (isUserNameEmpty) {
                currentUserName = Menu.userChoiceMenu(currentUserName, users);
            } else {
                currentUser = Utility.getCurrentUser(currentUserName, users);
                users = Menu.loggedInMenu(currentUserName, currentUser, users);
            }

        }
    }
}