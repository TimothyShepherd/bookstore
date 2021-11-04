package Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) throws SQLException{

        Connection connection;
        connection = ConnectionFactory.getConnection();
        Scanner sc = new Scanner(System.in);
        User user = new User();

        // Outer while loop enables logout functionality
        boolean running = true;
        while(running)
        {

            boolean proceed = false;

            // Sign/Banner
//            System.out.println("""
//
//                    """);

            String welcomeMenu = """
                    ____________________________
                    |   Welcome to Book Store  |
                    |__________________________|
                    | Please select an action: |
                    |--------------------------|
                    | 1. Login                 |
                    | 2. Register Account      |
                    | 3. Exit                  |
                    |__________________________|
                    """;

            System.out.println(welcomeMenu);

            while(proceed == false)
            {

                System.out.print("-> ");

                int choice = sc.nextInt();

                switch(choice)
                {
                    case 1 -> {

                        System.out.print("Please enter your email: ");
                        user.setEmail(sc.next());

                        System.out.print("Please enter your password: ");
                        user.setPassword(sc.next());

                        // Queries the User table to see if any rows match the email, password entered
                        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, user.getEmail());
                        preparedStatement.setString(2, user.getPassword());
                        ResultSet resultSet = preparedStatement.executeQuery();
                        int size = 0;

                        // Tries to see how many rows were returned
                        if(resultSet != null){
                            resultSet.next();
                            size = resultSet.getRow();
                        }
                        // Allows the user to login only if 1 or more rows are returned.
                        if(size >= 1){
                            System.out.println("\nLogin Successful!");
                            proceed = true;
                            Menus.bookstoreMenu();
                        }else{
                            System.out.println("\nInvalid login credentials.");
                            System.out.println(welcomeMenu);
                             }
                        }

                        case 2 -> {

                            System.out.print("\nPlease enter your first name: ");
                            user.setFirstName(sc.next());

                            System.out.print("Please enter your last name: ");
                            user.setLastName(sc.next());

                            System.out.print("Please create your email: ");
                            user.setEmail(sc.next());

                            System.out.print("Please enter desired password: ");
                            user.setPassword(sc.next());

                            String sql = ("INSERT INTO user (first_name, last_name, email, password) VALUES (?, ?, ?, ?)");

                            PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
                            preparedStatement2.setString(1, user.getFirstName());
                            preparedStatement2.setString(2, user.getLastName());
                            preparedStatement2.setString(3, user.getEmail());
                            preparedStatement2.setString(4, user.getPassword());
                            int count = preparedStatement2.executeUpdate();
                            if(count > 0){
                                System.out.println("\nYour account has been created.");
                                System.out.println("\nYou may now login at the main menu.");
                            }else{
                                System.out.println("Sorry. Something went wrong.");
                            }
                            System.out.println(welcomeMenu);
                        }

                        case 3 -> {
                            System.out.println("\n Goodbye.");
                            Runtime.getRuntime().exit(0);
                        }

                        default -> {
                            System.out.println("\nInvalid selection!");
                            System.out.println("\nTry again: \n");
                    }
                }
            }
        }
    }
}