package Library;

import java.sql.SQLException;
import java.util.List;

public interface BookDao{

    List<Book> getCategories() throws SQLException;

    List<Book> getBookByTitle(String title) throws SQLException;

    List<Book> getBookByAuthor(String author) throws SQLException;

    List<Book> getBookByCategory(String category) throws SQLException;

    Book getBookByIsbn(int isbn) throws SQLException;

    Book addToCart(int isbn) throws SQLException;

    List<Book> getCart() throws SQLException;
}
