import model.LoggedInMenu;
import model.FirstMenu;

public class Main {
    public static void main(String[] args) {
        String currentUserName = null;

        while (true) {
            if (currentUserName == null) {
                currentUserName = FirstMenu.userChoiceMenu();
            } else {
                LoggedInMenu.loggedInMenu(currentUserName);
            }
        }
    }
}