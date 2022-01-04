import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static File userAccounts = new File("accounts.txt");
    public static File bookshelf = new File("bookshelf.txt");

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
            FileWriter accountChecker = new FileWriter(userAccounts , true);
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
            Scanner accountChecker = new Scanner(userAccounts);
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


    public static void bookRegister() {
        String delimiter = "~";
        Scanner input = new Scanner(System.in);
        String title = guest.getInput("please enter the title of the book");
        System.out.println("please enter the book ISBN");
        String ISBN = input.next();
        String author = guest.getInput("please enter the author of the book");
        String genre = guest.getInput("please enter the genre of the book");
        System.out.println("adding book to database");
        try {

            FileWriter librarian = new FileWriter(bookshelf, true);
            librarian.write(title + delimiter + ISBN + delimiter + author + delimiter + genre + "\n");
            librarian.close();

            System.out.println("book successfully added");
        } catch (IOException e) {
            System.out.println("error, unable to add book");
            e.printStackTrace();
        }
    }

    public static void bookCheck() {

        try {
            Scanner fileReader = new Scanner(bookshelf);

            if (bookshelf.getName().length() == 0) {
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

    public static void bookRemove() {
        ArrayList<String> temp = new ArrayList<>();
        try {
            bookCheck();
            String removeChoice = guest.getInput("which book would you like to remove?");
            Scanner librarian = new Scanner(bookshelf);
            while (librarian.hasNextLine()) {
                String line = librarian.nextLine();
                if (!line.contains(removeChoice)) {
                    temp.add(line);
                }
            }
            FileWriter remover = new FileWriter(bookshelf, false);
            for (int i = 0; i < temp.size(); i++) {
                remover.write(temp.get(i) + "\n");
                remover.close();
            }
            remover.close();
        } catch (IOException e) {
            System.out.println("error , file could not be found" + e);
        }
    }

    public static void adminMenu() {
        boolean leave = false;
        while (!leave) {
            switch (guest.getInput(" would you like to register a book,remove a book, check what books are available, search for a specific book or leave?")) {
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
                case ("remove"):
                    bookRemove();
                    break;
                case ("search"):
                    guest.search();
                    break;

                default:
                    System.out.println("that is not a valid choice");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        createShelf();
        if (guest.guestCheck()) {
            guest.guestLogin();
            guest.guestMenu();
        } else {
            adminLogin();
            adminMenu();
        }
    }
}