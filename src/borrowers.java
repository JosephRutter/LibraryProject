import java.util.ArrayList;

public class borrowers {

    private String username;
    private String password;
    private ArrayList<String> books;

    public borrowers(String username, String password, ArrayList<String> books) {
        this.username = username;
        this.password = password;
        this.books = books;
    }

    public String bookToString() {
        return username + "~" + password + "~" + books;
    }

    public String getUsername() { 
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

    public ArrayList<String> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<String> books) {
        this.books = books;
    }
}
