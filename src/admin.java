import databases.book;
import databases.borrower;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class admin {

    //OK
    public static void bookCheck() {
        for (Object obj : Main.currentBooks
        ) {
            System.out.println(obj.toString());

        }
    }

    //OK
    public static void bookRemove() {
        bookCheck();
        String bookToRemove = guest.getInput("please enter the book you wish to remove");
        for (Object obj : Main.currentBooks) {
            if (obj.getClass() == book.class) {
                if (((book) obj).getTitle().equals(bookToRemove)) {
                    Main.currentBooks.remove(obj);
                }
            }
        }
    }

    //OK
    public static void adminMenu() {
        boolean leave = false;
        while (!leave) {
            switch (guest.getInput(" would you like to register a book,remove a book, check what databases.books are available, search for a specific book,remove a user or leave?")) {
                case ("register"):
                    bookRegister();
                    break;
                case ("check"):
                    bookCheck();
                    break;
                case ("leave"):
                    System.out.println("goodbye, come again soon");
                    leave = true;
                    break;
                case ("remove book"):
                    bookRemove();
                    break;
                case ("search"):
                    guest.search();
                    break;
                case ("remove user"):
                    removeGuest();
                    break;

                default:
                    System.out.println("that is not a valid choice");
                    break;
            }
        }
    }

    public static void removeGuest() {

        if (guest.currentUsers.size() == 0) {
            System.out.println("there are no users to remove");
        } else {
            boolean userBanned = false;
            while (userBanned == false) {
                String bannedUser = guest.getInput("please enter the user you wish to remove");
                for (Object obj : guest.currentUsers) {
                    if (obj.getClass() == borrower.class) {
                        if (obj.toString().equals(bannedUser)) {
                            guest.currentUsers.remove(obj);
                            System.out.println("user successfully removed");
                            userBanned = true;
                            break;
                        }
                    }
                }
                if (userBanned == false) {
                    System.out.println("user does not exist");
                }
            }
        }
    }

    public static void adminLogin() {
        final String adminPassword = "harold trotter";
        while (true) {
            String passwordInput = guest.getInput("please enter the admin password");
            if (passwordInput.equals(adminPassword)) {
                break;
            }
            System.out.println("that is not the correct password, please try again");
        }
    }


    public static void bookRegister() {
        Scanner input = new Scanner(System.in);
        System.out.println("please enter the book title, ISBN,author and genre in that order ");
        Main.currentBooks.add(new book(input.next(), input.next(), input.next(), input.next()));
        System.out.println("book added");
    }
}




