package databases;

import java.util.ArrayList;

public class borrowers {
    private ArrayList books = new ArrayList();
    private String username;
    private String password;

    public borrowers(String username, String password) {
        this.username = username;
        this.password = password;

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


    @Override
    public String   toString() {
        return books.toString() + '$' + username + '~' + password ;
    }
}
