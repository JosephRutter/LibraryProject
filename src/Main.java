import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static int genreToKey(String genre) {
        switch (genre) {

            case ("thriller"):
                return 1;

            case ("horror"):
                return 2;

            case ("romance"):
                return 3;

            case ("comedy"):
                return 4;

            case ("graphic novel"):
                return 5;

            case ("non fiction"):
                return 6;

            default:
                return 7;
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
        String genre = getInput("please enter the genre of the book, your choices are: thriller , horror, romance, comedy , graphic novel,non fiction, other");
        System.out.println("adding book to database");
        try {

            FileWriter librarian = new FileWriter(bookshelf, true);
            librarian.write(title + delimiter + ISBN + delimiter + author + delimiter + genreToKey(genre) + "\n");
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
                fileReader.close();
            } else {
                while (fileReader.hasNextLine()) {
                    String bookInfo = fileReader.nextLine() + "\n";
                    System.out.println(bookInfo);
                }
                fileReader.close();
            }
        }catch(FileNotFoundException e){
            System.out.println("an error occured");
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
                if (line.contains(removeChoice)) {
                } else {
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

    public static void mainMenu() {
        System.out.println("welcome to the library");
        boolean leave = false;
        while (!leave) {
            System.out.println(" would you like to register a book,remove a book, or check what books are available?");
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
                case ("remove"):
                    bookRemove();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        createShelf();
        mainMenu();
    }
}