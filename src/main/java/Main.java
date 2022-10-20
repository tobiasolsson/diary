import model.Menu;
import model.User;
import model.Utility;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

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