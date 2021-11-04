package Library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookImpl implements BookDao{

    Connection connection;

    @Override
        public List<Book> getCategories() throws SQLException{
            List<Book> books = new ArrayList<>();
            String sql = "SELECT * FROM books WHERE category = ?";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int isbn = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String author = resultSet.getString(3);
                float price = resultSet.getFloat(4);
                String description = resultSet.getString(5);
                String category = resultSet.getString(6);
                Book book = new Book(isbn, title, author, price, description, category);
                books.add(book);
            }
            return books;
    }

    @Override
    public List<Book> getBooks() throws SQLException{
        return null;
    }

    @Override
    public List<Book> getBookByTitle(String bookTitle) throws SQLException{
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title = ?";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            int isbn = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String author = resultSet.getString(3);
            float price = resultSet.getFloat(4);
            String description = resultSet.getString(5);
            String category = resultSet.getString(6);
            Book book = new Book(isbn, title, author, price, description, category);
            books.add(book);
        }
        return books;
    }

    @Override
    public List<Book> getBookByAuthor(String bookAuthor) throws SQLException{
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE author = ?";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            int isbn = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String author = resultSet.getString(3);
            float price = resultSet.getFloat(4);
            String description = resultSet.getString(5);
            String category = resultSet.getString(6);
            Book book = new Book(isbn, title, author, price, description, category);
            books.add(book);
        }
        return books;
    }

    @Override
    public List<Book> getBookByCategory(String bookCategory) throws SQLException{
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE category = ?";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            int isbn = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String author = resultSet.getString(3);
            float price = resultSet.getFloat(4);
            String description = resultSet.getString(5);
            String category = resultSet.getString(6);
            Book book = new Book(isbn, title, author, price, description, category);
            books.add(book);
        }
        return books;
    }

    @Override
    public Book getBookByIsbn(int bookIsbn) throws SQLException{
    Book book = new Book();
    String sql = "SELECT * FROM books WHERE isbn = ?" + bookIsbn;
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if(resultSet != null){
        int isbn = resultSet.getInt(1);
        String title = resultSet.getString(2);
        String author = resultSet.getString(3);
        float price = resultSet.getFloat(4);
        String description = resultSet.getString(5);
        String category = resultSet.getString(6);
        book = new Book(isbn, title, author, price, description, category);
    }else{
        System.out.println("No book found for that ISBN.");
    }
        return book;
    }
}
