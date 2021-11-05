package Library;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookImpl implements BookDao{

    Connection connection;

    public BookImpl(){

        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public List<Book> getCategories() throws SQLException{
        List<Book> books = new ArrayList<>();
        String sql = "SELECT DISTINCT category FROM books";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            String category = resultSet.getString(1);
            Book bookCategory = new Book(category);
            books.add(bookCategory);
        }
        return books;
    }

    @Override
    public List<Book> getBookByTitle(String bookTitle) throws SQLException{
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title = " + "'" + bookTitle + "'";
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
        String sql = "SELECT * FROM books WHERE author = " + "'" + bookAuthor + "'";
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
        String sql = "SELECT * FROM books WHERE category = " + "'" + bookCategory + "'";
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
        String sql = "SELECT * FROM books WHERE isbn = " + bookIsbn;
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


    @Override
    public Book addToCart(int bookIsbn) throws SQLException{
        Book book = new Book();
        Scanner sc = new Scanner(System.in);
        String sql = "SELECT * FROM books WHERE isbn = " + bookIsbn;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        int isbn = resultSet.getInt(1);
        String title = resultSet.getString(2);
        String author = resultSet.getString(3);
        float price = resultSet.getFloat(4);
        String description = resultSet.getString(5);
        String category = resultSet.getString(6);

        book = new Book(isbn, title, author, price, description, category);
        try{
            CLS.cls();
        }catch(IOException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        final Object[][] table = new String[6][];
        table[0] = new String[]{"________________________________\n ISBN: ", String.valueOf(book.getIsbn()) + "\n--------------------------------"};
        table[1] = new String[]{"Title: ", book.getTitle()};
        table[2] = new String[]{"Author: ", book.getAuthor()};
        table[3] = new String[]{"Price: $", String.valueOf(book.getPrice())};
        table[4] = new String[]{"Description: ", book.getDescription()};
        table[5] = new String[]{"Category: ", book.getCategory() + "\n________________________________"};

        for(final Object[] row : table){
            System.out.format("%-15s%-15s%n", row);
        }

        System.out.println("""
                ____________________________
                | Please select an action: |
                |--------------------------|
                | 1. Add to Cart           |
                | 2. Cancel                |
                |__________________________|
                """);

        boolean proceed = false;

        while(proceed == false){

            System.out.print("-> ");

            int userChoice = sc.nextInt();
            if(userChoice == 1){
                book = new Book(isbn, title, author, price, description, category);
                String sql2 = "INSERT INTO cart (title, author, price) VALUES (?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, author);
                preparedStatement.setFloat(3, price);
                int count = preparedStatement.executeUpdate();
                if(count > 0){
                    System.out.println("\nBook has been added to cart.\n");
                    Menus.bookstoreMenu();
                }else{
                    System.out.println("\nSorry, something went wrong.\n");
                }
                return book;
            }else if(userChoice == 2){
                try{
                    CLS.cls();
                }catch(IOException e){
                    e.printStackTrace();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("\nReturning to menu...");
                proceed = true;
                Menus.bookstoreMenu();
            }else{
                System.out.println("\nInvalid selection.\n");
            }
        }
        return book;
    }

    @Override
    public List<Book> getCart() throws SQLException{
        Scanner sc = new Scanner(System.in);
        List<Book> books = new ArrayList<>();
        int itemNum = 0;
        float sum = 0;
        float tax = 0.06f;
        float total;

        String sql = "SELECT * FROM cart";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            String title = resultSet.getString(1);
            String author = resultSet.getString(2);
            float price = resultSet.getFloat(3);
            Book book = new Book(title, author, price);
            books.add(book);
        }
        int count = 0;
        for(Book book : books){
            ++count;
            sum += book.getPrice();
            itemNum++;
            System.out.printf(
                    "%-17s%-2s%-60s%n",
                    "Item: " + itemNum + ": \t$" + String.format("%.2f", book.getPrice()),
                    "|",
                    book.getTitle() + " by " + book.getAuthor()
            );
        }
        if(count != 0){
            System.out.println("\nSubtotal: \t$" + String.format("%.2f", sum));
            System.out.println("Tax: \t\t$" + String.format("%.2f", sum * tax));
            System.out.println("---------------------------");
            total = (sum * tax) + sum;
            System.out.println("Total: \t\t$" + String.format("%.2f", total));
            System.out.println("""
                    ____________________________
                    | Please select an action: |
                    |--------------------------|
                    | 1. Checkout              |
                    | 2. Cancel                |
                    |__________________________|
                    """);

            boolean proceed = false;

            while(proceed == false){

                System.out.print("-> ");

                int userChoice = sc.nextInt();
                if(userChoice == 1){
                    String sql2 = "TRUNCATE cart;";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql2);
                    preparedStatement.executeUpdate();
                    try{
                        CLS.cls();
                    }catch(IOException e){
                        e.printStackTrace();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("\nYour purchase was successful.\n");
                    System.out.println("You were charged: $" + String.format("%.2f", total) + "\n\nThank you for shopping with us!");
                    proceed = true;
                    Menus.bookstoreMenu();
                }else if(userChoice == 2){
                    try{
                        CLS.cls();
                    }catch(IOException e){
                        e.printStackTrace();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("\nReturning to menu...");
                    proceed = true;
                    Menus.bookstoreMenu();
                }else{
                    System.out.println("\nInvalid selection.\n");
                }
            }
            return books;
        }else{
            System.out.println("\nYour shopping cart is empty!");
            Menus.bookstoreMenu();
        }
        return books;
    }
}