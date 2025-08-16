package ca.bytetube._07_bookreservation;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();


        Book book1 = new Book("B001", "Java Basic", 2, "James Gosling", "ISBN001");
        Book book2 = new Book("B002", "Python Advance", 1, "Guido van Rossum", "ISBN002");
        CD cd1 = new CD("C001", "Happy Music", 1, "Various Artists", 60);

        library.addResource(book1);
        library.addResource(book2);
        library.addResource(cd1);


        System.out.println("=== search test ===");
        List<Resource> searchResults = library.search("Java");
        searchResults.forEach(resource ->
                System.out.println("find resource: " + resource.getName())
        );


        System.out.println("\n=== reserve test ===");
        String user1 = "User001";
        String user2 = "User002";
        String user3 = "User003";
        String user4 = "User004";
        boolean result1 = library.reserve("B001", user1);
        System.out.println("User001 reserved: " + result1);

        boolean result2 = library.reserve("B001", user2);
        System.out.println("User002 reserved: " + result2);

        boolean result3 = library.reserve("B001", user3);
        System.out.println("User003 reserved: " + result3);

        boolean result4 = library.reserve("B001", user4);
        System.out.println("User004 reserved: " + result4);


        System.out.println("\n=== release test ===");
        library.releaseResource("B001", user1);
//        library.release("B001", user2);
        System.out.println("After User 1 releases the resources, " +
                "the users in the waiting queue will automatically obtain the reservation.");


        System.out.println("\n=== resource status test ===");
        Resource resource = library.getResource("B001");
        if (resource != null) {
            System.out.println("resource " + resource.getName() + " current available quantity: " +
                    resource.getAvailableQuantity());
            System.out.println("Length of the waiting queue: " + resource.getWaitingList().size());
        } else {
            System.out.println("B001 NOT FOUND");
        }
    }

}
