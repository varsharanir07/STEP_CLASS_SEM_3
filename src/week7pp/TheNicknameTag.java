package week7pp;

/**
 * Problem 3: The Nickname Tag
 * 
 * Scenario:
 * A chat app shows a friendly short nickname instead of your full name.
 * 
 * Problem Statement:
 * Create an immutable NameTag class that takes a full name once and builds a nickname from it — first name plus the last name's initial.
 * 
 * Requirements:
 * - Take one full name string in the constructor (e.g., "Maria Gomez") and split it into first and last name.
 * - Store whatever you keep as final fields — nothing about the name should be changeable after creation.
 * - Provide a method that returns the nickname (e.g., "Maria G."), built from the stored parts.
 * - Assume the full name always has exactly one first name and one last name, separated by a single space.
 */
final class NameTag {
    private final String firstName;
    private final String lastName;
    private final String nickname;

    public NameTag(String fullName) {
        if (fullName != null) {
            String[] parts = fullName.trim().split("\\s+");
            this.firstName = parts.length > 0 ? parts[0] : "";
            this.lastName = parts.length > 1 ? parts[1] : "";
        } else {
            this.firstName = "";
            this.lastName = "";
        }

        if (!this.lastName.isEmpty()) {
            this.nickname = this.firstName + " " + this.lastName.charAt(0) + ".";
        } else {
            this.nickname = this.firstName;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }
}

public class TheNicknameTag {
    public static void main(String[] args) {
        NameTag tag = new NameTag("Maria Gomez");
        System.out.println("tag.getNickname() -> \"" + tag.getNickname() + "\"");

        NameTag tag2 = new NameTag("Alan Turing");
        System.out.println("new NameTag(\"Alan Turing\").getNickname() -> \"" + tag2.getNickname() + "\"");
    }
}
