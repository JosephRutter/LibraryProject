import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//need to finish setting up book register , then do book check, and like make it a file and stuff lol 

public class Main {
    private static File bookshelf = new File("bookshelf.txt");

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

        Scanner input = new Scanner(System.in);
        String title = getInput("please enter the title of the book");
        System.out.println("please enter the book ISBN");
        String ISBN = input.next();
        String author = getInput("please enter the author of the book");
        System.out.println("adding book to database");
        String genre = getInput("please enter the genre of the book");
        try {
            FileWriter librarian = new FileWriter(bookshelf.getName(), true);
            librarian.write(title + "~" + ISBN + "~" + author + "~" + genre + "\n");
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
        while (fileReader.hasNextLine()) {
            String bookInfo = fileReader.nextLine();
            System.out.println(bookInfo);
        }
        fileReader.close();
        } catch (FileNotFoundException e){
            System.out.println("an error occurred");
            e.printStackTrace();
        }
    }

    public static void mainMenu() {
        System.out.println("welcome to the library");
        boolean leave = false;
        while (!leave) {
            System.out.println(" would you like to register a book, or check what books are available?");
            Scanner input = new Scanner(System.in);
            switch (input.next()) {
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
            }
        }
    }

    public static void main(String[] args) {
        createShelf();
        mainMenu();
    }
}
