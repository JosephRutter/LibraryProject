import databases.book;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class admin {


    public static void bookCheck() {

        try {
            Scanner fileReader = new Scanner(Main.bookshelf);

            if (Main.bookshelf.getName().length() == 0) {
                System.out.println("there are no databases.books currently registered");
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
            Scanner librarian = new Scanner(Main.bookshelf);
            while (librarian.hasNextLine()) {
                String line = librarian.nextLine();
                if (!line.contains(removeChoice)) {
                    temp.add(line);
                }
            }
            FileWriter remover = new FileWriter(Main.bookshelf, false);
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

    public static void removeGuest(){
        ArrayList<String> temp = new ArrayList<>();
        try {
            Scanner input = new Scanner(System.in);
            Scanner librarian = new Scanner(Main.userAccounts);
            FileWriter banHammer = new FileWriter(Main.userAccounts, false);
            System.out.println("which user would you like to remove?");
            while (librarian.hasNextLine()){
                System.out.println(librarian.nextLine());
            }
            String bannedUser = input.next();

            while (librarian.hasNextLine()){
              String tempLine = librarian.nextLine();
                if(!(tempLine.contains(bannedUser))){
                    temp.add(tempLine);
                }
            }
            for (int i = 0; i < temp.size(); i++){
                banHammer.write(temp.get(i) + "\n");
                banHammer.close();
            }
            System.out.println("removed!");
        } catch (IOException e) {
            System.out.println("an error occurred"+e);
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




