import java.util.ArrayList;
import java.util.Scanner;

//need to finish setting up book register , then do book check, and like make it a file and stuff lol 

public class Main {
public static ArrayList<String> bookshelf = new ArrayList<String>();
    public static void bookRegister() {
        Scanner input = new Scanner(System.in);
        System.out.println("please enter the book title");
        String bookTitle = input.next();
        System.out.println("please enter the book ISBN");
        String ISBN = input.next();
        System.out.println("please enter the book author");
        String author = input.next();
    }

    public static void bookCheck() {

    }

    public static void mainMenu() {
        System.out.println("welcome to the library");
    boolean leave = false;
        while (leave == false) {
            System.out.println(" would you like to register a book, or check what books are available?");
            Scanner input = new Scanner(System.in);
            switch (input.next()) {
                case ("register"):
                    bookRegister();
                case ("check"):
                    bookCheck();
                case ("leave"):
                    System.out.println("goodbye, come again soon");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
