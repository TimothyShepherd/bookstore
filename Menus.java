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
                | 6. Select to Purchase    |
                | 7. View Cart             |
                | 8. Logout                |
                |__________________________|
                """;

        System.out.println(bookstore);

        while(continues){

            System.out.println("-> ");

            int choice = sc.nextInt();

            switch(choice){

                case 1 -> {
                    System.out.println("\nShowing Categories...\n");
                    List<Book> categories = dao.getCategories();
                    for(Book bookCategory : categories){
                        final Object[][] table = new String[5][];
                        table[0] = new String[] { "________________________________\n ID: ", String.valueOf(bookCategory.getIsbn()) + "\n--------------------------------" };
                        table[1] = new String[] { "Title: ", bookCategory.getTitle() };
                        table[2] = new String[] { "Author: ", bookCategory.getAuthor() };
                        table[3] = new String[] { "Price: ", String.valueOf(bookCategory.getPrice()) };
                        table[4] = new String[] { "Description: ", bookCategory.getDescription() + "\n________________________________" };

                        for(final Object[] row : table){
                            System.out.format("%-15s%-15s%n", row);
                        }
                    }
                    System.out.println("\n" + bookstore);
                }
                case 2 -> {

                }
            }
        }
    }


}
