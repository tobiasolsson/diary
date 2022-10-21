package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FirstMenu {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * First menu in program. User either chooses a user or creates new one. Or quits.
     *
     * @return string of the username
     */
    public static String userChoiceMenu() {
        displayStartMenu();

        try {
            int userMenuChoice = Integer.parseInt(scanner.nextLine());

            switch (userMenuChoice) {
                case 1:
                    return chooseUser();
                case 2:
                    createNewUser();
                    break;
                case 3:
                    Utility.exitProgram();
                    break;
                default:
                    System.out.println("Använd siffrorna 1-3 för att välja i menyn");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Fel input, använd siffrorna 1-3");
        }
        return null;
    }

    /**
     *
     * @return String of the username that logged in
     */
    private static String chooseUser() {
        List<User> users = Utility.updatePostsList();

        if (users.size() > 0) {
            for (User user :
                    users) {
                System.out.println(user.name());
            }

            System.out.println();
            System.out.println("Skriv in ditt användare namn du vill logga in på: ");

            String chooseUser = scanner.nextLine();

            // Loop all users and set currentUser to user.getName so capitalisation is correct
            for (User user :
                    users) {
                if (user.name().equalsIgnoreCase(chooseUser)) {
                    return user.name();
                }
            }
        } else {
            System.out.println("Ingen användare finns, vänligen skapa en ny användare innan du loggar in!");
        }

        return null;
    }

    /**
     *
     * @throws IOException Jackson exception
     */
    private static void createNewUser() throws IOException {
        System.out.println("Skriv (1) för att skapa en ny användare");
        System.out.println("Skriv (2) för att gå tillbaka");

        int userCreationMenuChoice = Integer.parseInt(scanner.nextLine());

        if (userCreationMenuChoice == 1) {
            System.out.println("Vad vill du att din användare ska heta?");

            String newUserName = scanner.nextLine();

            if (Utility.checkIfUserIsAvailable(newUserName)) {
                User newUser = new User(newUserName, new ArrayList<>());
                // Update JSON with new user, so we can write entries to JSON
                Utility.writeNewUserToJSON(newUser);
            } else {
                System.out.println("Användarnamnet finns redan, välj ett annat");
            }
        }
    }

    /**
     * Display the first menu choices
     */
    private static void displayStartMenu() {
        System.out.println();
        System.out.println("Aktiv användare: Ingen");
        System.out.println("1. Välj användare");
        System.out.println("2. Skapa ny användare");
        System.out.println("3. Avluta");
        System.out.println();
    }

}
