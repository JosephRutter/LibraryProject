package databases;

import java.util.ArrayList;

public class borrowers {
    private String firstBook;
    private String secondBook;
    private String thirdBook;
    private String username;
    private String password;

    public borrowers(String username, String password, String firstBook , String secondBook, String thirdBook) {
        this.username = username;
        this.password = password;
        this.firstBook = firstBook;
        this.secondBook = secondBook;
        this.thirdBook = thirdBook;
    }



    public  String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFirstBook() {
        return firstBook;
    }

    public void setFirstBook(String firstBook) {
        this.firstBook = firstBook;
    }

    public String getSecondBook() {
        return secondBook;
    }

    public void setSecondBook(String secondBook) {
        this.secondBook = secondBook;
    }

    public String getThirdBook() {
        return thirdBook;
    }

    public void setThirdBook(String thirdBook) {
        this.thirdBook = thirdBook;
    }
}
