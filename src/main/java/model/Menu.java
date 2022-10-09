package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static String userChoiceMenu(String currentUser, List<User> users) {
        Scanner scanner = new Scanner(System.in);

        Menu.displayStartMenu(currentUser);
        try {
            int userMenuChoice = Integer.parseInt(scanner.nextLine());

            // List all users
            if (userMenuChoice == 1) {
                if (users.size() > 0) {
                    for (User user :
                            users) {
                        System.out.println(user.getName());
                    }

                    String chooseUser = scanner.nextLine();

                    // Loop all users and set currentUser to user.getName so capitalisation is correct
                    for (User user :
                            users) {
                        if (user.getName().equalsIgnoreCase(chooseUser)) {
                            return user.getName();
                        }
                    }
                } else {
                    System.out.println("Ingen användare finns, vänligen skapa en ny användare innan du loggar in!");
                }
            } else if (userMenuChoice == 2) {
                // TODO Create new user, then back to main menu

            } else if (userMenuChoice == 3) {
                // TODO exit
            }

        } catch (Exception e) {
            System.out.println("Oj");
        }
        return null;
    }

    public static List<User> loggedInMenu(String currentUser, User user, List<User> users) {
        Scanner scanner = new Scanner(System.in);
        // TODO lift this entire block to a class
        // if user == null display start menu
        // if user == someone, display entriesMenu
        Menu.displayEntriesMenu(currentUser);
        try {
            int entriesMenuChoice = Integer.parseInt(scanner.nextLine());

            if (entriesMenuChoice == 1) {
                // Before we list the entries, make sure list is updated from json-file
                users = Utility.updatePostsList();

                for (Post post : user.getEntries()) {
                    System.out.printf("%s - %s \n", post.getDate(), post.getTitle());
                    System.out.printf("%s\n", post.getBody());
                }

            } else if (entriesMenuChoice == 2) {
                System.out.println("Ange din titel:");
                String postTitle = scanner.nextLine();

                System.out.println("Skriv ditt inlägg: ");
                String postBody = scanner.nextLine();

                String currentDate = LocalDate.now().toString();

                Post newEntry = new Post(postTitle, postBody, currentDate);

                // Update posts List<> to current values in JSON file
                // returns new list of posts, so we can update posts list
                //user.setEntries(Utility.addNewEntry(newEntry, user, users));
                users = Utility.addNewEntry(newEntry, user, users);
            } else if (entriesMenuChoice == 3) {
                System.out.println("Välkommen åter!");
            }
        } catch (Exception e) {
            System.out.println("Använd endast siffror i meny valen!");
        }
        return users;
    }

    public static void displayStartMenu(String userName) {
        // TODO Dynamically display user name or if none is selected
        if (userName == null) {
            System.out.println("Aktiv användare: Ingen");
        } else {
            System.out.println("Aktiv användare: " + userName);
        }
        System.out.println("1. Välj användare");
        System.out.println("2. Skapa ny användare");
        System.out.println("3. Avluta");
    }

    public static void displayEntriesMenu(String userName) {
        // TODO Dynamically display user name
        System.out.println("Aktiv användare: " + userName);
        System.out.println("1. Visa inlägg");
        System.out.println("2. Skriv ett inlägg");
        System.out.println("3. Avsluta");
    }
}
