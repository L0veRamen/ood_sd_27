package ca.bytetube._07_bookreservation;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();

        // Add resources
        Book book1 = new Book("B001", "Java Basic", 2, "ISBN001", "James Gosling");
        CD cd1 = new CD("C001", "Happy Music", 1, "Various Artists", 60);
        library.addResource(book1);
        library.addResource(cd1);

        // Add users with different balances
        User alice = new User("U001", "Alice", 10.0);
        User bob = new User("U002", "Bob", 5.0);
        User charlie = new User("U003", "Charlie", 1.0);
        library.addUser(alice);
        library.addUser(bob);
        library.addUser(charlie);

        System.out.println("=== Initial User Balances ===");
        System.out.printf("%s: $%.2f%n", alice.getName(), alice.getBalance());
        System.out.printf("%s: $%.2f%n", bob.getName(), bob.getBalance());
        System.out.printf("%s: $%.2f%n", charlie.getName(), charlie.getBalance());

        System.out.println("\n=== Reservations ===");
        // Alice reserves book for 3 days ($3.00)
        library.reserve("B001", "U001", 3);

        // Bob reserves CD for 7 days ($14.00)
        library.reserve("C001", "U002", 7);

        // Charlie tries to reserve book (should go to waiting list)
        library.reserve("B001", "U003", 1);

        System.out.println("\n=== Returns and Payments ===");

        // Alice returns and pays (has enough money)
        library.releaseAndPay("B001", "U001");

        // Bob tries to return but doesn't have enough money
        library.releaseAndPay("C001", "U002");

        System.out.println("\n=== Bob adds money and tries again ===");
        bob.addMoney(10.0);
        library.releaseAndPay("C001", "U002");

        System.out.println("\n=== Final User Balances ===");
        System.out.printf("%s: $%.2f%n", alice.getName(), alice.getBalance());
        System.out.printf("%s: $%.2f%n", bob.getName(), bob.getBalance());
        System.out.printf("%s: $%.2f%n", charlie.getName(), charlie.getBalance());

        System.out.println("\n=== Resource Status ===");
        Resource book = library.getResource("B001");
        Resource cd = library.getResource("C001");
        System.out.println("Book available: " + book.getAvailableQuantity());
        System.out.println("CD available: " + cd.getAvailableQuantity());
        System.out.println("Book waiting list size: " + book.getWaitingList().size());


//        Book book1 = new Book("B001", "Java Basic", 2, "James Gosling", "ISBN001");
//        Book book2 = new Book("B002", "Python Advance", 1, "Guido van Rossum", "ISBN002");
//        CD cd1 = new CD("C001", "Happy Music", 1, "Various Artists", 60);
//
//        library.addResource(book1);
//        library.addResource(book2);
//        library.addResource(cd1);
//
//
//        System.out.println("=== search test ===");
//        List<Resource> searchResults = library.search("Java");
//        searchResults.forEach(resource ->
//                System.out.println("find resource: " + resource.getName())
//        );
//
//
//        System.out.println("\n=== reserve test ===");
//        String user1 = "User001";
//        String user2 = "User002";
//        String user3 = "User003";
//        String user4 = "User004";
//        boolean result1 = library.reserve("B001", user1);
//        System.out.println("User001 reserved: " + result1);
//
//        boolean result2 = library.reserve("B001", user2);
//        System.out.println("User002 reserved: " + result2);
//
//        boolean result3 = library.reserve("B001", user3);
//        System.out.println("User003 reserved: " + result3);
//
//        boolean result4 = library.reserve("B001", user4);
//        System.out.println("User004 reserved: " + result4);
//
//
//        System.out.println("\n=== release test ===");
//        library.releaseResource("B001", user1);
////        library.release("B001", user2);
//        System.out.println("After User 1 releases the resources, " +
//                "the users in the waiting queue will automatically obtain the reservation.");
//
//
//        System.out.println("\n=== resource status test ===");
//        Resource resource = library.getResource("B001");
//        if (resource != null) {
//            System.out.println("resource " + resource.getName() + " current available quantity: " +
//                    resource.getAvailableQuantity());
//            System.out.println("Length of the waiting queue: " + resource.getWaitingList().size());
//        } else {
//            System.out.println("B001 NOT FOUND");
//        }
    }

}
