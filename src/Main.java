import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static File bookshelf = new File("bookshelf.txt");

    public static String getPassword(){
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");

        while(true){
            String passwordInput = getInput("please enter a secure password, it must have 1 uppercase letter, 1 special character, and be at least 8 letters long");
            Matcher matcher = pattern.matcher(passwordInput);
            if(passwordInput.contains(".*\\d*.") && passwordInput.equals(passwordInput.toUpperCase()) && passwordInput.length() >= 8 && matcher.find())
            {return passwordInput;
            }else{
                System.out.println("that is not a secure enough password, please try again");
            }
        }
    }

    public static String getEmail(){
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
       String email = getEmail();
       String Password = getPassword();


    }

    public static void guestSignIn() {
    }

    public static void adminLogin() {
        final String adminPassword = "harold trotter";
        while (true) {
            String passwordInput = getInput("please enter the admin password");
            if (passwordInput.equals(adminPassword)) {
                break;
            }
            System.out.println("that is not the correct password, please try again");
        }
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

    public static void guestMenu() {
        boolean leave = false;
        while (!leave) {
            switch (getInput("would you like to browse available books , search for a specific book or leave?")) {

                case ("browse"):
                    bookRegister();
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

    public static void search() {
        try {
            Scanner fileReader = new Scanner(bookshelf);
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

    public static String getInput(String prompt) {
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void bookRegister() {
        String delimiter = "~";
        Scanner input = new Scanner(System.in);
        String title = getInput("please enter the title of the book");
        System.out.println("please enter the book ISBN");
        String ISBN = input.next();
        String author = getInput("please enter the author of the book");
        String genre = getInput("please enter the genre of the book");
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
            String removeChoice = getInput("which book would you like to remove?");
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
            switch (getInput(" would you like to register a book,remove a book, check what books are available, search for a specific book or leave?")) {
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

                default:
                    System.out.println("that is not a valid choice");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        createShelf();
        if (guestCheck()) {
            guestLogin();
            guestMenu();
        } else {
            adminLogin();
            adminMenu();
        }
    }
}