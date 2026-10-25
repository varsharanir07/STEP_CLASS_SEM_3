package week8pp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem 2: Library Item Due Date Calculator
 * 
 * Task:
 * A library manages different types of items (e.g., Books, DVDs, Magazines). Each item type
 * has a specific borrowing duration. The system needs to process a list of borrowed items
 * and calculate their respective due dates from the current date.
 * 
 * Input:
 * The first line contains an integer N, the number of borrowed items. Each of the next N lines
 * contains ItemType ItemTitle:
 * - BOOK "The Great Gatsby"
 * - DVD "Inception"
 * - MAGAZINE "National Geographic Jan 2023"
 * 
 * Output:
 * For each borrowed item, display ItemTitle: DueDate. The DueDate should be formatted as YYYY-MM-DD.
 * 
 * Business Rules:
 * - Books have a standard borrowing period of 14 days.
 * - DVDs have a standard borrowing period of 7 days.
 * - Magazines have a standard borrowing period of 3 days.
 * - The due date is calculated relative to the "current date". For simplicity, assume the current date is 2023-10-26.
 * 
 * Constraints:
 * - 1 <= N <= 100
 * - ItemTitle is a string.
 * 
 * Instructions:
 * Implement a solution that allows different library item types to determine their due date
 * based on their specific rules, while the main processing logic can handle all items uniformly.
 * You may use Python's datetime module / Java's java.time package for date calculations.
 * Consider how to model different library item types that share common attributes but have
 * distinct borrowing rules. Focus on how a central system can calculate due dates for a
 * collection of various item types without explicit type checking.
 */
abstract class LibraryItem {
    protected final String itemType;
    protected final String title;

    public LibraryItem(String itemType, String title) {
        this.itemType = itemType;
        this.title = title;
    }

    public String getItemType() {
        return itemType;
    }

    public String getTitle() {
        return title;
    }

    // Abstract method to calculate due date polymorphically
    public abstract LocalDate calculateDueDate(LocalDate currentDate);
}

class BookItem extends LibraryItem {
    public BookItem(String title) {
        super("BOOK", title);
    }

    @Override
    public LocalDate calculateDueDate(LocalDate currentDate) {
        // Standard borrowing period of 14 days
        return currentDate.plusDays(14);
    }
}

class DvdItem extends LibraryItem {
    public DvdItem(String title) {
        super("DVD", title);
    }

    @Override
    public LocalDate calculateDueDate(LocalDate currentDate) {
        // Standard borrowing period of 7 days
        return currentDate.plusDays(7);
    }
}

class MagazineItem extends LibraryItem {
    public MagazineItem(String title) {
        super("MAGAZINE", title);
    }

    @Override
    public LocalDate calculateDueDate(LocalDate currentDate) {
        // Standard borrowing period of 3 days
        return currentDate.plusDays(3);
    }
}

class LibraryProcessingSystem {
    private final List<LibraryItem> items = new ArrayList<>();
    private final LocalDate currentDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LibraryProcessingSystem(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public void addItem(LibraryItem item) {
        if (item != null) {
            items.add(item);
        }
    }

    // Uniformly process all borrowed items without type-checking
    public void processAllDueDates() {
        for (LibraryItem item : items) {
            LocalDate dueDate = item.calculateDueDate(currentDate);
            System.out.printf("%s: %s%n", item.getTitle(), dueDate.format(FORMATTER));
        }
    }
}

public class LibraryItemDueDateCalculator {
    public static final LocalDate CURRENT_DATE = LocalDate.of(2023, 10, 26);

    public static LibraryItem createItem(String type, String title) {
        String cleanTitle = title.trim();
        if (cleanTitle.startsWith("\"") && cleanTitle.endsWith("\"") && cleanTitle.length() >= 2) {
            cleanTitle = cleanTitle.substring(1, cleanTitle.length() - 1);
        }

        if ("BOOK".equalsIgnoreCase(type)) {
            return new BookItem(cleanTitle);
        } else if ("DVD".equalsIgnoreCase(type)) {
            return new DvdItem(cleanTitle);
        } else if ("MAGAZINE".equalsIgnoreCase(type)) {
            return new MagazineItem(cleanTitle);
        }
        throw new IllegalArgumentException("Unknown item type: " + type);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = Integer.parseInt(sc.nextLine().trim());
            LibraryProcessingSystem system = new LibraryProcessingSystem(CURRENT_DATE);
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                int firstSpace = line.indexOf(' ');
                if (firstSpace != -1) {
                    String type = line.substring(0, firstSpace).trim();
                    String title = line.substring(firstSpace + 1).trim();
                    system.addItem(createItem(type, title));
                }
            }
            system.processAllDueDates();
        } else {
            // Default sample demo if no input piped
            LibraryProcessingSystem system = new LibraryProcessingSystem(CURRENT_DATE);
            system.addItem(createItem("BOOK", "\"1984\""));
            system.addItem(createItem("DVD", "\"The Matrix\""));
            system.addItem(createItem("MAGAZINE", "\"Forbes Issue 500\""));
            system.processAllDueDates();
        }
        sc.close();
    }
}
