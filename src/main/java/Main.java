import model.Menu;

public class Main {
    public static void main(String[] args) {
        String currentUserName = null;

        while (true) {
            if (currentUserName == null) {
                currentUserName = Menu.userChoiceMenu();
            } else {
                Menu.loggedInMenu(currentUserName);
            }
        }
    }
}