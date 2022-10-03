package model;

public class Menu {
    // TODO: make to static to avoid instantiating new object
    /*public void displayStartMenu() {
        System.out.println("Aktiv användare: ingen");
        System.out.println("1. Vällj användare");
        System.out.println("2. Skapa ny användare");
        System.out.println("3. Avluta");
    }
    public void displayStartMenu(User user) {
        if (user == null) {
            System.out.println("Aktiv användare ingen");
        }
        System.out.println("Aktiv användare: " + user);
        System.out.println("1. Vällj användare");
        System.out.println("2. Skapa ny användare");
        System.out.println("3. Avluta");
    }*/
    public static void displayStartMenu() {
        System.out.println("1. Visa inlägg");
        System.out.println("2. Skriv ett inlägg");
        System.out.println("3. Avsluta");
    }
}
