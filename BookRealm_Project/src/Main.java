import BookSearchProgram.BSP_MainPage;
import book.BookController;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

       BSP_MainPage mainPage = new BSP_MainPage();
       mainPage.mainMenu();
    }
}