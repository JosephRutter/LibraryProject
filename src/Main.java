import java.io.*;
import java.util.Scanner;

public class Main {
    public static File userAccounts = new File("accounts.txt");
    public static File bookshelf = new File("bookshelf.txt");
    public static void createShelf() {

        try {
            if (bookshelf.createNewFile()) {
                System.out.println("bookshelf successfully created");
            } else {
                System.out.println("file already exists");
            }
        } catch (IOException e) {
            System.out.println("an error occurred");
            e.printStackTrace();
        }
    }
    public static void txtToBooks(){
        try {
            Scanner librarian = new Scanner(bookshelf);
            while(librarian.hasNextLine()){

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void txtToBorrowers() {
        try {
            Scanner librarian = new Scanner(userAccounts);
            while (librarian.hasNextLine()) {
                borrowers
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        createShelf();
        if (guest.guestCheck()) {
            guest.guestLogin();
            guest.guestMenu();
        } else {
            admin.adminLogin();
            admin.adminMenu();
        }
    }
}