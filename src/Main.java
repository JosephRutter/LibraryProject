import databases.book;
import databases.borrower;

import javax.annotation.processing.Filer;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static File userAccounts = new File("accounts.txt");
    public static File bookshelf = new File("bookshelf.txt");
    public static ArrayList<Object> currentBooks = new ArrayList<>();

    //OK
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

    //OK
    public static void borrowersToTemp() {
        try {
            Scanner librarian = new Scanner(userAccounts);
            librarian.useDelimiter("~");
            while (librarian.hasNextLine()) {

                guest.currentUsers.add((new borrower(librarian.next(), librarian.next())));
                while (librarian.hasNext()) {
                    guest.currentUsers.add(new book(librarian.next(), librarian.next(), librarian.next(), librarian.next()));
                }
            }
            librarian.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //OK
    public static void booksToTemp() {
        try {
            Scanner librarian = new Scanner(bookshelf);
            librarian.useDelimiter("~");
            if (bookshelf.length() != 0) {
                while (librarian.hasNextLine()) {
                    currentBooks.add(new book(librarian.next(), librarian.next(), librarian.next(), librarian.next()));

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //OK
    public static void booksToTxt() {
        try {
            FileWriter cleaner = new FileWriter(bookshelf, false);
            FileWriter librarian = new FileWriter(bookshelf, true);
            cleaner.write("");
            cleaner.close();
            for (Object obj : currentBooks) {
                librarian.write((obj.toString()) + "\n");
            }
            librarian.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void borrowersToTxt() {
        try {
            FileWriter cleaner = new FileWriter(userAccounts, false);
            FileWriter librarian = new FileWriter(userAccounts, true);
            cleaner.write("");
            cleaner.close();
            for (Object obj : guest.currentUsers
            ) {
                if (obj.getClass() == borrower.class) {
                    librarian.write("\n");
                }
                librarian.write(obj.toString());

            }
            librarian.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        {

        }
    }

    //OK
    public static void main(String[] args) {
        createShelf();
        borrowersToTemp();
        booksToTemp();
        if (guest.guestCheck()) {
            guest.guestLogin();
            guest.guestMenu();
        } else {
            admin.adminLogin();
            admin.adminMenu();
        }
        booksToTxt();
        borrowersToTxt();
    }
}