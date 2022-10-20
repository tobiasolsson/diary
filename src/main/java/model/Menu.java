package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    /**
     * First menu in program. User either chooses a user or creates new one. Or quits.
     *
     * @param currentUser string of the current user, default null
     * @param users the list of users
     * @return string of the username
     */
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

                    System.out.println();
                    System.out.println("Skriv in ditt användare namn du vill logga in på: ");

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
                System.out.println("Skriv (1) för att skapa en ny användare");
                System.out.println("Skriv (2) för att gå tillbaka");
                int userCreationMenuChoice = Integer.parseInt(scanner.nextLine());
                if (userCreationMenuChoice == 1) {
                    String newUserName = scanner.nextLine();
                    User newUser = new User(newUserName, new ArrayList<>());
                    // Update JSON with new user, so we can write entries to JSON
                    Utility.writeNewUserToJSON(newUser, users);
                }
            } else if (userMenuChoice == 3) {
                System.out.println("Välkommen åter!");
                System.exit(0);
            } else {
                System.out.println("Använd siffrorna 1-3 för att välja i menyn");
            }

        } catch (Exception e) {
            System.out.println("Fel input, använd siffrorna 1-3");
        }
        return null;
    }

    /**
     * Logged in menu, where the user can read och write to their list of entries
     *
     * @param currentUser current username
     * @param user current user Object
     * @param users list of copy from JSON-file
     * @return new list of Users
     */
    public static List<User> loggedInMenu(String currentUser, User user, List<User> users) {
        Scanner scanner = new Scanner(System.in);
        // if user == null display start menu
        // if user == someone, display entriesMenu
        Menu.displayEntriesMenu(currentUser);
        try {
            int entriesMenuChoice = Integer.parseInt(scanner.nextLine());

            if (entriesMenuChoice == 1) {
                // Before we list the entries, make sure list is updated from json-file
                users = Utility.updatePostsList();

                // If there are entries, list them
                // Else say there are no entries
                if (user.getEntries().size() > 0) {
                    for (Entry entry : user.getEntries()) {
                        System.out.println();
                        System.out.printf("%s - %s \n", entry.getDate(), entry.getTitle());
                        System.out.printf("%s\n", entry.getBody());
                    }
                } else {
                    System.out.println("Inga inlägg ännu!");
                }


            } else if (entriesMenuChoice == 2) {
                System.out.println("Ange din titel:");
                String postTitle = scanner.nextLine();

                System.out.println("Skriv ditt inlägg: ");
                String postBody = scanner.nextLine();

                String currentDate = LocalDate.now().toString();

                Entry newEntry = new Entry(postTitle, postBody, currentDate);

                // Update posts List<> to current values in JSON file
                // returns new list of posts, so we can update posts list
                // user.setEntries(Utility.addNewEntry(newEntry, user, users));
                users = Utility.addNewEntry(newEntry, user, users);
            } else if (entriesMenuChoice == 3) {
                System.out.println("Välkommen åter!");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Använd endast siffror i meny valen!");
        }
        return users;
    }

    public static void displayStartMenu(String userName) {
        System.out.println();
        if (userName == null) {
            System.out.println("Aktiv användare: Ingen");
        } else {
            System.out.println("Aktiv användare: " + userName);
        }
        System.out.println("1. Välj användare");
        System.out.println("2. Skapa ny användare");
        System.out.println("3. Avluta");
        System.out.println();
    }

    public static void displayEntriesMenu(String userName) {
        System.out.println();
        System.out.println("Aktiv användare: " + userName);
        System.out.println("1. Visa inlägg");
        System.out.println("2. Skriv ett inlägg");
        System.out.println("3. Avsluta");
        System.out.println();
    }
}
