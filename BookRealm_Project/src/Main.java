import BookSearchProgram.BSP_MainPage;
import book.BookController;
import review.ReviewDAO;
import review.ReviewVO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {

       BSP_MainPage mainPage = new BSP_MainPage();
       mainPage.mainMenu();
    }
}