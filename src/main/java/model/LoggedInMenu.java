package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class LoggedInMenu {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Logged in menu, where the user can read och write to their list of entries
     *
     * @param currentUserName current username from parent menu
     */
    public static void loggedInMenu(String currentUserName) {
        User currentUser = Utility.getCurrentUser(currentUserName);

        displayEntriesMenu(currentUserName);

        try {
            int entriesMenuChoice = Integer.parseInt(scanner.nextLine());

            if (entriesMenuChoice == 1) {
                assert currentUser != null;
                listUserEntries(currentUser);
            } else if (entriesMenuChoice == 2) {
                createNewEntry(currentUser);
            } else if (entriesMenuChoice == 3) {
                Utility.exitProgram();
            }
        } catch (Exception e) {
            System.out.println("Använd endast siffror i meny valen!");
        }
    }

    /**
     *
     * @param currentUser current user to be passed along to addNewEntry for the correct user gets the new entry
     * @throws IOException handle Jackson error
     */
    private static void createNewEntry(User currentUser) throws IOException {
        System.out.println("Ange din titel:");
        String postTitle = scanner.nextLine();

        System.out.println("Skriv ditt inlägg: ");
        String postBody = scanner.nextLine();

        String currentDate = LocalDate.now().toString();

        Entry newEntry = new Entry(postTitle, postBody, currentDate);

        Utility.addNewEntry(newEntry, currentUser);
    }

    /**
     *
     * @param currentUser current User to fetch entries from
     */
    private static void listUserEntries(User currentUser) {
        if (currentUser.entries().size() > 0) {
            for (Entry entry : currentUser.entries()) {
                System.out.println();
                System.out.printf("%s - %s \n", entry.date(), entry.title());
                System.out.printf("%s\n", entry.body());
            }
        } else {
            System.out.println("Inga inlägg ännu!");
        }
    }

    /**
     *
     * @param userName the username to display
     */
    private static void displayEntriesMenu(String userName) {
        System.out.println();
        System.out.println("Aktiv användare: " + userName);
        System.out.println("1. Visa inlägg");
        System.out.println("2. Skriv ett inlägg");
        System.out.println("3. Avsluta");
        System.out.println();
    }
}
