import model.Menu;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String currentUserName = null;

        while (true) {
            if (currentUserName == null) {
                currentUserName = Menu.userChoiceMenu(null);
            } else {
                Menu.loggedInMenu(currentUserName);
            }

        }
    }
}