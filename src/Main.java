import databases.book;
import databases.borrower;
import java.io.*;
import java.util.ArrayList;
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


    public static ArrayList<Object> currentBooks = new ArrayList<>();


    public static void borrowersToTemp() {
        try {
            Scanner librarian = new Scanner(userAccounts);
            librarian.useDelimiter("~");
            while (librarian.hasNextLine()) {

                guest.currentUsers.add((new borrower(librarian.next(), librarian.next())));
                while (librarian.hasNext()) {
                    guest.currentUsers.add(new book(librarian.next(), librarian.next(), librarian.next(), librarian.next()));
                }
                librarian.nextLine();
            }
            librarian.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void booksToTemp() {
        try {
            Scanner librarian = new Scanner(bookshelf);
            librarian.useDelimiter("~");
            if (bookshelf.length() != 0) {
                while (librarian.hasNextLine()) {
                    currentBooks.add(new book(librarian.next(), librarian.next(), librarian.next(), librarian.next()));
                }
            }}
        catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }

    public static void booksToTxt() {
        try {
           FileWriter librarian = new FileWriter(bookshelf);
           for (int i = 0; i < currentBooks.size();i++){
            librarian.write(currentBooks.get(i).toString());
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



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

    }
}