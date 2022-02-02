
import databases.book;
import databases.borrower;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class guest {


    public static String getInput(String prompt) {
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }


    public static void guestLogin() {
        String userChoice;
        boolean loggedIn = false;
        while (!loggedIn) {
            userChoice = getInput("would you like to sign in or register an account?");

            switch (userChoice) {

                case ("sign in"):
                    confirmUser();
                    loggedIn = true;
                    break;

                case ("register"):
                    createAccount();
                    break;

                default:
                    System.out.println("that was not a valid choice");
                    break;
            }
        }
    }

    public static boolean guestCheck() {
        System.out.println("welcome to the library");
        while (true) {
            String userRole = getInput("are you a guest or admin?");
            if (userRole.equals("guest")) {
                return true;
            } else if (userRole.equals("admin")) {
                return false;
            } else {
                System.out.println("that is not a valid entry , please try again");
            }
        }
    }

    public static void search() {
        try {
            Scanner fileReader = new Scanner(Main.bookshelf);
            String userSearch = getInput("please enter the title, the author , or the ISBN of the book you wish to find");
            while (fileReader.hasNextLine()) {
                String currentBook = fileReader.nextLine();
                if (currentBook.contains(userSearch)) {
                    System.out.println(currentBook);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("error ,library database not found" + e);
        }
    }

    public static ArrayList<Object> currentUsers = new ArrayList<>();

    public static String loggedOnUser;

    public static void guestMenu() {
        boolean leave = false;
        while (!leave) {
            switch (getInput("would you like to browse available books , search for a specific book, borrow a book or leave?")) {

                case ("browse"):
                    admin.bookCheck();
                    break;

                case ("search"):
                    search();
                    break;

                case ("leave"):
                    leave = true;
                    break;

                case ("borrow"):
                    borrow();
                    break;
                default:
                    System.out.println("that is not a valid choice");
                    break;
            }
        }

    }
    public static int getUserPosition(String loggedOnUser){
        for (Object obj: currentUsers
             ) {
            if(obj.getClass().equals(borrower.class)){
                if(((borrower) obj).getUsername().contains(loggedOnUser)){
                    return currentUsers.indexOf(obj) +1;


                }
            }

        } return -1;
    }


    public static void borrow() {
        admin.bookCheck();
        String bookToBorrow = getInput("which book would you like to borrow?");
        for (Object obj : Main.currentBooks) {
            if(obj.getClass() == book.class){
                if(((book) obj).getTitle().equals(bookToBorrow)) {
                    currentUsers.add(getUserPosition(loggedOnUser),obj);
                    Main.currentBooks.remove(obj);
                    break;
                }
            }
        }

    }


    //OK
    public static String getPassword() {

        Pattern validPassword = Pattern.compile("(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}");

        while (true) {
            String passwordInput = getInput("please enter a secure password, it must have 1 uppercase letter, 1 special character, and be at least 8 letters long");
            Matcher matcher = validPassword.matcher(passwordInput);
            if (matcher.matches()) {
                return passwordInput;
            } else {
                System.out.println("that is not a secure enough password, please try again");
            }
        }
    }

    //OK
    public static String getEmail() {
        while (true) {
            String emailInput = getInput("please enter a valid email");
            if (emailInput.contains("@") && emailInput.contains(".com")) {
                return emailInput;
            } else {
                System.out.println("that is not a valid email, please try again");
            }
        }
    }

    //OK
    public static void createAccount() {


        currentUsers.add(new borrower(getEmail(), getPassword()));
        System.out.println("account successfully created, you can now login to the library system");


    }

    //OK
    public static void confirmUser() {
        boolean loggedIn = false;
        while (!loggedIn) {
            String emailInput = getInput("please enter a valid email");
            String passwordInput = getInput("please enter the password for this account");
            for (Object obj : currentUsers) {
                if (obj.getClass() == borrower.class) {
                    if (obj.toString().contains(emailInput) && obj.toString().contains(passwordInput)) {
                        loggedOnUser = emailInput;
                        loggedIn = true;
                    }
                }
                if (loggedIn) {
                    break;
                } else {
                    System.out.println("your input does not match our database, please try again");
                }

            }
        }
    }
}