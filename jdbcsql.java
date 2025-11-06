import java.sql.*;
import java.util.*;

class jdbcsql {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/library_management";
        String username = "lib_user";
        String password = "Lib@1234";

//DB_USER=lib_user
// DB_PASSWORD=Lib@1234
// DB_NAME=library_management

        Connection con = DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established Successfully!\n");

        Statement st = con.createStatement();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Insert Record");
            System.out.println("2. Update Record");
            System.out.println("3. Delete Record");
            System.out.println("4. Display Records");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                System.out.print("Enter Name: ");
                String name = sc.next();
                System.out.print("Enter Marks: ");
                int marks = sc.nextInt();

                String insert = "INSERT INTO students VALUES(" + id + ", '" + name + "', " + marks + ")";
                st.executeUpdate(insert);
                System.out.println("Record Inserted Successfully!");

            } else if (choice == 2) {
                System.out.print("Enter ID to Update: ");
                int id = sc.nextInt();
                System.out.print("Enter New Marks: ");
                int marks = sc.nextInt();

                String update = "UPDATE students SET marks = " + marks + " WHERE id = " + id;
                st.executeUpdate(update);
                System.out.println("Record Updated Successfully!");

            } else if (choice == 3) {
                System.out.print("Enter ID to Delete: ");
                int id = sc.nextInt();

                String delete = "DELETE FROM students WHERE id = " + id;
                st.executeUpdate(delete);
                System.out.println("Record Deleted Successfully!");

            } else if (choice == 4) {
                String select = "SELECT * FROM students";
                ResultSet rs = st.executeQuery(select);

                System.out.println("\n--- Student Records ---");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int marks = rs.getInt("marks");
                    System.out.println(id + "  " + name + "  " + marks);
                }

            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid Choice!");
            }
        }

        st.close();
        con.close();
        System.out.println("\nConnection Closed.");
        sc.close();
    }
}