package Library;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menus{

    public static void bookstoreMenu() throws SQLException{
        Scanner sc = new Scanner(System.in);
        BookDao dao = DaoFactory.getBookDao();

        boolean continues = true;

        String bookstore = """
                ____________________________
                |     Bookstore Menu       |
                |__________________________|
                | Please select an action: |
                |--------------------------|
                | 1. Show Categories       |
                | 2. Select Category       |
                | 3. Search Book by Title  |
                | 4. Search Book by Author |
                | 5. Search Book by ISBN   |
                | 6. Add Book to Cart      |
                | 7. View Cart             |
                | 8. Logout                |
                |__________________________|
                """;

        System.out.println(bookstore);

        while(continues){

            System.out.print("-> ");

            int choice = sc.nextInt();

            switch(choice){

                case 1 -> {

                    System.out.println("\nShowing Categories...\n");

                    List<Book> categories = dao.getCategories();

                    for(Book bookCategory : categories){
                        final Object[][] table = new String[1][];

                        table[0] = new String[] { "Categories: ", bookCategory.getCategory() };

                        for(final Object[] row : table){
                            System.out.format("%-15s%-15s%n", row);
                        }
                    }
                    System.out.println("\n" + bookstore);
                }
                case 2 -> {

                    System.out.print("\nPlease enter name of category: ");
                    String bookCategory = sc.next();

                    System.out.println("\nShowing books...\n");
                    List<Book> books = dao.getBookByCategory(bookCategory);
                    for(Book book : books){
                        System.out.printf(
                                "%-2s%-8s%-2s%-20s%-2s%-30s%-2s%-20s%-2s%-20s%-2s%-20s%n",
                                "|",
                                "ISBN: " + book.getIsbn(),
                                "|",
                                "Title: " + book.getTitle(),
                                "|",
                                "Author: " + book.getAuthor(),
                                "|",
                                "Price: " + book.getPrice(),
                                "|",
                                "Description: " + book.getDescription(),
                                "|",
                                "Category: " + book.getCategory()
                        );
                    }
                    System.out.println(bookstore);
                }
                case 3 -> {

                    System.out.print("\nPlease enter name of title: ");
                    String bookTitle = sc.next();

                    System.out.println("\nShowing books...\n");
                    List<Book> books = dao.getBookByTitle(bookTitle);
                    for(Book book : books){
                        System.out.printf(
                                "%-2s%-8s%-2s%-20s%-2s%-30s%-2s%-20s%-2s%-20s%-2s%-20s%n",
                                "|",
                                "ISBN: " + book.getIsbn(),
                                "|",
                                "Title: " + book.getTitle(),
                                "|",
                                "Author: " + book.getAuthor(),
                                "|",
                                "Price: " + book.getPrice(),
                                "|",
                                "Description: " + book.getDescription(),
                                "|",
                                "Category: " + book.getCategory()
                        );
                    }
                    System.out.println(bookstore);
                }
                case 4 -> {
                    System.out.print("\nPlease enter name of author: ");
                    String bookAuthor = sc.next();

                    System.out.println("\nShowing books...\n");
                    List<Book> books = dao.getBookByAuthor(bookAuthor);
                    for(Book book : books){
                        System.out.printf(
                                "%-2s%-8s%-2s%-20s%-2s%-30s%-2s%-20s%-2s%-20s%-2s%-20s%n",
                                "|",
                                "ISBN: " + book.getIsbn(),
                                "|",
                                "Title: " + book.getTitle(),
                                "|",
                                "Author: " + book.getAuthor(),
                                "|",
                                "Price: " + book.getPrice(),
                                "|",
                                "Description: " + book.getDescription(),
                                "|",
                                "Category: " + book.getCategory()
                        );
                    }
                    System.out.println(bookstore);
                }
                case 5 -> {
                    System.out.print("\nPlease enter ISBN: ");
                    int bookIsbn = sc.nextInt();

                    System.out.println("\nShowing book...");

                    Book book = dao.getBookByIsbn(bookIsbn);
                    final Object[][] table = new String[6][];
                    table[0] = new String[] { "________________________________\n ISBN: ", String.valueOf(book.getIsbn()) + "\n--------------------------------" };
                    table[1] = new String[] { "Title: ", book.getTitle() };
                    table[2] = new String[] { "Author: ", book.getAuthor() };
                    table[3] = new String[] { "Price: ", String.valueOf(book.getPrice()) };
                    table[4] = new String[] { "Description: ", book.getDescription() };
                    table[5] = new String[] { "Category: ", book.getCategory() + "\n________________________________" };

                    for(final Object[] row : table){
                        System.out.format("%-15s%-15s%n", row);
                    }
                    System.out.println(bookstore);
                }
            }
        }


    }
}
