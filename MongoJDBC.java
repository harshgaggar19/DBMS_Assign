import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.*;

public class MongoJDBC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- Connection Setup ---
        String uri = "mongodb://te31234:te31234@10.10.8.119:27017/?authSource=te31234_db";
        MongoClient mongo = new MongoClient(new MongoClientURI(uri));
        MongoDatabase db = mongo.getDatabase("library_management");
        MongoCollection<Document> collection = db.getCollection("students");

        System.out.println("✅ MongoDB Connection Established Successfully!\n");

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

                Document doc = new Document("id", id)
                        .append("name", name)
                        .append("marks", marks);

                collection.insertOne(doc);
                System.out.println("Record Inserted Successfully!");

            } else if (choice == 2) {
                System.out.print("Enter ID to Update: ");
                int id = sc.nextInt();
                System.out.print("Enter New Marks: ");
                int marks = sc.nextInt();

                Document query = new Document("id", id);
                Document update = new Document("$set", new Document("marks", marks));
                collection.updateOne(query, update);

                System.out.println("Record Updated Successfully!");

            } else if (choice == 3) {
                System.out.print("Enter ID to Delete: ");
                int id = sc.nextInt();

                Document query = new Document("id", id);
                collection.deleteOne(query);

                System.out.println("Record Deleted Successfully!");

            } else if (choice == 4) {
                System.out.println("\n--- Student Records ---");
                for (Document doc : collection.find()) {
                    System.out.println(
                            doc.get("id") + "  " +
                            doc.get("name") + "  " +
                            doc.get("marks")
                    );
                }

            } else if (choice == 5) {
                break;

            } else {
                System.out.println("Invalid Choice!");
            }
        }

        mongo.close();
        sc.close();
        System.out.println("\nConnection Closed.");
    }
}
