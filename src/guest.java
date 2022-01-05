import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
                    guestSignIn();
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



    public static void guestMenu() {
        boolean leave = false;
        while (!leave) {
            switch (getInput("would you like to browse available books , search for a specific book or leave?")) {

                case ("browse"):
                    bookCheck();
                    break;

                case ("search"):
                    search();
                    break;

                case ("leave"):
                    leave = true;
                    break;

                default:
                    System.out.println("that is not a valid choice");
                    break;
            }
        }

    }


    public static void bookCheck() {

        try {
            Scanner fileReader = new Scanner(Main.bookshelf);

            if (Main.bookshelf.getName().length() == 0) {
                System.out.println("there are no books currently registered");
            } else {
                while (fileReader.hasNextLine()) {
                    String bookInfo = fileReader.nextLine() + "\n";
                    System.out.println(bookInfo);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("an error occurred");
        }
    }

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

    public static void createAccount() {

        try {
            FileWriter accountChecker = new FileWriter(Main.userAccounts, true);
            accountChecker.write(getEmail() + "~" + getPassword());
            accountChecker.close();
            System.out.println("account successfully created, you can now login to the library system");
        } catch (IOException e) {
            System.out.println("account could not be created");
            e.printStackTrace();
        }


    }

    public static void guestSignIn() {
        confirmPassword(confirmEmail());}

    public static void confirmPassword(String currentLine) {

        boolean correctPassword = false;
        while (!correctPassword) {
            String passwordInput = getInput("please enter your password");
            if (currentLine.contains(passwordInput)) {
                break;
            } else {
                System.out.println("that was not the correct password");
            }
        }
    }

    public static String confirmEmail() {
        try {
            Scanner accountChecker = new Scanner(Main.userAccounts);
            boolean foundEmail = false;
            while (!foundEmail) {
                String emailInput = getInput("please enter your email that you have registered with");
                while (accountChecker.hasNextLine()) {
                    String currentLine = accountChecker.nextLine();
                    if (currentLine.contains(emailInput)) {
                        return currentLine;
                    }

                }
                System.out.println("that email does not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
